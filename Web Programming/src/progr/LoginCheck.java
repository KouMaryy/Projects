package progr;

import java.io.IOException;
import java.security.MessageDigest;
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
import javax.xml.bind.DatatypeConverter;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
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
        ResultSet rs=null,rs1=null;
        Connection c = null;
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String user_name=null,salt=null,pass_word=null;
	int usertype=6,isActive=3;
	
	HttpSession session = request.getSession();
	try{
		
		
        c = DriverManager
        		.getConnection("jdbc:postgresql://localhost:5432/WEB_programming",
                        "postgres", "12345678910");
       
		String sql="SELECT * FROM USERS WHERE username=?";
		prepared_stmt = c.prepareStatement(sql);
		prepared_stmt.setString(1, username);
		rs = prepared_stmt.executeQuery();

		while(rs.next()){
		salt=rs.getString(7);
		}
		
		
		 MessageDigest md = MessageDigest.getInstance("MD5");
		 md.update((password + salt).getBytes());
		 byte[] digest = md.digest();
		 String newpass = DatatypeConverter.printHexBinary(digest).toUpperCase();
		 
		 String sql1="SELECT * FROM USERS WHERE username=? AND userpassword=?";
		 prepared_stmt = c.prepareStatement(sql1);
		 prepared_stmt.setString(1, username);
		 prepared_stmt.setString(2, newpass);
		 rs1 = prepared_stmt.executeQuery();

		 while(rs1.next()) {
			 user_name=rs1.getString(3);
			 usertype=rs1.getInt(2);
			 isActive=rs1.getInt(6);
			 
			 if (isActive==1)throw new Exception();
		 }
		 session.setAttribute("username", user_name);
		 session.setAttribute("usertype", usertype);
		 session.setAttribute("isActive", isActive);
		 
		 
		if (user_name==null) {
			throw new Exception();
		}else if(usertype==1){
			response.sendRedirect("admin.jsp");
		}
		else if(usertype==2 ) {
		response.sendRedirect("contadmin.jsp");
		
		}else if(usertype==3) {
			response.sendRedirect("successful.jsp");
		}




		}catch(Exception e){
		 System.out.println(e);	
		 response.sendRedirect("fail.jsp");
		  return;
		}
	}

}
