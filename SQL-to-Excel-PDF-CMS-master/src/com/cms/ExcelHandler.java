package com.cms;

import java.sql.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelHandler{
	XSSFWorkbook workbook = new XSSFWorkbook();
	
	protected XSSFWorkbook createWorkbook(ResultSet rs, String sheetname) throws Exception{
		ResultSetMetaData meta = rs.getMetaData();
		int cols = meta.getColumnCount();
		try{			 
			XSSFSheet spreadsheet = workbook.createSheet(sheetname);
			
			XSSFFont dataFont = workbook.createFont(),headingFont = workbook.createFont();
			dataFont.setColor(IndexedColors.GREY_40_PERCENT.getIndex());
			headingFont.setBold(true);
			
			CellStyle dataStyle = workbook.createCellStyle(),headingStyle = workbook.createCellStyle();
			dataStyle.setFont(dataFont);
			headingStyle.setFont(headingFont);
			headingStyle.setAlignment(HorizontalAlignment.CENTER);
			headingStyle.setBorderBottom(BorderStyle.THICK);
			  
			XSSFRow row = spreadsheet.createRow(0);
			XSSFCell cell;
			
			for(int i=1;i<=cols;i++) {
				cell = row.createCell(i-1);
				cell.setCellValue(meta.getColumnLabel(i).toUpperCase());
				cell.setCellStyle(headingStyle);
				spreadsheet.autoSizeColumn(i-1);
			}
			int j = 1;
			
			if(!rs.next()) {
				return null;			
			}
			
			do{
				row = spreadsheet.createRow(j);
				for(int i=1;i<=cols;i++) {
					cell = row.createCell(i-1);
					cell.setCellValue(rs.getString(i));
					cell.setCellStyle(dataStyle);
					spreadsheet.autoSizeColumn(i-1);
				}
				j++;
			  }while(rs.next());
		    return workbook;
		    
	   }
        catch(Exception e)
           {
        	e.printStackTrace();
           }
		return null;
	}
	
	public void closeExporter() {
		try {
			workbook.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
