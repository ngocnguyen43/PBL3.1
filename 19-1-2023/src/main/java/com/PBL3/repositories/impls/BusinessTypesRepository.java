package com.PBL3.repositories.impls;

import com.PBL3.daos.IBusinessDAO;
import com.PBL3.daos.IBusinessTypesDAO;
import com.PBL3.models.Business;
import com.PBL3.models.BusinessTypes;
import com.PBL3.repositories.IBusinessTypesRepository;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;

import javax.inject.Inject;

public class BusinessTypesRepository implements IBusinessTypesRepository {
	@Inject
	private IBusinessTypesDAO businessTypesDAO;
	@Inject
	private IBusinessDAO businessDAO;

	@Override
	public void createBusinessType(BusinessTypes domainBusinessTypes)
			throws DuplicateEntryException, CreateFailedException, NotFoundException {
		// TODO Auto-generated method stub
		Business business = businessDAO.findOneById(domainBusinessTypes.getBusinessId());
		if (business == null)
			throw new NotFoundException("Not Found Business to Create Type");
		BusinessTypes businessTypes = businessTypesDAO.findOne(domainBusinessTypes.getTypeName());
		if (businessTypes != null)
			throw new DuplicateEntryException("Business Type has already existed");
		String id = businessTypesDAO.save(domainBusinessTypes);
		if (id == null)
			throw new CreateFailedException("Create Business Type Failed");
	}

}
