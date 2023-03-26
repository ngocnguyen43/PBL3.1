package com.PBL3.utils.helpers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CheckFieldExist {
    static public boolean  checkExist(ResultSet result,String name){
        ResultSetMetaData meta = null;
        try {
            meta = result.getMetaData();
            int columnCount = meta.getColumnCount();
            boolean flag = false;
            for (int i = 1; i <=columnCount; i++) {
            if(meta.getColumnName(i).equals(name)) {
                flag = true;
            }
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
