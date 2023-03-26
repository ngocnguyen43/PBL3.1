package com.PBL3.repositories.impls;

import com.PBL3.daos.IBusinessDAO;
import com.PBL3.models.Business;
import com.PBL3.repositories.IBusinessRepository;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;

import javax.inject.Inject;

public class BusinessRepository implements IBusinessRepository{
	@Inject
	private IBusinessDAO businessDAO;
	@Override
	public void createBusines(Business domainBusiness) throws DuplicateEntryException, CreateFailedException {
		// TODO Auto-generated method stub
		Business business = businessDAO.findOne(domainBusiness.getBusinessName());
		if (business != null) throw new DuplicateEntryException("Business Name has already existed");
		String id = businessDAO.save(domainBusiness);
		if (id == null) throw new CreateFailedException("Create Business Failed");
		return ;
		
	}

}
