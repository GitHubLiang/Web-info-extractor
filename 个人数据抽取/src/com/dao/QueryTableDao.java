package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;
  
import com.entity.*;
import com.dao.MySQLJdbc;   
import com.exception.TableIDException;

/**
 * 图书计划数据库操作类 
 */
public class QueryTableDao extends SuperDao {
    
    public int deleteDetails(String perName,TableList details)
            throws TableIDException { 
        String personnelSql = "delete from "+perName+"TableList where tableName=?";
        Connection conn = null;
        PreparedStatement ps = null;
        int id = 0;
        try {
            conn = MySQLJdbc.getConnection();
            ps = conn.prepareStatement(personnelSql);
            ps.setString(1, details.getTableName());
            id = ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	destoryResource();
        }
        return id;
    } 
    public int InsertOrUpdateDetails(String perName,TableList details) {
        String personnelSql;
        if (details.getTableName() == null)
            personnelSql = "insert into "+perName+"TableList values(book_id,unit,writer,type,content,page_number,stat_number,start_date,end_Date,work_day,progress,current_content,remark) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        else
            personnelSql = "update "+perName+"TableList set tableName=?,date=?,remark=?";
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = MySQLJdbc.getConnection();
            ps = conn.prepareStatement(personnelSql);
            ps.setString(1, details.getTableName());
            ps.setString(2, details.getDate());
            ps.setString(3, details.getRemark()); 
            rows = ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	destoryResource();
        }
        return rows;
    } 
    /**
     * 搜索指定人员指定日期段的所有个人表格(TableList)
     * 
     * @param perId
     *            人员编号
     * @param from
     *            起始日期
     * @param to
     *            结束日期
     * @return
     */
    public List<TableList> searchDetails(String perName, Date from,Date to) {
        List<TableList> list = new ArrayList<TableList>();
        String sql = "select * from "+perName+"TableList "+"where (date between ? and ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MySQLJdbc.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, from);
            ps.setDate(2, to); 
            ResultSet rs = ps.executeQuery();
            loadDetailsBean(list, rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	destoryResource();
        }
        return list;
    }
    
    /**
     * 获取指定某天的工作计划
     * 
     * @param perId
     * @param from
     * @param to
     * @return
     */
    public List<TableList> listDayDetails(String perName, Date day) {
        List<TableList> list = new ArrayList<TableList>();
        String sql = "select * from "+perName+"TableList where (date between ? and ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MySQLJdbc.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, day);
            ps.setDate(2, day); 
            ResultSet rs = ps.executeQuery();
            loadDetailsBean(list, rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	destoryResource();
        }
        return list;
    }
    
    private void loadDetailsBean(List<TableList> list,
            ResultSet rs) throws SQLException {
        while (rs.next()) {
            TableList details = new TableList();
            details.setTableName(rs.getString("tableName"));
            details.setDate(rs.getString("date"));
            details.setRemark(rs.getString("remark"));
            list.add(details);
        }
    }
}
