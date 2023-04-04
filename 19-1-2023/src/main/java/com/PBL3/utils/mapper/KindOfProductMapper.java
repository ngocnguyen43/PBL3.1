package com.PBL3.utils.mapper;

import com.PBL3.models.KindOfProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KindOfProductMapper implements IMapper<KindOfProductModel> {
    private boolean withDate = false;

    public KindOfProductMapper() {
    }

    public KindOfProductMapper(boolean withDate) {
        this.withDate = withDate;
    }

    @Override
    public KindOfProductModel mapRow(ResultSet result) {
        KindOfProductModel kindOfProduct = new KindOfProductModel();
        try {
            kindOfProduct.setId(result.getString("kindId"));
            kindOfProduct.setName(result.getString("name"));
            if (this.withDate) {
                kindOfProduct.setModifiedBy(result.getString("modified_by"));
                kindOfProduct.setUpdatedAt(result.getTimestamp("updated_at"));
                kindOfProduct.setCreatedAt(result.getTimestamp("created_at"));
            }
            return kindOfProduct;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
