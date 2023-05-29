package com.PBL3.services.impl;

import com.PBL3.daos.IPostDAO;
import com.PBL3.dtos.PostDTO;
import com.PBL3.dtos.pagination.PostPaginationDTO;
import com.PBL3.models.Notification;
import com.PBL3.models.PostModel;
import com.PBL3.models.pagination.PostPaginationModel;
import com.PBL3.services.INotificationService;
import com.PBL3.services.IPostService;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.PBL3.utils.response.Pagination;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

public class PostService implements IPostService {
    @Inject
    private IPostDAO iPostDAO;
    @Inject
    private INotificationService iNotificationService;

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
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator("anonymous")
                .withMods(Collections.singletonList("all"))
                .withAdmin(true)
                .withMessage("New post has been submitted by anonymous")
                .build();
        try {
            iPostDAO.Create(domain);
            iNotificationService.create(notification);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnexpectedException();
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("OK").build();
        return new Message.Builder(meta).build();
    }

    @Override
    public Message GetAll(PostPaginationDTO dto) throws UnexpectedException {
        List<PostModel> list;
        Integer pages;
        PostPaginationModel model = Helper.objectMapper(dto, PostPaginationModel.class);
        try {
            list = iPostDAO.GetAll(model);
            pages = iPostDAO.countAllPosts();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnexpectedException();
        }
        Pagination pagination = new Pagination.Builder().
                withCurrentPage(dto.getPage()).
                withTotalPages((int) Math.ceil((double) pages / PER_PAGE)).
                withTotalResults(pages).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK").build();
        Data data = new Data.Builder(null).withResults(list).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
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
