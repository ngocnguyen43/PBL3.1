package com.PBL3.services;

import com.PBL3.dtos.BusinessDTO;
import com.PBL3.utils.response.Message;

public interface IBusinessService {
	Message createBusiness(BusinessDTO businessDTO);
}
