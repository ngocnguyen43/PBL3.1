package com.PBL3.utils.mapper;

import com.PBL3.models.PostTypeModel;
import com.PBL3.utils.helpers.CheckFieldExist;

import java.sql.ResultSet;

public class PostTypeMapper implements IMapper<PostTypeModel> {
    @Override
    public PostTypeModel mapRow(ResultSet result) {
        PostTypeModel postTypeModel = new PostTypeModel();
        try {
            postTypeModel.setId(result.getString("type_id"));
            postTypeModel.setTypeName(result.getString("type_name"));
            if (CheckFieldExist.checkExist(result, "created_at")) {
                postTypeModel.setCreatedAt(result.getTimestamp("created_at"));
                postTypeModel.setUpdatedAt(result.getTimestamp("updated_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postTypeModel;
    }
}
