package com.cms;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Authenticator extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int flag = 0;
				
		String uid=request.getParameter("uid");
		String psw=request.getParameter("psw");
		
		if(uid==null || psw==null) {
			response.sendRedirect(request.getContextPath()+"/welcome");
		}
		
		SQLHandler sqlHandler = new SQLHandler();
		HttpSession session;
		ResultSet rs = null;
        String query="select password from login where name = '"+uid+"';";
		try{
			rs = sqlHandler.retrieveData(query);
			session = request.getSession();
            if(rs.next()) {
            		String stored_psw = rs.getString("password");
            		if(psw.equals(stored_psw)) {
            			session.setAttribute("user", uid);
            			response.sendRedirect(request.getContextPath()+"/dashboard");
            		}
            		else {
            			flag = 1;
            			session.setAttribute("flag",flag);
                    	response.sendRedirect(request.getContextPath()+"/welcome");
            		}
            }
            else {
            	flag = 2;
            	session.setAttribute("flag",flag);
            	response.sendRedirect(request.getContextPath()+"/welcome");
            }
           }
        catch(Exception e)
           {
        	e.printStackTrace();
           }
		finally {
			sqlHandler.closeSQL();
			try {
			rs.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
