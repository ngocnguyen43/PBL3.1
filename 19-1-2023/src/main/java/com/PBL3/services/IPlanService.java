package com.PBL3.services;

import com.PBL3.dtos.PlanDTO;
import com.PBL3.dtos.pagination.PlanPaginationDTO;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.response.Message;

public interface IPlanService {
    Message createOne(PlanDTO dto) throws NotFoundException, InvalidPropertiesException, CreateFailedException;

    Message getOneById(String id) throws NotFoundException;

    Message updateTime(PlanDTO dto) throws InvalidPropertiesException, NotFoundException, UpdateFailedException;

    Message inactivePlan(String id) throws InvalidPropertiesException, UpdateFailedException;

    Message getAll(PlanPaginationDTO dto, String id) throws NotFoundException;
}
