package com.PBL3.services.impl;

import com.PBL3.daos.IPostTypeDAO;
import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.PostTypeDTO;
import com.PBL3.models.Notification;
import com.PBL3.models.PostTypeModel;
import com.PBL3.services.INotificationService;
import com.PBL3.services.IPostTypeService;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class PostTypeService implements IPostTypeService {
    @Inject
    private IPostTypeDAO iPostTypeDAO;
    @Inject
    private INotificationService iNotificationService;
    @Inject
    private IUserDAO userDAO;

    @Override
    public Message Create(PostTypeDTO postTypeDTO, String userId) throws DuplicateEntryException, UnexpectedException, InvalidPropertiesException {
        if (userId == null) throw new InvalidPropertiesException("Invalid properties");
        String role = userDAO.getUserRole(userId);
        String name = userDAO.getUserName(userId);
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(userId)
                .withMods(Collections.singletonList("all"))
                .withAdmin(!role.equals("Admin"))
                .withMessage("A new post type was created by " + (role.equals("Admin") ? role : name))
                .build();
        boolean isExist = iPostTypeDAO.GetByName(postTypeDTO.getTypeName()) != null;
        if (isExist) {
            throw new DuplicateEntryException("Post Type is Already Existed");
        }
        PostTypeModel domain = Helper.objectMapper(postTypeDTO, PostTypeModel.class);
        domain.setId(IDGeneration.generate());
        try {
            iPostTypeDAO.Create(domain);
            iNotificationService.create(notification);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnexpectedException();
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("OK").build();
        return new Message.Builder(meta).build();
    }

    @Override
    public Message GetAll() throws UnexpectedException {
        List<PostTypeModel> postTypeModels;
        try {
            postTypeModels = iPostTypeDAO.GetALl();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new UnexpectedException();
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK").build();
        Data data = new Data.Builder(null).withResults(postTypeModels).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
