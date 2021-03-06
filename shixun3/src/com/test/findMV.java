package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;

/**
 * Servlet implementation class findMV
 */
@WebServlet("/findMV")
public class findMV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findMV() {
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
		UserDao userDao=new UserDao();
		try {
			ResultSet rs=userDao.queryDate("select * from course where mv_name like '%z%'");
			int count = 0;
			//System.out.println("11");
			PrintWriter writer = response.getWriter();
			writer.write("[");
				while(rs.next()) {
					if (count != 0)
						writer.write(",");
					writer.write("{");
					writer.write("\"id\":");
					writer.write("\'"+rs.getInt(1)+"\'"+",");
					writer.write("\"author\":");
					writer.write("\'"+rs.getInt(2)+"\'"+",");
					writer.write("\"time\":");
					writer.write("\'"+rs.getString(3)+"\'"+",");
					writer.write("\"mv_name\":");
					writer.write("\'"+rs.getString(4)+"\'"+",");
					writer.write("\"mv_account\":");
					writer.write("\'"+rs.getString(5)+"\'"+",");
					writer.write("\"mv_path\":");
					writer.write("\'"+rs.getString(6)+"\'"+",");
					writer.write("\"mv_photo\":");
					writer.write("\'"+rs.getString(7)+"\'"+",");
					writer.write("\"label\":");
					writer.write("\'"+rs.getString(8)+"\'"+",");
					writer.write("}");
					count=1;
				}
			writer.write("]");
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
