

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


/**
 * Servlet implementation class bookingdetailsservlet
 */
@WebServlet("/bookingdetailsservlet")
public class bookingdetailsservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookingdetailsservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		String format = "yyyy/mm/dd hh:mm a";
		String check_in_date1= request.getParameter("check_in_date");
		String check_out_date2 = request.getParameter("check_out_date");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		int  hotel_id=Integer.parseInt(request.getParameter("hotel_id"));
		int user_id =Integer.parseInt(request.getParameter("user_id"));
		Date check_in_date = null;
		try {
			check_in_date = sdf.parse(check_in_date1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date check_out_date = null;
		try {
			check_out_date = sdf.parse(check_out_date2);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int price1,price2;
		String total_number_of_pax=request.getParameter("total_number_of_pax");
		String type_of_room=request.getParameter("type_of_room");
		
		//DecimalFormat crunchifyFormatter = new DecimalFormat("###,###");
		
		long diff = check_out_date.getTime() - check_in_date.getTime();
		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		System.out.println("difference between days: " + diffDays);
	int	price= 0;
		
		try 
		{
			
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelwebpage","root","root");
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		ResultSet rs=null;
		String query="select * from price_details where hotel_id=?";
		String q="insert into booking_details(hotel_id,user_id,total_number_of_pax,check_in_date,check_out_date,type_of_room,is_cancelled,price)values(?,?,?,?,?,?,?,?)";
		ps=con.prepareStatement(query);
		ps.setInt(1,hotel_id);
		rs=ps.executeQuery();
		while(rs.next())
		{
			 price1=rs.getInt(3);
			 price2=rs.getInt(4);
			out.print(price1);
			out.print(price2);
			
			if(type_of_room.equals("double deluxe"))
			{
				
				
			}
			
			
		}
		
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
		
	}
	



}