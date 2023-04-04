package com.PBL3.daos.impl;

import com.PBL3.daos.IKindOfProductDAO;
import com.PBL3.models.KindOfProductModel;
import com.PBL3.utils.mapper.KindOfProductMapper;

import java.util.List;

public class KindOfProductDAO extends AbstractDAO<KindOfProductModel> implements IKindOfProductDAO {
    @Override
    public List<KindOfProductModel> findAll() {
        String sql = "SELECT * FROM kind_of_product ";
        return query(sql, new KindOfProductMapper());
    }

    @Override
    public void save(KindOfProductModel domain) {
        String sql = "INSERT INTO kind_of_product (kindId,name,modified_by) value(?,?,?)";
        insert(sql, domain.getId(), domain.getName(), domain.getModifiedBy());
    }

    @Override
    public KindOfProductModel findOne(String id) {
        String sql = "SELECT * FROM kind_of_product WHERE kindId = ?";
        List<KindOfProductModel> kindOfProductModels = query(sql, new KindOfProductMapper(), id);
        return kindOfProductModels.isEmpty() ? null : kindOfProductModels.get(0);
    }
}
