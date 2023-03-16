package com.karthik.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		System.out.println("init() called");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String mobile = req.getParameter("mobile");
		String[] ts = req.getParameterValues("techskills");
		System.out.println("Name = " + name);
		System.out.println("mobile = " + mobile);
		System.out.println("Techskills" );
		for(String i : ts) {
			System.out.println(i);
		}
		
//		Servlet Response		
//		RequestDispatcher rd = req.getRequestDispatcher("/staticresponse.html");
//		rd.forward(req, resp);
		
		
		
		PrintWriter writer = resp.getWriter();
		writer.println("<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Insert title here</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<h3>Thank you for the response."+name+"</h3>\r\n"
				+ "</body>\r\n"
				+ "</html>");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() called");
	}
}
