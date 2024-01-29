package com.tutourialsninja.qa.listeners;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutourials.qa.utils.ExtentReporter;
import com.tutourials.qa.utils.Utilities;

//ITestListener is the annotation of Testng ...in pomxml will change the priority test to compile
public class Mylisteners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	
	public void onStart(ITestContext context) {
		//in onstart we are starting to generate the report
		 extentReport=ExtentReporter.generateExtentReport();
//       System.out.println("Execution of Project Tests started");		
	}

	public void onTestStart(ITestResult result) {
		testName =result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO,testName+" started executing");
//		System.out.println(testName+" started executing");
	//pre define method	
	}
	
	public void onTestSuccess(ITestResult result) {
//		testName =result.getName();
		extentTest.log(Status.PASS, testName+ "got successfully executed");
//		System.out.println(testName+ "got successfully executed");
	}
  
	public void onTestFailure(ITestResult result) {
		
//		String testName =result.getName();

		WebDriver driver =null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		}
		 
		String destinationScreenshotPath =Utilities.captureScreenShot(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+ "got failed");
//		System.out.println(testName+ "got failed");
//		System.out.println(result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
//		testName =result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+ "got skipped");
//		System.out.println(testName+ "got skipped");
//		System.out.println(result.getThrowable());
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
// if i dont flush all these details will be not be added to the report,		
//		System.out.println("Finised executing Project Tests");
		
    	String pathOfExtentReport = (System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
//	    String extentReportPath = ExtentReporter.getExtentReportFilePath();
		System.out.println("Finished executing Project Tests");
	    System.out.println("Extent Report File Path: " + pathOfExtentReport);
//	    File extentReportFile = new File(pathOfExtentReport);
//		try {
//    	Desktop.getDesktop().browse(extentReportFile.toURI());
////			Desktop.getDesktop().browse(new File("C:\\Users\\User\\eclipse-workspace\\TutourialsNinjaProj\\src\\main\\java\\test-output\\ExtentReports\\extentReport.html").toURI());
//		} catch (IOException e) {
//			// 
//			e.printStackTrace();
//		}
	}	
	
}

