package com.karthik.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Servlet2 extends HttpServlet {
	Connection con;
//	ResourceBundle bundle = ResourceBundle.getBundle("database");
//	String url=bundle.getString("url");
//	String username=bundle.getString("username");
//	String password=bundle.getString("password");
	
	
	
	@Override
	public void init() throws ServletException {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlets","root","admin");
			System.out.println("connection established");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String givenUsername = req.getParameter("username");
		String givenPassword = req.getParameter("password");
		resp.setContentType("text/html");
		try {
			String query = "select * from users where username=? and password=?";
			PreparedStatement psmt = con.prepareStatement(query);
			
			psmt.setString(1, givenUsername);
			psmt.setString(2, givenPassword);
			ResultSet rs = psmt.executeQuery();
			
			PrintWriter writer=resp.getWriter();
			if(rs.next()) {
				writer.println("<h3>Welcome to Chaos "+rs.getString("name")+"!!</h3>\r\n");
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("/invalidUserLogin.html");
				rd.forward(req, resp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
