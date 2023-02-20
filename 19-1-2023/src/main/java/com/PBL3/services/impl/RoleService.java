package com.PBL3.services.impl;

import java.util.List;

import javax.inject.Inject;

import com.PBL3.daos.IRoleDAO;
import com.PBL3.models.Role;
import com.PBL3.services.IRoleService;

public class RoleService implements IRoleService {

	@Inject
	private IRoleDAO roleDao;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}

	@Override
	public Role findByRoleID(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.findByRoleId(roleId);
	}

}
