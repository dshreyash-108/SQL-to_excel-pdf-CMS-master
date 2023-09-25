package com.cms;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.poi.xssf.usermodel.*;

public class ExportController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SQLHandler sqlHandler = new SQLHandler();
		ExcelHandler excelHandler = new ExcelHandler();
		XSSFWorkbook workbook = null;
		ResultSet rs = null;
		
		String flag=request.getParameter("flag");
		String filename = "" ,query = "";
			
		if(flag.equals("mis")) {
			String fdate=request.getParameter("fdate");
			String tdate=request.getParameter("tdate");
			if(fdate==null || tdate==null) {
				response.sendRedirect(request.getContextPath()+"/welcome");
			}
			filename = "Consolidated MIS ("+fdate+" to "+tdate+")";
			query = "select * from tbl_misdetails_old where date_time>='" + fdate + "' and date_time<='" + tdate + "' order by date_time asc";
		}
		else if(flag.equals("adnet")) {
			String month=request.getParameter("month");
			String year=request.getParameter("year");
			String agency=request.getParameter("agency");
			if(month==null || year==null || agency==null) {
				response.sendRedirect(request.getContextPath()+"/welcome");
			}
			filename = "Adnet Hits ("+month+"/"+year+") - "+agency;
			query = "";
		}
		else if(flag.equals("churn_mgmt")) {
			String month=request.getParameter("month");
			String year=request.getParameter("year");
			String agency=request.getParameter("agency");
			String date=request.getParameter("date");
			if(month==null || year==null || agency==null || date==null) {
				response.sendRedirect(request.getContextPath()+"/welcome");
			}
			filename = "Churn Management ("+date+"/"+month+"/"+year+") - "+agency;
			query = "";
		}
		else if(flag.equals("logs")) {
			filename = "Transaction Logs";
			query = "";
		}
		else if(flag.equals("churn_details")) {
			filename = "Churn Details";
			query = "";
		}
		else if(flag.equals("renewal_details")) {
			String fdate=request.getParameter("fdate");
			String tdate=request.getParameter("tdate");
			String agency=request.getParameter("agency");
			if(fdate==null || tdate==null || agency==null) {
				response.sendRedirect(request.getContextPath()+"/welcome");
			}
			filename = "Renewal Details ("+fdate+" to "+tdate+") - "+agency;
			query = "";
		}
		else if(flag.equals("activation_details")) {
			filename = "Activation Details";
			query = "";
		}
		else if(flag.equals("callbacks")) {
			String fdate=request.getParameter("fdate");
			String tdate=request.getParameter("tdate");
			String agency=request.getParameter("agency");
			if(fdate==null || tdate==null || agency==null) {
				response.sendRedirect(request.getContextPath()+"/welcome");
			}
			filename = "Callbacks ("+fdate+" to "+tdate+") - "+agency;
			query = "";
		}
		else if(flag.equals("active_base")) {
			filename = "Active Base";
			query = "";
		}
		else{
			response.sendRedirect(request.getContextPath()+"/welcome");
		}
		
		response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment; filename="+filename+".xlsx");
        
        HttpSession session = request.getSession(); 
        
        if(query=="") {
			session.setAttribute("flag", 1);
			response.sendRedirect(request.getContextPath()+"/welcome");
		}
        
		try{
			rs = sqlHandler.retrieveData(query);
			workbook = excelHandler.createWorkbook(rs, filename);
			
			if(workbook==null) {
				session.setAttribute("flag", 1);
				response.sendRedirect(request.getContextPath()+"/dashboard");
			}
			else {
				workbook.write(response.getOutputStream());
			}
           }
        catch(Exception e)
           {
        	e.printStackTrace();
           }
		finally {
			sqlHandler.closeSQL();
			excelHandler.closeExporter();
			try {
				if(workbook!=null)
					workbook.close();
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
