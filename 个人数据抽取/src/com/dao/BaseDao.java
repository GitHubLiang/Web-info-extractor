package com.dao;

import static java.sql.DriverManager.getConnection;

import java.rmi.Remote;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���ݿ��������
 *  
 */
public abstract class BaseDao implements Remote {
    // ���ݿ���������
    private static String driver = "com.mysql.jdbc.Driver";
    // ���ݿ����·��
    private String dbUrl = "jdbc:mysql://localhost:3306";
    // �������ݿ���˺�
    private String dbUser = "root";
    // �������ݿ������
    private String dbPass = "ymzmdx";
    
    /**
     * �������ݿ�
     * 
     * @return ���ݿ����Ӷ���
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
     * �ر�SQLָ����������ݿ����Ӷ���
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
     * �ر�SQLָ�����
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
     * �ر����ݿ�����
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
