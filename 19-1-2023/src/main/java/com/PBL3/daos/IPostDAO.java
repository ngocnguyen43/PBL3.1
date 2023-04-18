package com.PBL3.daos;

import com.PBL3.models.PostModel;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;

import java.util.List;

public interface IPostDAO extends GenericDAO<PostModel> {
    void Create(PostModel postModel);

    List<PostModel> GetAll();

    void SetAction(String id) throws NotFoundException;

    PostModel GetById(String id);

}
