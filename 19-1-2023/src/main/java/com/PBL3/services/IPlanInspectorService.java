package com.PBL3.services;

import com.PBL3.dtos.PlanInspectorDTO;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.response.Message;

public interface IPlanInspectorService {
    Message createOne(PlanInspectorDTO dto, String from) throws DuplicateEntryException, CreateFailedException, InvalidPropertiesException;

    Message inactive(PlanInspectorDTO dto, String id) throws UpdateFailedException, InvalidPropertiesException;
}
