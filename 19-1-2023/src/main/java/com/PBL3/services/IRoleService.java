package com.PBL3.services;

import java.util.List;

import com.PBL3.models.Role;

public interface IRoleService {
	List<Role> findAll();
	Role findByRoleID(Integer roleId );
}
