package com.PBL3.daos.impl;

import com.PBL3.daos.IBusinessTypesDAO;
import com.PBL3.models.BusinessTypes;
import com.PBL3.utils.mapper.BusinessTypesMapper;

import java.util.List;

public class BusinessTypesDAO extends AbstractDAO<BusinessTypes> implements IBusinessTypesDAO {

    @Override
    public String save(BusinessTypes businessTypes) {
        String sql = "INSERT INTO business_types (type_id,business_id,"
                + "type_name,"
                + "modified_by) VALUES (?,?,?,?)";
        insert(sql, businessTypes.getId(), businessTypes.getBusinessId(), businessTypes.getTypeName(), businessTypes.getModifiedBy());
        return businessTypes.getId();
    }

    @Override
    public BusinessTypes findOne(String name) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM login.business_types WHERE login.business_types.type_name = ?";
        List<BusinessTypes> businessTypes = query(sql, new BusinessTypesMapper(), name);
        return businessTypes.isEmpty() ? null : businessTypes.get(0);
    }


    @Override
    public List<BusinessTypes> FindAll() {
        String sql = "SELECT * FROM login.business_types";
        return query(sql, new BusinessTypesMapper());
    }
}
