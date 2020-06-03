package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;

/**
 * Servlet implementation class shipin
 */
@WebServlet("/shipin")
public class shipin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shipin() {
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
		try {
			UserDao user = new UserDao();
		ResultSet rs = user.queryDate("select * from course");
		int count = 0;
		//System.out.println("11");
		PrintWriter writer = response.getWriter();
		writer.write("[");
			while(rs.next()) {
				if (count != 0)
					writer.write(",");
				writer.write("{");
				writer.write("\"id\":");
				writer.write("\""+rs.getString(1)+"\""+",");
				writer.write("\"author\":");
				writer.write("\""+rs.getString(2)+"\""+",");
        	    writer.write("\"time\":");
				writer.write("\""+rs.getString(3)+"\""+",");
        	    writer.write("\"mv_name\":");
				writer.write("\""+rs.getString(4)+"\""+",");
        	    writer.write("\"mv_account\":");
				writer.write("\""+rs.getString(5)+"\""+",");
				writer.write("\"mv_path\":");
				writer.write("\""+rs.getString(6)+"\""+",");
				writer.write("\"label\":");
				writer.write("\""+rs.getString(7)+"\""+",");
				writer.write("\"mv_pic\":");
				writer.write("\""+rs.getString(8)+"\"");
				writer.write("}");
				count=1;
			}
		writer.write("]");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			((Throwable) e).printStackTrace();
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
