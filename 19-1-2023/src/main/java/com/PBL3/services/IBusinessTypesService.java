package com.PBL3.services;

import com.PBL3.dtos.BusinessTypesDTO;
import com.PBL3.utils.response.Message;

public interface IBusinessTypesService {
    Message createBusinessType(BusinessTypesDTO businessTypesDTO);
}
