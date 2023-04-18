package com.PBL3.services.impl;

import com.PBL3.daos.IPostTypeDAO;
import com.PBL3.dtos.PostTypeDTO;
import com.PBL3.models.PostTypeModel;
import com.PBL3.services.IPostTypeService;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

public class PostTypeService implements IPostTypeService {
    @Inject
    private IPostTypeDAO iPostTypeDAO;

    @Override
    public Message Create(PostTypeDTO postTypeDTO) throws DuplicateEntryException, UnexpectedException {
        boolean isExist = iPostTypeDAO.GetByName(postTypeDTO.getTypeName()) != null;
        if (isExist) {
            throw new DuplicateEntryException("Post Type is Already Existed");
        }
        PostTypeModel domain = Helper.objectMapper(postTypeDTO, PostTypeModel.class);
        domain.setId(IDGeneration.generate());
        try {
            iPostTypeDAO.Create(domain);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnexpectedException();
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("OK").build();
        return new Message.Builder(meta).build();
    }
}
