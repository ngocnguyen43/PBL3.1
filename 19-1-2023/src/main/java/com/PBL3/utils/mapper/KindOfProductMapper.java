package com.PBL3.utils.mapper;

import com.PBL3.models.KindOfProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KindOfProductMapper implements  IMapper<KindOfProductModel>{
    @Override
    public KindOfProductModel mapRow(ResultSet result) {
        KindOfProductModel kindOfProduct = new KindOfProductModel();
        try{
            kindOfProduct.setId(result.getString("kindId"));
            kindOfProduct.setName(result.getString("name"));
            kindOfProduct.setModifiedBy(result.getString("modified_by"));
            kindOfProduct.setCreatedAt(result.getTimestamp("created_at"));
            kindOfProduct.setCreatedAt(result.getTimestamp("updated_at"));
            return kindOfProduct;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
