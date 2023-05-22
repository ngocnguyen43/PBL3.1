package com.PBL3.daos.impl;

import com.PBL3.daos.IPostTypeDAO;
import com.PBL3.models.PostTypeModel;
import com.PBL3.utils.mapper.PostTypeMapper;

import java.util.List;

public class PostTypeDAO extends AbstractDAO<PostTypeModel> implements IPostTypeDAO {
    @Override
    public void Create(PostTypeModel postTypeModel) {
        String sql = "INSERT INTO login.post_types (type_id,type_name) VALUES (?,?)";
        insert(sql, postTypeModel.getId(), postTypeModel.getTypeName());
    }

    @Override
    public List<PostTypeModel> GetALl() {
        String sql = "SELECT post_types.type_id,post_types.type_name FROM login.post_types";
        return query(sql, new PostTypeMapper());
    }

    @Override
    public PostTypeModel GetByName(String name) {
        String sql = "SELECT * FROM login.post_types WHERE type_name = ?";
        List<PostTypeModel> postTypeModels = query(sql, new PostTypeMapper(), name);
        return postTypeModels.isEmpty() ? null : postTypeModels.get(0);
    }
}
