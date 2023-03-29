package com.PBL3.daos;

import com.PBL3.models.Role;

import java.util.List;

public interface IRoleDAO extends GenericDAO<Role> {
    List<Role> findAll();

    Role findByRoleId(Integer roleId);
}
