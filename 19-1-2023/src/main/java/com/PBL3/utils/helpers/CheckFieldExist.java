package com.PBL3.utils.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckFieldExist {
    static public boolean checkExist(ResultSet result, String name) {
        try {
            result.findColumn(name);
            return true;
        } catch (SQLException e) {
//            e.printStackTrace();
            return false;
        }
    }
}
