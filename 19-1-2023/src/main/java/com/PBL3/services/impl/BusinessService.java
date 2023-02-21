package com.PBL3.services.impl;

import javax.inject.Inject;

import com.PBL3.dtos.BusinessDTO;
import com.PBL3.models.Business;
import com.PBL3.repositories.IBusinessRepository;
import com.PBL3.services.IBusinessService;
import com.PBL3.ultils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.ultils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.ultils.helpers.Helper;
import com.PBL3.ultils.helpers.IDGeneration;
import com.PBL3.ultils.response.Message;
import com.PBL3.ultils.response.Meta;

public class BusinessService implements IBusinessService {
	@Inject 
	private IBusinessRepository businessRepository;
	@Override
	public Message createBusiness(BusinessDTO businessDTO) {
		// TODO Auto-generated method stub
		try {
			Business business = Helper.objectMapper(businessDTO, Business.class);
			String id = IDGeneration.generate();
			business.setId(id);
			Message message = businessRepository.createBusines(business);
			return message;
		}
		catch(DuplicateEntryException | CreateFailedException e){
			Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage()).build();
			Message message = new Message.Builder(meta).build();
			return message;
		}

		catch (Exception e) {
			// TODO: handle exception
			Meta meta = new Meta.Builder(500).withError(e.getMessage()).build();
			Message message = new Message.Builder(meta).build();
			return message;

		}
	}
	
}
