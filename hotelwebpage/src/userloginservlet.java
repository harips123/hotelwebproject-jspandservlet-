

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.sql.*;
import java.text.SimpleDateFormat;
/**
 * Servlet implementation class userloginservlet
 */
@WebServlet("/userloginservlet")
public class userloginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userloginservlet() {
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
		String user_name=request.getParameter("user_name");
		String passwords=request.getParameter("passwords");
		int user_id ;
		String name,email,contact_number;
		PrintWriter out = response.getWriter();
		

		
		
		
		try
		{
			
		    SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");  
		    Date date = new Date(); 
		    
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelwebpage","root","root");
			String query="select ud.* ,ld.login_dateandtime from user_details ud ,login_details ld where user_name = ? and passwords = ? order by ld.login_dateandtime desc LIMIT 1,1";
			String  s="insert into login_details(user_name_or_hotel_email_id,type_of_user,login_dateandtime)values(?,?,?)";
			PreparedStatement pst=(PreparedStatement)con.prepareStatement(s);
			pst.setString(1, user_name);
			pst.setInt(2, 1);
			pst.setString(3,formatter.format(date));
			
			pst.executeUpdate();
			
			PreparedStatement st=(PreparedStatement)con.prepareStatement(query); 
			//Statement st= con.createStatement();
			st.setString(1, user_name);
			st.setString(2, passwords);
			ResultSet rs;
			rs=st.executeQuery();
			
		if(rs.next())
		{
			user_id=rs.getInt(1);
			name=rs.getString(2);
			email=rs.getString(3);
			contact_number=rs.getString(7);
			String dateandtime=rs.getString(8);
			
			HttpSession session = request.getSession();
			session.setAttribute("user_name",user_name);
			session.setAttribute("user_id", user_id);
			session.setAttribute("name", name);
			session.setAttribute("email", email);
			session.setAttribute("contact_number", contact_number);
			session.setAttribute("dateandtime", dateandtime);
//System.out.println(user_id);
//System.out.println(user_name);
//System.out.println(name);
//System.out.println(email);
//System.out.println(contact_number);
		response.sendRedirect("userwelcome.jsp");
		} 
		else {
			out.println("<html>");
		    out.println("Invalid password or id <a href=loginpage.jsp>try again</a>");
		    out.println("</html>");
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	


}
