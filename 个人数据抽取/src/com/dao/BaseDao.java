package com.dao;

import static java.sql.DriverManager.getConnection;

import java.rmi.Remote;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库操作基类
 *  
 */
public abstract class BaseDao implements Remote {
    // 数据库驱动名称
    private static String driver = "com.mysql.jdbc.Driver";
    // 数据库访问路径
    private String dbUrl = "jdbc:mysql://localhost:3306";
    // 访问数据库的账号
    private String dbUser = "root";
    // 访问数据库的密码
    private String dbPass = "ymzmdx";
    
    /**
     * 连接数据库
     * 
     * @return 数据库连接对象
     * @throws SQLException
     */
    protected Connection getConn() throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException e) {
            if (e.getMessage().contains("No suitable driver")) {
                try {
                    Class.forName(driver);
                    connection = getConnection(dbUrl, dbUser, dbPass);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }
    
    /**
     * 关闭SQL指令对象与数据库连接对象
     * 
     * @param ps
     */
    protected void closeStatementAndConnection(Statement ps, Connection conn) {
        try {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 关闭SQL指令对象
     * 
     * @param ps
     */
    protected void closeStatement(Statement ps) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 关闭数据库连接
     * 
     * @param conn
     */
    protected void closeConn(Connection conn) {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
