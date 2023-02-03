package com.PBL3.mapper;

import java.sql.ResultSet;

import com.PBL3.model.Role;

public class RoleMapper implements IMapper<Role> {

	@Override
	public Role mapRow(ResultSet result) {
		// TODO Auto-generated method stub
		Role role = new Role();
		try {
			role.setRoleId(result.getInt("roleId"));
			role.setRoleCode(result.getString("rolecode"));
			role.setRoleName(result.getString("rolename"));
//			role.setModifiedBy(result.getString("modifiedBy"));
//			role.setCreatedAt(result.getTimestamp("createdAt"));
//			role.setUpdatedAt(result.getTimestamp("updatedAt"));
			return role;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
