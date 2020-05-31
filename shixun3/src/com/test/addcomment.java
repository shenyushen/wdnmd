package com.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;

/**
 * Servlet implementation class addcomment
 */
@WebServlet("/addcomment")
public class addcomment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addcomment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id=request.getParameter("mvcomment_id");
		int mvcomment_id=Integer.parseInt(id==null || "".equals(id)?"0":id);
		String tep=request.getParameter("author");
		int author=Integer.parseInt(tep==null || "".equals(tep)?"0":tep);
		String content=request.getParameter("content");
		String mvcomment_timeString=request.getParameter("mvcomment_time");
		String temp=request.getParameter("mv_id");
		int mv_id=Integer.parseInt(temp==null || "".equals(temp)?"0":temp);
		UserDao com=new UserDao();
		try {
			com.addData("Insert into mv_comment values('"+mvcomment_id+"','"+author+"','"+content+"','"+mvcomment_timeString+"','"+mv_id+"')");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
