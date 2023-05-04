package com.PBL3.daos.impl;

import com.PBL3.daos.IBusinessDAO;
import com.PBL3.models.Business;
import com.PBL3.utils.mapper.BusinessMapper;

import java.util.List;

public class BusinessDAO extends AbstractDAO<Business> implements IBusinessDAO {

    @Override
    public String save(Business business) {
        // TODO Auto-generated method stub
        System.out.println(business.getBusinessName());
        String sql = "INSERT INTO business (business_id,business_name,modified_by) value(?,?,?)";
        insert(sql, business.getId(), business.getBusinessName(), business.getModifiedBy());
        return business.getId();
    }

    @Override
    public Business findOne(String name) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM business WHERE business_name = ?";
        List<Business> businesses = query(sql, new BusinessMapper(), name);
        return businesses.isEmpty() ? null : businesses.get(0);
    }

    @Override
    public Business findOneById(String id) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM business WHERE business_id = ?";
        List<Business> businesses = query(sql, new BusinessMapper(), id);
        return businesses.isEmpty() ? null : businesses.get(0);
    }

    @Override
    public List<Business> findAll() {
        String sql = "SELECT * FROM login.business";
        return query(sql, new BusinessMapper());
    }

}
