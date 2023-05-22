package com.PBL3.daos.impl;

import com.PBL3.config.EnvConfig;
import com.PBL3.daos.GenericDAO;
import com.PBL3.utils.mapper.IMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> implements GenericDAO<T> {
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + EnvConfig.load().get("DB_URL") + "/" + EnvConfig.load().get("DB_NAME");
            return DriverManager.getConnection(url, EnvConfig.load().get("USER_NAME"), EnvConfig.load().get("PASSWORD"));
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> List<T> query(String sql, IMapper<T> mapper, Object... parameters) {
        List<T> list = new ArrayList<T>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = getConnection();
            statement = connection.prepareCall(sql);
            // set parameter
            setParams(statement, parameters);
            result = statement.executeQuery();
            while (result.next()) {
                list.add(mapper.mapRow(result));
            }
            return list;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
                return null;
            }
        }
    }

    private void setParams(PreparedStatement statement, Object... parameters) {
        try {

            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long) {
                    statement.setLong(index, (Long) parameter);
                } else if (parameter instanceof String) {
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    statement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Timestamp) {
                    statement.setTimestamp(index, (Timestamp) parameter);
                } else {
                    statement.setNull(index, Types.NULL);
                }

            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    public void update(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParams(statement, params);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    @Override
    public void insert(String sql, Object... params) {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParams(statement, params);
            statement.executeUpdate();
            connection.commit();
//			return params[0];

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParams(statement, params);
            statement.executeUpdate();
            connection.commit();
//			return params[0];

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
