package com.PBL3.utils.mapper;

import com.PBL3.models.PostModel;
import com.PBL3.models.PostTypeModel;
import com.PBL3.utils.helpers.CheckFieldExist;

import java.sql.ResultSet;

public class PostMapper implements IMapper<PostModel> {
    @Override
    public PostModel mapRow(ResultSet result) {
        PostModel postModel = new PostModel();
        try {
            postModel.setId(result.getString("post_id"));
            postModel.setIsIncognito(result.getInt("isIncognito"));
            postModel.setTitle(result.getString("title"));
            postModel.setContent(result.getString("content"));
            postModel.setUserId(result.getString("user_id"));
            postModel.setPhoneNUmber(result.getString("phone_number"));
            postModel.setAction(result.getInt("action"));
            PostTypeModel postTypeModel = new PostTypeModel();
            postTypeModel.setTypeName(result.getString("type_name"));
            postTypeModel.setId(result.getString("type_id"));
            postModel.setPostTypeModel(postTypeModel);
            if (CheckFieldExist.checkExist(result, "created_at")) {
                postModel.setModifiedBy(result.getString("modified_by"));
                postModel.setCreatedAt(result.getTimestamp("created_at"));
                postModel.setUpdatedAt(result.getTimestamp("updated_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postModel;
    }
}
