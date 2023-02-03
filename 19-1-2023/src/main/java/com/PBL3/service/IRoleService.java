package com.PBL3.service;

import java.util.List;

import com.PBL3.model.Role;

public interface IRoleService {
	List<Role> findAll();
	Role findByRoleID(Integer roleId );
}
