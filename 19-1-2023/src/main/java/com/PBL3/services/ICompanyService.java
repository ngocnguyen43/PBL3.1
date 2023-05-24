package com.PBL3.services;

import com.PBL3.dtos.pagination.UserPaginationDTO;
import com.PBL3.utils.response.Message;

public interface ICompanyService {
    Message getAllcompanies(UserPaginationDTO dto);
}
