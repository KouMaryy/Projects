package progr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateAction
 */
@WebServlet("/UpdateAction")
public class UpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAction() {
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
	    String movie_name = request.getParameter("movie_name");
	    String roomid = request.getParameter("roomid");
	    int room_id = Integer.parseInt(roomid);
	    String time = request.getParameter("time");
	    String date = request.getParameter("date");
	    PrintWriter out = response.getWriter();
	    int schedule_id= 0;
	    ResultSet rs = null;
	    
	    try {
	    	c = DriverManager
	        		.getConnection("jdbc:postgresql://localhost:5432/WEB_programming",
	                        "postgres", "12345678910");
	       
	    	String sql="SELECT max(schedule_id) FROM Schedule";	     
	    	prepared_stmt = c.prepareStatement(sql);
	    	rs = prepared_stmt.executeQuery();
	    	while(rs.next()) {
	    		schedule_id=rs.getInt(1);
	    	}

    	
    	String sql_2="INSERT INTO schedule VALUES(?,?,?,?,?)";       	
      	prepared_stmt = c.prepareStatement(sql_2);
      	prepared_stmt.setInt(1, schedule_id+1);
      	prepared_stmt.setString(2, movie_name);
      	prepared_stmt.setInt(3, room_id);
      	prepared_stmt.setString(4, time);
      	prepared_stmt.setString(5, date);

      	affectedrows = prepared_stmt.executeUpdate(); 
			
			if (affectedrows==0)throw  new Exception();
			out.println("<html><body><h1> Successfull!</h1> <br><form action='Schedule'><input type='submit' value='Continue'></form></body></html>");
			
			
	    }catch(Exception e) {
	    	out.println("<html><body><h1>Unsuccessful!</h1> <br><form action='Schedule'><input type='submit' value='Go Back'></form></body></html>");
	    	
	    }}

}
