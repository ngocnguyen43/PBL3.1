package com.PBL3.daos.impl;

import com.PBL3.daos.IPostDAO;
import com.PBL3.models.PostModel;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.mapper.PostMapper;

import java.util.List;

public class PostDAO extends AbstractDAO<PostModel> implements IPostDAO {
    @Override
    public void Create(PostModel postModel) {
        String sql = "INSERT INTO login.posts (post_id,user_id,isIncognito,phone_number,type_id,title,content,action) VALUES (?,?,?,?,?,?,?,?)";
        insert(sql, postModel.getId(), postModel.getUserId(), postModel.getIsIncognito(), postModel.getPhoneNUmber(), postModel.getTypeId(), postModel.getTitle(), postModel.getContent(), postModel.getAction());
    }

    @Override
    public List<PostModel> GetAll() {
        String sql = "SELECT login.posts.post_id,login.posts.user_id," +
                "login.posts.isIncognito," +
                "login.posts.phone_number," +
                "login.posts.type_id," +
                "login.posts.title," +
                "login.posts.content," +
                "login.posts.action," +
                "login.post_types.type_id," +
                "login.post_types.type_name FROM login.posts " +
                "INNER JOIN login.post_types ON login.post_types.type_id = login.posts.type_id " +
                "ORDER BY created_at ";
        return query(sql, new PostMapper());
    }

    @Override
    public void SetAction(String id) throws NotFoundException {
        PostModel model = this.GetById(id);
        if (model == null) {
            throw new NotFoundException("Post Not Found");
        }
        String sql = "UPDATE login.posts SET posts.action = ?"
                + " WHERE post_id = ?";
        update(sql, model.getAction() == 1 ? 0 : 1, id);
    }

    @Override
    public PostModel GetById(String id) {
        String sql = "SELECT login.posts.post_id,login.posts.user_id," +
                " login.posts.isIncognito," +
                " login.posts.phone_number," +
                " login.posts.type_id," +
                " login.posts.title," + " login.posts.action," +
                " login.posts.content," +
                " login.post_types.type_id," +
                " login.post_types.type_name FROM login.posts " +
                " INNER JOIN login.post_types ON login.post_types.type_id = login.posts.type_id WHERE post_id = ?";
        List<PostModel> postModels = query(sql, new PostMapper(), id);
        return postModels.isEmpty() ? null : postModels.get(0);
    }
}
