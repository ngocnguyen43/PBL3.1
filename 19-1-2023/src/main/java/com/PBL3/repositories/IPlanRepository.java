package com.PBL3.repositories;

import com.PBL3.models.PlanModel;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;

public interface IPlanRepository {
    void createOne(PlanModel domain) throws CreateFailedException, InvalidPropertiesException, NotFoundException;
}
