package com.PBL3.repositories;

import com.PBL3.models.PlanModel;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;

public interface IPlanRepository {
    void createOne(PlanModel domain) throws CreateFailedException, InvalidPropertiesException, NotFoundException;

    PlanModel findOneById(String id) throws NotFoundException;

    void updateTime(PlanModel domain) throws NotFoundException, UpdateFailedException, InvalidPropertiesException;

    void inactivePlan(String id) throws InvalidPropertiesException, UpdateFailedException;
}
