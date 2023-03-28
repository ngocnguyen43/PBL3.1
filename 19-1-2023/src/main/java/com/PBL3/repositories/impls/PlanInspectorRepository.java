package com.PBL3.repositories.impls;

import com.PBL3.daos.IPlanInspectorDAO;
import com.PBL3.models.PlanInspectorModel;
import com.PBL3.repositories.IPlanInspectorRepository;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;

import javax.inject.Inject;

public class PlanInspectorRepository implements IPlanInspectorRepository {
    @Inject
    private IPlanInspectorDAO iPlanInspectorDAO;
    @Override
    public void createOne(PlanInspectorModel domain) throws DuplicateEntryException, CreateFailedException {
        PlanInspectorModel isExisted = iPlanInspectorDAO.findOne(domain.getUserId(),domain.getPlanId());
        if (isExisted != null) throw new DuplicateEntryException("Inspectors already in Plan");
        try {
            iPlanInspectorDAO.createOne(domain);
        }catch (Exception e){
            e.printStackTrace();
            throw  new CreateFailedException("Create new Inspector for Plan Failed");
        }
    }

    @Override
    public void deactiveInspector(PlanInspectorModel domain) throws NotFoundException, CreateFailedException {
        PlanInspectorModel isExisted = iPlanInspectorDAO.findOne(domain.getUserId(),domain.getPlanId());
        if (isExisted == null) throw new NotFoundException("Inspector isn't Existed in Plan");
        try {
            iPlanInspectorDAO.inactiveInspector(domain.getUserId(), domain.getPlanId());
        }catch (Exception e){
            e.printStackTrace();
            throw new CreateFailedException("Inactive Inspector in Plan Failed");
        }
    }
}
