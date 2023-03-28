package com.PBL3.services.impl;

import com.PBL3.dtos.BusinessDTO;
import com.PBL3.models.Business;
import com.PBL3.repositories.IBusinessRepository;
import com.PBL3.services.IBusinessService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

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
			businessRepository.createBusines(business);

			Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Created Successfully").build();
			return new Message.Builder(meta).build();
		} catch (DuplicateEntryException | CreateFailedException e) {
			Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage())
					.build();
			return new Message.Builder(meta).build();
		}

		catch (Exception e) {
			// TODO: handle exception
			Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withError(e.getMessage()).build();
			return new Message.Builder(meta).build();

		}
	}

}
