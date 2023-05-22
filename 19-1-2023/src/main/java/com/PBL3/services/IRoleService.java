package com.PBL3.services;

import com.PBL3.models.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    Role findByRoleID(Integer roleId);
}
