

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


/**
 * Servlet implementation class resetpasswordservlet
 */
@WebServlet("/resetpasswordservlet")
public class resetpasswordservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resetpasswordservlet() {
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
		// TODO Auto-generated method stub
		
		String user_name = request.getParameter("user_name");
		String reg_number = request.getParameter("reg_number");
		String reset_pass = request.getParameter("reset_pass");
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelwebpage","root","root");
			String query = "update user_details set passwords= ? where user_name= ? and contact_number = ? ";
			PreparedStatement ps= null;
			ps=con.prepareStatement(query);
			
			ps.setString(1,reset_pass );
			ps.setString(2, user_name);
			ps.setString(3, reg_number);
			
			ps.executeUpdate();
			
			response.sendRedirect("loginpage.jsp");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
