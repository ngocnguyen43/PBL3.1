package com.PBL3.daos;

import com.PBL3.models.PostTypeModel;

import java.util.List;

public interface IPostTypeDAO extends GenericDAO<PostTypeModel> {
    void Create(PostTypeModel postTypeModel);

    List<PostTypeModel> GetALl();

    PostTypeModel GetByName(String name);
}
