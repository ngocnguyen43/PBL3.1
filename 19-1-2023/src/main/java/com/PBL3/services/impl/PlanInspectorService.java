package com.PBL3.services.impl;

import com.PBL3.daos.IPlanInspectorDAO;
import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.PlanInspectorDTO;
import com.PBL3.models.Notification;
import com.PBL3.models.PlanInspectorModel;
import com.PBL3.services.INotificationService;
import com.PBL3.services.IPlanInspectorService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.PBL3.utils.response.Response;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

public class PlanInspectorService implements IPlanInspectorService {
    @Inject
    private IPlanInspectorDAO planInspectorDAO;
    @Inject
    private INotificationService notificationService;
    @Inject
    private IUserDAO userDAO;

    @Override
    public Message createOne(PlanInspectorDTO dto, String creator) throws DuplicateEntryException, CreateFailedException, InvalidPropertiesException {
        if (creator == null) throw new InvalidPropertiesException("Invalid properties");
        String role = userDAO.getUserRole(creator);
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(creator)
                .withMods(Collections.singletonList(dto.getUserId()))
                .withMessage("You have been added to new plan by " + role)
                .build();
        boolean isExisted = planInspectorDAO.findOne(dto.getUserId(), dto.getPlanId()) != null;
        if (isExisted) throw new DuplicateEntryException(Response.DUPLICATED);
        PlanInspectorModel domain = Helper.objectMapper(dto, PlanInspectorModel.class);
        String id = IDGeneration.generate();
        domain.setId(id);

        try {
            planInspectorDAO.createOne(domain);
            notificationService.create(notification);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CreateFailedException(Response.CREATE_FAILED);
        }
    }

    @Override
    public Message inactive(PlanInspectorDTO dto, String userId) throws UpdateFailedException, InvalidPropertiesException {
        if (userId == null) throw new InvalidPropertiesException("Invalid properties");
        String role = userDAO.getUserRole(userId);
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(userId)
                .withMods(Collections.singletonList(dto.getUserId()))
                .withMessage("You have been deleted from plan by " + role)
                .build();
        try {
            PlanInspectorModel domain = Helper.objectMapper(dto, PlanInspectorModel.class);
            planInspectorDAO.inactiveInspector(domain.getUserId(), domain.getPlanId());
            notificationService.create(notification);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException(Response.FAILED);
        }
    }
}
