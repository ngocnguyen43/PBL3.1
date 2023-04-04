package com.PBL3.services.impl;

import com.PBL3.daos.IPlanInspectorDAO;
import com.PBL3.dtos.PlanInspectorDTO;
import com.PBL3.models.PlanInspectorModel;
import com.PBL3.services.IPlanInspectorService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

public class PlanInspectorService implements IPlanInspectorService {
    @Inject
    private IPlanInspectorDAO planInspectorDAO;


    @Override
    public Message createOne(PlanInspectorDTO dto) throws DuplicateEntryException, CreateFailedException {
        boolean isExisted = planInspectorDAO.findOne(dto.getUserId(), dto.getPlanId()) != null;
        if (isExisted) throw new DuplicateEntryException("Inspector already in Plan");
        PlanInspectorModel domain = Helper.objectMapper(dto, PlanInspectorModel.class);
        String id = IDGeneration.generate();
        domain.setId(id);
        try {
            planInspectorDAO.createOne(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Add Inspector Into Plan Successfully!").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException("Add Inspector Into Plan Failed");
        }
    }

    @Override
    public Message deactive(PlanInspectorDTO dto) throws UpdateFailedException {
        try {
            PlanInspectorModel domain = Helper.objectMapper(dto, PlanInspectorModel.class);
            planInspectorDAO.inactiveInspector(domain.getUserId(), domain.getPlanId());
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Inactive Inspector in Plan Successfully!").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException("Inactive Inspector Failed");
        }
    }
}
