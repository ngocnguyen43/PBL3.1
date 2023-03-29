package com.PBL3.daos.impl;

import com.PBL3.daos.IRoleDAO;
import com.PBL3.models.Role;
import com.PBL3.utils.mapper.RoleMapper;

import java.util.List;

public class RoleDAO extends AbstractDAO<Role> implements IRoleDAO {

    @Override
    public List<Role> findAll() {
        String sql = "SELECT * FROM roles";
//		System.out.println(query(sql, new RoleMapper()));
        return query(sql, new RoleMapper());
    }

    @Override
    public Role findByRoleId(Integer roleId) {
        String sql = "SELECT * FROM roles WHERE roleId = ?";
        List<Role> roles = query(sql, new RoleMapper(), roleId);
        return roles.isEmpty() ? null : roles.get(0);
    }

    @Override
    public void update(String sql, Object... params) {
        // TODO Auto-generated method stub

    }

}
