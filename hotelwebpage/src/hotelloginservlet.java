

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

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
		PrintWriter out= response.getWriter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelwebpage","root","root");
			String query="select * from hotel_details where email = ? and passwords = ?  ";
			PreparedStatement st=(PreparedStatement)con.prepareStatement(query); 
			//Statement st= con.createStatement();
			st.setString(1, email);
			st.setString(2, passwords);
			ResultSet rs;
			rs=st.executeQuery();
			
		if(rs.next())
		{ 	
			hotel_id=rs.getInt(1);
			HttpSession session =request.getSession();
			session.setAttribute("email",email);
			session.setAttribute("hotel_id", hotel_id);
		//	session.setAttribute("", value);
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
