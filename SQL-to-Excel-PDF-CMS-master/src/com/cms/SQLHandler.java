package com.cms;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class SQLHandler{
	public static Connection con;
	public static Statement stmt;
	public static InputStream in;
	public static ResultSet rs;

	public ResultSet retrieveData(String query) {
		try{
			in =  getClass().getClassLoader().getResourceAsStream("connection.prop");
			Properties props = new Properties();
			props.load(in);
			
			
			String sql_url = (String) props.get("url");
			String sql_db = (String) props.get("db");
			String sql_user = (String) props.get("user");
			String sql_psw = (String) props.get("pass");
	        Class.forName("com.mysql.jdbc.Driver");
	        con = (Connection)
	        DriverManager.getConnection	
	        (sql_url+"/"+sql_db,sql_user,sql_psw);
	        stmt = (Statement) con.createStatement(); 
            rs = stmt.executeQuery(query);
         }
        catch(Exception e){
        	e.printStackTrace();
         }
		return rs;
	}
	
	public void closeSQL() {
		try {
			con.close();
			stmt.close();
			in.close();
			rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String getAgencies() {
		StringBuilder data = new StringBuilder("");
		data.append("<select>");
		ResultSet result = this.retrieveData("Select distinct vendor_name from campaign_config");
		try {
			while(result.next()) {
				data.append("<option>"+result.getString(1)+"</option>");
			}
			data.append("</select>");
			return data.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
