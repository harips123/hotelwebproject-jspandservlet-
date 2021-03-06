

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class hotelloginservlet
 */
@WebServlet("/hotelloginservlet")
public class hotelloginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hotelloginservlet() {
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
		String email=request.getParameter("email");
		String passwords=request.getParameter("passwords");
		int hotel_id;
		String hotel_name,user_name,star_classification,contact_number,state;
		PrintWriter out= response.getWriter();
		try
		{
			   SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");  
			    Date date = new Date(); 
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelwebpage","root","root");
			String query="select hd.*,ld.login_dateandtime from hotel_details hd,login_details ld where hd.email = ? and hd.passwords = ?  order by ld.login_dateandtime desc LIMIT 1,1  ";
			String  s="insert into login_details(user_name_or_hotel_email_id,type_of_user,login_dateandtime)values(?,?,?)";
			PreparedStatement pst=(PreparedStatement)con.prepareStatement(s);
			pst.setString(1, email);
			pst.setInt(2, 2);
			pst.setString(3,formatter.format(date));
			
			pst.executeUpdate();
			
			PreparedStatement st=(PreparedStatement)con.prepareStatement(query); 
			//Statement st= con.createStatement();
			st.setString(1, email);
			st.setString(2, passwords);
			//st.setString(3, email);
			ResultSet rs;
			rs=st.executeQuery();
			
		if(rs.next())
		{ 	
			hotel_id=rs.getInt(1);
			hotel_name=rs.getString(2);
			user_name=rs.getString(4);
			star_classification=rs.getString(6);
			state=rs.getString(7);
			contact_number=rs.getString(8);
			String dateandtime=rs.getString(9);
			
			HttpSession session =request.getSession();
			session.setAttribute("email",email);
			session.setAttribute("hotel_id", hotel_id);
			session.setAttribute("hotel_name", hotel_name);
			session.setAttribute("user_name", user_name);
			session.setAttribute("star_classification", star_classification);
			session.setAttribute("state", state);
			session.setAttribute("contact_number", contact_number);
			session.setAttribute("dateandtime", dateandtime);
			
			//System.out.print(hotel_id);
	response.sendRedirect("hotelwelcome.jsp");
		} else {
		    out.print("Invalid password <a href='hoteloginpage.jsp'>try again</a>");
		}
		}
		catch(Exception e)
		{
			out.print(e);
		}
	}

}
