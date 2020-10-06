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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BookAction
 */
@WebServlet("/BookAction")
public class BookAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PreparedStatement prepared_stmt = null, pr1 = null, pr2 = null,pr3=null;
		ResultSet rs = null, rs1 = null,rs2=null,rs3=null;
		Connection c = null;
		String sql, sql2, sql1,sql3,sql4;
		int affectedrows = 0, ticket_id = 0, userid = 0,takenSeats=0,totalSeats=0;
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		PrintWriter out = response.getWriter();
		try {

			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/WEB_programming", "postgres",
					"12345678910");

			
			sql4="select seats from (schedule inner join rooms on schedule.room_id=rooms.idroom) where schedule_id=?";
			pr3=c.prepareStatement(sql4);
			pr3.setInt(1,Integer.parseInt(id));
			rs3=pr3.executeQuery();
			
			while(rs3.next()) {
				totalSeats=rs3.getInt(1);
			}
			
			sql3="select count(scheduleid) \r\n" + 
					"from tickets inner join schedule on tickets.scheduleid= schedule.schedule_id\r\n" + 
					"where scheduleid =?";
			pr2=c.prepareStatement(sql3);
			pr2.setInt(1,Integer.parseInt(id));
			rs2=pr2.executeQuery();

			while(rs2.next()) {
				
				takenSeats=rs2.getInt(1);
				
			}
			
			System.out.println(totalSeats-takenSeats);
			if(totalSeats-takenSeats>0) {
				
			
			// id generation
			sql = "SELECT max(idtickets) FROM tickets";
			prepared_stmt = c.prepareStatement(sql);
			rs = prepared_stmt.executeQuery();

			while (rs.next()) {
				ticket_id = rs.getInt(1);
			}
			
			// find userid
			sql1 = "Select userid from users where username=? ";
			pr1 = c.prepareStatement(sql1);
			pr1.setString(1, session.getAttribute("username").toString());
			rs1 = pr1.executeQuery();
			
	
			while (rs1.next()) {
				
				userid = rs1.getInt(1);
				
			}
			

			// insert to book a movie
			sql2 = "INSERT INTO tickets VALUES(?,?,?,?)";
			pr2 = c.prepareStatement(sql2);
			pr2.setInt(1, ticket_id + 1);
			pr2.setInt(2, 7);
			pr2.setInt(3, userid);
			pr2.setInt(4, Integer.parseInt(id));
			affectedrows = pr2.executeUpdate();
			
			out.println("<html><body><h1>Successful Booking!</h1><form action=\"successful.jsp\">\r\n"
					+ "  <input type=\"submit\" value = \"Go back!\">\r\n" + "</form></body></html>");
			}else {
				out.println("<html><body><h1>Not available seats!</h1><form action=\"successful.jsp\">\r\n"
						+ "  <input type=\"submit\" value = \"Go back!\">\r\n" + "</form></body></html>");
			
			}
		} catch (Exception e) {
			System.out.print(e);
			out.println("<html><body><h1>Something went wrong!</h1><form action=\"successful.jsp\">\r\n"
					+ "  <input type=\"submit\" value = \"Go back!\">\r\n" + "</form></body></html>");
		}
	}
}
