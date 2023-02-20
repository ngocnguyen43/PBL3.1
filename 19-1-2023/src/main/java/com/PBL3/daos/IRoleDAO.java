package com.PBL3.daos;

import java.util.List;

import com.PBL3.models.Role;

public interface IRoleDAO extends GenericDAO<Role> {
	List<Role> findAll();

	Role findByRoleId(Integer roleId);
}
