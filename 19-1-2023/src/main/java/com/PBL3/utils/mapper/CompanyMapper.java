package com.PBL3.utils.mapper;

import com.PBL3.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper implements IMapper<User> {
    @Override
    public User mapRow(ResultSet result) {
        User user = new User();
        try {
            user.setCompanyId(result.getString("company_id"));
            user.setCompanyName(result.getString("company_name"));
            user.setId(result.getString("user_id"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
