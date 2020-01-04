package com.deloitte.dept.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deloitte.dept.beans.Dept;
import com.deloitte.dept.dao.DeptDao;
import com.deloitte.dept.utils.DeptUtils;

import oracle.jdbc.OracleDriver;

public class DeptDaoImpl implements DeptDao{
	private Connection conn=null;
	private PreparedStatement pst;
	private ResultSet rs;
	@Override
	public void openConnection() {
		try {
			DriverManager.registerDriver(new OracleDriver());
			String url ="jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pass = "tiger";
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	@Override
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int addDept(Dept dept) {
		openConnection();
		int rows=0;
		try {
			pst = conn.prepareStatement(DeptUtils.insertqry);
			pst.setInt(1, dept.getDeptno());
			pst.setString(2, dept.getDeptname());
			pst.setString(3, dept.getLocation());
			
			rows= pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			close();
		}
		return rows;


    }
	@Override
	public List<Dept> getDeptls() {
		openConnection();
		List<Dept> deptlist = new ArrayList<Dept>();
		try {
			pst = conn.prepareStatement(DeptUtils.GETDEPT);
			rs = pst.executeQuery();
			while(rs.next()){
				Dept dept = new Dept(rs.getInt(1), rs.getString(2),rs.getString(3));
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return deptlist;
	}
	@Override
	public int deleteDept(int deptno){
		openConnection();
		int rows=0;
		try{
			pst=conn.prepareStatement(DeptUtils.DELETEDEPTBYNO);
			pst.setInt(1, deptno);
			rows=pst.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
		}
		finally{
			close();
		}
		return rows;
	}

}

