package com.PBL3.services.impl;

import com.PBL3.daos.IPostDAO;
import com.PBL3.dtos.PostDTO;
import com.PBL3.models.PostModel;
import com.PBL3.services.IPostService;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PostService implements IPostService {
    @Inject
    private IPostDAO iPostDAO;

    private boolean validateData(PostDTO postDTO) {
        return postDTO.getContent() != null
                && postDTO.getTitle() != null
                && postDTO.getTypeId() != null;
    }

    @Override
    public Message Create(PostDTO postDTO) throws UnexpectedException, InvalidPropertiesException {
        if (!validateData(postDTO)) {
            throw new InvalidPropertiesException("Invalid input");
        }
        PostModel domain = Helper.objectMapper(postDTO, PostModel.class);
        domain.setId(IDGeneration.generate());
        try {
            iPostDAO.Create(domain);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnexpectedException();
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("OK").build();
        return new Message.Builder(meta).build();
    }

    @Override
    public Message GetAll() throws UnexpectedException {
        List<PostModel> list;
        try {
            list = iPostDAO.GetAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnexpectedException();
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK").build();
        Data data = new Data.Builder(null).withResults(list).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message InactivePost(String id) throws NotFoundException {
        try {
            iPostDAO.SetAction(id);
        } catch (NotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("OK").build();
        return new Message.Builder(meta).build();
    }
}
