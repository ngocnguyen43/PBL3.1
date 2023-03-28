package com.PBL3.services.impl;

import com.PBL3.dtos.PlanInspectorDTO;
import com.PBL3.models.PlanInspectorModel;
import com.PBL3.repositories.IPlanInspectorRepository;
import com.PBL3.services.IPlanInspectorService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

public class PlanInspectorService implements IPlanInspectorService {
    @Inject
    private IPlanInspectorRepository iPlanInspectorRepository;


    @Override
    public Message createOne(PlanInspectorDTO dto) {
        try {
            PlanInspectorModel domain = Helper.objectMapper(dto,PlanInspectorModel.class);
            String id = IDGeneration.generate();
            domain.setId(id);
            iPlanInspectorRepository.createOne(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Add Inspector Into Plan Successfully!").build();
            return new Message.Builder(meta).build();
        } catch (CreateFailedException | DuplicateEntryException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message deactive(PlanInspectorDTO dto) {
        try {
            PlanInspectorModel domain = Helper.objectMapper(dto,PlanInspectorModel.class);
            iPlanInspectorRepository.deactiveInspector(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Inactive Inspector in Plan Successfully!").build();
            return new Message.Builder(meta).build();
        } catch (CreateFailedException | NotFoundException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }
    }
}
