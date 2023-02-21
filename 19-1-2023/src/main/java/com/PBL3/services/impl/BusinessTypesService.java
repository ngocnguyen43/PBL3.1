package com.PBL3.services.impl;

import javax.inject.Inject;

import com.PBL3.dtos.BusinessTypesDTO;
import com.PBL3.models.BusinessTypes;
import com.PBL3.repositories.IBusinessTypesRepository;
import com.PBL3.services.IBusinessTypesService;
import com.PBL3.ultils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.ultils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.ultils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.ultils.helpers.Helper;
import com.PBL3.ultils.helpers.IDGeneration;
import com.PBL3.ultils.response.Message;
import com.PBL3.ultils.response.Meta;

public class BusinessTypesService implements IBusinessTypesService {

	@Inject
	private IBusinessTypesRepository businessTypesRepository;

	@Override
	public Message createBusinessType(BusinessTypesDTO businessTypesDTO) {
		// TODO Auto-generated method stub
		try {
			BusinessTypes businessTypes = Helper.objectMapper(businessTypesDTO, BusinessTypes.class);
			String id = IDGeneration.generate();
			businessTypes.setId(id);
			Message message = businessTypesRepository.createBusinessType(businessTypes);
			return message;
		} catch (NotFoundException | DuplicateEntryException | CreateFailedException e) {
			Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage())
					.build();
			Message message = new Message.Builder(meta).build();
			return message;
		} catch (Exception e) {
			// TODO: handle exception
			Meta meta = new Meta.Builder(500).withError(e.getMessage()).build();
			Message message = new Message.Builder(meta).build();
			return message;
		}

	}

}
