/**
 * 
 */
package com.deloitte.dept.dao;

import java.util.List;

import com.deloitte.dept.beans.Dept;

/**
 * @author admin
 *
 */
public interface DeptDao {
	void openConnection();
	void close();
	int addDept(Dept dept); 
	int deleteDept(int deptno);
	List<Dept> getDeptls();
	//Dept getDeptByCode(int deptno);

}
