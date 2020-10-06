package progr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.postgresql.util.PSQLException;

/**
 * Servlet implementation class AddContAdmin
 */
@WebServlet("/AddContAdmin")
public class AddContAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PreparedStatement prepared_stmt= null;
	    int affectedrows=0;
	    Connection c = null;
	    String username = request.getParameter("username");
	    PrintWriter out = response.getWriter();
	    
	    try {
	    	c = DriverManager
	        		.getConnection("jdbc:postgresql://localhost:5432/WEB_programming",
	                        "postgres", "12345678910");
	       
			String sql="UPDATE users SET usertype=2 WHERE username=?";
			prepared_stmt = c.prepareStatement(sql);
			prepared_stmt.setString(1, username);
			affectedrows = prepared_stmt.executeUpdate();
			
			if (affectedrows==0)throw  new Exception();
			out.println("<html><body><h1> Successfull!</h1> <br><form action='Admin'><input type='submit'></form></body></html>");
			
			
	    }catch(Exception e) {
	    	response.sendRedirect("failAdd.jsp");
	    	
	    }
	}
  
}
