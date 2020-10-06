package progr;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PreparedStatement prepared_stmt = null;
		ResultSet rs = null, rs1 = null, rs2 = null;
		Connection c = null;
		String sql, sql1, sql2;
		int affectedrows = 0;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String uname = request.getParameter("uname");

		// int salt = 0;
		String user_name = null;
		PrintWriter out = response.getWriter();
		int user_id = 0;
		String salt = null;

		Random rand = new Random();
		try {

			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/WEB_programming", "postgres",
					"12345678910");

			// id generation
			sql = "SELECT max(userid) FROM Users";
			prepared_stmt = c.prepareStatement(sql);
			rs = prepared_stmt.executeQuery();

			while (rs.next()) {
				user_id = rs.getInt(1);
			}

			// user check
			sql1 = "SELECT * FROM USERS WHERE username=?";
			prepared_stmt = c.prepareStatement(sql1);
			prepared_stmt.setString(1, username);
			rs1 = prepared_stmt.executeQuery();

			while (rs1.next()) {
				user_name = rs.getString(3);

			}

			// salt for password
			salt = Integer.toString(rand.nextInt(9999));
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update((password + salt).getBytes());
			byte[] digest = md.digest();
			String newpass = DatatypeConverter.printHexBinary(digest).toUpperCase();

			// insert user
			sql2 = "INSERT INTO USERS VALUES(?,?,?,?,?,?,?)";
			prepared_stmt = c.prepareStatement(sql2);
			prepared_stmt.setInt(1, user_id + 1);
			prepared_stmt.setInt(2, 3);
			prepared_stmt.setString(3, username);
			prepared_stmt.setString(4, uname);
			prepared_stmt.setString(5, newpass);
			prepared_stmt.setInt(6, 0);
			prepared_stmt.setString(7, salt);

			affectedrows = prepared_stmt.executeUpdate();
			response.sendRedirect("index.jsp");

		} catch (Exception e) {
			// user already exists
			out.println("<html><body><h1>User already exists!</h1><form action=\"register_page.jsp\">\r\n"
					+ "  <input type=\"submit\" value = \"Register\">\r\n" + "</form></body></html>");
		}
	}
}
