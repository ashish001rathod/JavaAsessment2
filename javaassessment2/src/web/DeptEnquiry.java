package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deloitte.dept.beans.Dept;
import com.deloitte.dept.dao.DeptDao;
import com.deloitte.dept.dao.impl.DeptDaoImpl;

@WebServlet("/DeptEnquiry")
public class DeptEnquiry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		String deptname = request.getParameter("deptname");
		String location = request.getParameter("location");
		
		Dept dept = new Dept(deptno,deptname,location);
		DeptDao dao =new DeptDaoImpl();
		int rows= dao.addDept(dept);
		out.println(rows+" row Inserted");
	}

}
