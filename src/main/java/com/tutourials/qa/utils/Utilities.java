package com.tutourials.qa.utils;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
public class Utilities {
	
	//here will declare time constant as it cannot is taken from property file or from the excle file
	
	public static final int IMPLICIT_WAIT_TIME=30;
	
	public static final int PAGE_WAIT_TIME=50;
	
	//declared final as its a constant and i dont want to change it from base class
	
	
 // here we have made the method static because it will be easy to call from class name.
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
	    String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
	    //System.out.println(timeStamp);
	    //return timeStamp;// this method is normally used for the time satmp if we called this in main method
		 return "sardhadutta"+timeStamp+"@gmail.com";
}

	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		
	    File excelFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutourialsninja\\qa\\testdata\\TutourialsNinjaTestData.xlsx");
	    Workbook workbook = null;

	    try {
	        FileInputStream fisExcel = new FileInputStream(excelFile);
	        workbook = WorkbookFactory.create(fisExcel);
	    }catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}
	        Sheet sheet = workbook.getSheet(sheetName);

	        int rows = sheet.getLastRowNum();
	        int cols = sheet.getRow(0).getLastCellNum();

	        Object[][] data = new Object[rows][cols];

	        for (int i = 0; i < rows; i++) {
	            Row row = sheet.getRow(i+1);

	            for (int j = 0; j < cols; j++) {
	                Cell cell = row.getCell(j);
	                    CellType cellType = cell.getCellType();

	                    switch (cellType) {
	                        case STRING:
	                            data[i][j] = cell.getStringCellValue();
	                            break;
	                        case NUMERIC:
	                            data[i][j] = Integer.toString((int)cell.getNumericCellValue());
	                            break;
	                        case BOOLEAN:
	                            data[i][j] = cell.getBooleanCellValue();
	                            break;
	                        default:
	                            break;
	                    }
	            }
	        }

	      return data;
	        }
	    
	public static String captureScreenShot(WebDriver driver,String testName) {
		
	File srcScreenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";	
	try {
		FileHandler.copy(srcScreenshotFile, new File(destinationScreenshotPath));
	} catch (IOException e) {
		e.printStackTrace();
	}
	return destinationScreenshotPath;
	}
	
	}

	            
	        
	        
	        
	    
	            
	        
	    
	
	