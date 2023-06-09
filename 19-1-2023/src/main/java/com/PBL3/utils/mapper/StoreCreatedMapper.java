package com.PBL3.utils.mapper;

import com.PBL3.models.StatsModel;

import java.sql.ResultSet;

public class StoreCreatedMapper implements IMapper<StatsModel> {
    @Override
    public StatsModel mapRow(ResultSet result) {
        StatsModel model = new StatsModel();
        try {
            model.setDate(result.getString("date"));
            model.setValue(result.getInt("total"));
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
