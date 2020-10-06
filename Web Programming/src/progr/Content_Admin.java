package progr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Content_Admin
 */
@WebServlet("/Content_Admin")
public class Content_Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Content_Admin() {
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
		ResultSet rs=null;
		int affectedrows1=0;
		
 
        Connection c = null;
    	String name = request.getParameter("movie_name");
    	String category = request.getParameter("movie_category");
    	String duration = request.getParameter("movie_duration");
    	String desc = request.getParameter("movie_description");
    	int rate = Integer.parseInt(request.getParameter("movie_rate"));
    	try{
    		
    		HttpSession session = request.getSession();
    		String str = session.getAttribute("username").toString();
    		System.out.println(str);
    		
        	c =DriverManager
            		.getConnection("jdbc:postgresql://localhost:5432/WEB_programming",
                            "postgres", "12345678910");
        	

        	}catch(Exception e){

        	System.out.println(e);
        	}
    
        try{
        	

        	String sql_1="INSERT INTO Movie VALUES(?,?,?,?,?)";
        	prepared_stmt = c.prepareStatement(sql_1);
        	prepared_stmt.setString(1, name);
        	prepared_stmt.setString(2, category);
        	prepared_stmt.setString(3, duration);
        	prepared_stmt.setString(4, desc);
        	prepared_stmt.setInt(5, rate);
        	affectedrows1 = prepared_stmt.executeUpdate();  
        	response.sendRedirect("sucInsert.jsp");
        	

        	}
        catch(Exception e){System.out.println(e);}
        
       
        
          
	}

	}


