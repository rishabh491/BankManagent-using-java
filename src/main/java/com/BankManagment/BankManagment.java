package com.BankManagment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BankManagment
 */
@WebServlet("/BankManagment")
public class BankManagment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankManagment() {
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
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int AcNo =Integer.parseInt(request.getParameter("ac"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/acc","root","rishabh@491");
			PreparedStatement ps=con.prepareStatement("select *from acc where ac_no=?");
			ps.setInt(1, AcNo);
			
			out.print("<table width=75% border=1>");
			out.print("<caption>Account balance :</caption>");
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int totalcolumn=rsmd.getColumnCount();
			out.print("<tr>");
			for(int i=1;i<=totalcolumn;i++){
				out.print("<th>"+rsmd.getColumnName(i)+"</th>");
			}
			out.print("</tr>");
			while(rs.next()) {
			out.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getInt(2)+"</td></tr>");
			}
			out.print("</table>");
		}
		catch(Exception  e) {
			out.print(e);
		}
		
		
		
	}

}
