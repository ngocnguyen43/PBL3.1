package com.PBL3.utils.mapper;

import com.PBL3.models.Role;
import com.PBL3.models.User;
import com.PBL3.utils.helpers.CheckFieldExist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements IMapper<User> {
    private boolean withDate = false;
    private boolean withPassword = false;

    public UserMapper() {
    }

    public UserMapper(boolean withDate, boolean withPassword) {
        this.withDate = withDate;
        this.withPassword = withPassword;
    }

    @Override
    public User mapRow(ResultSet result) {
        User user = new User();
        try {
            user.setId(result.getString("user_id"));
//            user.setRoleId(result.getInt("role_id"));
            user.setCompanyId(result.getString("company_id"));
            user.setCompanyName(result.getString("company_name"));
            user.setTaxIndentity(result.getString("tax_identification_number"));
            user.setBusinessId(result.getString("business_id"));
            user.setPhoneNumber(result.getString("phone_number"));
            user.setFaxNumber(result.getString("fax_number"));
            user.setEmail(result.getString("email"));
            user.setFullName(result.getString("full_name"));
            user.setNationalId(result.getString("national_id"));
            user.setUserNumber(result.getString("user_number"));
            if (this.withPassword) user.setPassword(result.getString("password"));
            user.setAction(result.getInt("action"));
            if (this.withDate) {
                user.setModifiedBy(result.getString("modified_by"));
                user.setCreatedAt(result.getTimestamp("created_at"));
                user.setUpdatedAt(result.getTimestamp("updated_at"));
            }
            Role role = new Role();
            role.setRoleId(result.getInt("role_id"));
            role.setRoleCode(result.getString("role_code"));
            role.setRoleName(result.getString("role_name"));
            user.setRole(role);
            if (CheckFieldExist.checkExist(result, "business_name")) {
                user.setBusinessType(result.getString("business_name"));
            }
            return user;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
        return null;

    }

}
