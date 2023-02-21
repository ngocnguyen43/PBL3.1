package com.PBL3.repositories.impls;

import javax.inject.Inject;

import com.PBL3.daos.IBusinessDAO;
import com.PBL3.daos.IBussinessTypesDAO;
import com.PBL3.models.Business;
import com.PBL3.models.BusinessTypes;
import com.PBL3.repositories.IBusinessTypesRepository;
import com.PBL3.ultils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.ultils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.ultils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.ultils.response.Message;
import com.PBL3.ultils.response.Meta;

public class BusinessTypesRepository implements IBusinessTypesRepository{
	@Inject
	private IBussinessTypesDAO bussinessTypesDAO;
	@Inject
	private IBusinessDAO businessDAO;

	@Override
	public Message createBusinessType(BusinessTypes domainBusinessTypes) throws DuplicateEntryException, CreateFailedException, NotFoundException {
		// TODO Auto-generated method stub
		Business business = businessDAO.findOneById(domainBusinessTypes.getBusinessId());
		if(business == null) throw new NotFoundException("Not Found Business to Create Type");
		BusinessTypes businessTypes = bussinessTypesDAO.findOne(domainBusinessTypes.getTypeName());
		if (businessTypes != null) throw new DuplicateEntryException("Business Type has already existed");
		String id = bussinessTypesDAO.save(domainBusinessTypes);
		if (id == null) throw new CreateFailedException("Create Business Type Failed");
		Meta meta = new Meta.Builder(200).withMessage("Created Successfully").build();
		Message message = new Message.Builder(meta).build();
		return message;
	}

}
