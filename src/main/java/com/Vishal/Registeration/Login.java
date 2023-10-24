package com.Vishal.Registeration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uemail= request.getParameter("username");
		String upwd= request.getParameter("password");
		String doj=request.getParameter("doj");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher= null;
		
		if(uemail== null || uemail.equals("")) {
			request.setAttribute("status", "invalidEmail");
			dispatcher= request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		if(upwd== null || upwd.equals("")) {
			request.setAttribute("status", "invalidUpwd");
			dispatcher= request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		if(doj== null || doj.equals("")) {
			request.setAttribute("status", "invalidDoj");
			dispatcher= request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/SNTI?useSSL=false","root","Vishu#@123");
			PreparedStatement pst =con.prepareStatement("select * from users where uemail = ? and upwd= ? and doj = ?");
			pst.setString(1, uemail);
			pst.setString(2, upwd);
			pst.setString(3, doj);
			
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				session.setAttribute("name", rs.getString("uname"));
				dispatcher= request.getRequestDispatcher("index.jsp");
			}
			else {
				request.setAttribute("status", "failed");
				dispatcher= request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
		}
		catch (Exception e){
			
		}
	}

}
