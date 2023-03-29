package com.PBL3.repositories;

import com.PBL3.models.PlanInspectorModel;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;

public interface IPlanInspectorRepository {
    void createOne(PlanInspectorModel domain) throws DuplicateEntryException, CreateFailedException;

    void deactiveInspector(PlanInspectorModel domain) throws NotFoundException, CreateFailedException;
}
