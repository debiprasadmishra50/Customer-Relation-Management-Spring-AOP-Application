package demo.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDBServlet
 */
@WebServlet("/testdb")
public class TestDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// setup connection variables
		String usename = "spring";
		String password = "spring";
		String url = "jdbc:mysql://localhost:3306/spring_mvc_CRMapp?useSSL=false";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		// get connection
		try {
			PrintWriter out = response.getWriter();
			
			out.println("Connecting to Database : "+url);
			
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url,usename,password);
			
			if(con != null)
				out.print("<br><br>Connection Successfull");
			
			con.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

}
