package com.Vishal.Registeration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;



@WebServlet("/register")
public class Regerstraion_Servelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String uname= request.getParameter("name");
		String uemail= request.getParameter("email");
		String upwd= request.getParameter("pass");
		String Reupwd= request.getParameter("re_pass");
		String umobile= request.getParameter("contact");
		String dob=request.getParameter("dob");
		String doj=request.getParameter("doj");	
		
		RequestDispatcher dispatcher=null;
		Connection con=null;
		
		if(uname== null || uname.equals("")) {
			request.setAttribute("status", "invalidName");
			dispatcher= request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		if(uemail== null || uemail.equals("")) {
			request.setAttribute("status", "invalidEmail");
			dispatcher= request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(upwd== null || upwd.equals("")) {
			request.setAttribute("status", "invalidPWD");
			dispatcher= request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		else if(!upwd.equals(Reupwd)) {
			request.setAttribute("status", "invalidConfirmPWD");
			dispatcher= request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(umobile.length() !=10 ) {
			request.setAttribute("status", "invalidMobileLength");
			dispatcher= request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(dob== null || dob.equals("")) {
			request.setAttribute("status", "invalidDOB");
			dispatcher= request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(doj== null || doj.equals("")) {
			request.setAttribute("status", "invalidDOJ");
			dispatcher= request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		String recaptchaResponse = request.getParameter("g-recaptcha-response");

	    if (recaptchaResponse == null || recaptchaResponse.isEmpty()) {
	        request.setAttribute("status", "recaptchaFailed");
	        dispatcher = request.getRequestDispatcher("registration.jsp");
	        dispatcher.forward(request, response);
	        return; // Stop processing the form if reCAPTCHA failed
	    }

	    // Perform reCAPTCHA verification
	    String secretKey = "YOUR_RECAPTCHA_SECRET_KEY";
	    String verificationUrl = "https://www.google.com/recaptcha/api/siteverify?secret=" + "6Ldn6L0oAAAAAFRrF6-ZdWfiQsogMjv4WNSAHF6U" + "&response=" + recaptchaResponse;

	    try {
	        URL url = new URL(verificationUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        int responseCode = conn.getResponseCode();

	        if (responseCode == 200) {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            StringBuilder responseBody = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                responseBody.append(line);
	            }
	            reader.close();

	            JsonParser jsonParser = new JsonParser();
	            JsonObject jsonResponse = jsonParser.parse(responseBody.toString()).getAsJsonObject();

	            if (jsonResponse.get("success").getAsBoolean()) {
	                // reCAPTCHA verification passed, continue with user registration
	            } else {
	                request.setAttribute("status", "recaptchaFailed");
	                dispatcher = request.getRequestDispatcher("registration.jsp");
	                dispatcher.forward(request, response);
	            }
	        } else {
	            // Handle HTTP error (e.g., unable to reach reCAPTCHA server)
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exception
	    }
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/SNTI?useSSL=false","root","Vishu#@123");
			PreparedStatement pst =con.prepareStatement("insert into users(uname,upwd,uemail,umobile,dob,doj) values(?,?,?,?,?,?)");
			pst.setString(1,uname);
			pst.setString(2,upwd);
			pst.setString(3,uemail);
			pst.setString(4,umobile);
			pst.setString(5,dob);
			pst.setString(6,doj);
			
			int rowCount=pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			
			if(rowCount>0) {
				request.setAttribute("status","success");
			}
			else {
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
		    } 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
