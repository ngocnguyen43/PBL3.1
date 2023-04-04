package com.PBL3.utils.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountMapper implements IMapper<Integer> {
    @Override
    public Integer mapRow(ResultSet result) {
        Integer count = null;
        try {
            count = result.getInt("total");
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
