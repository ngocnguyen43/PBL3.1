package com.PBL3.DAO;

import java.util.List;

import com.PBL3.model.Role;

public interface IRoleDAO extends GenericDAO<Role> {
	List<Role> findAll();

	Role findByRoleId(Integer roleId);
}
