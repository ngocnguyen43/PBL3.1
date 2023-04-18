package com.PBL3.daos;

import com.PBL3.models.PostModel;

import java.util.List;

public interface IPostDAO  extends  GenericDAO<PostModel> {
    void Create(PostModel postModel);
    List<PostModel> GetAll(PostModel postModel);
}
