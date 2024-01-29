package com.tutourials.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
//	    private static final String extentReportFilePath = "C:/Users/User/eclipse-workspace/TutourialsNinjaProj/test-output/ExtentReports/extentReport.html";
//	    public static ExtentReports generateExtentReport() {
//	    	ExtentReports extentReport = new ExtentReports();
//	    	File extentReportFile = new File(extentReportFilePath);
	
	public static ExtentReports generateExtentReport() {
	    
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
//      File extentReportFile = new File("C:/Users/User/eclipse-workspace/TutourialsNinjaProj/test-output/ExtentReports/extentReport.html");
//	    String extentReportFilePath = "C:/Users/User/eclipse-workspace/TutourialsNinjaProj/test-output/ExtentReports/extentReport.html";
//	    File extentReportFile = new File(extentReportFilePath); 
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        System.out.println("File path: " + extentReportFile.getAbsolutePath());

// here we can set how our extent report will look like 		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutourialsNinja Test Automations Results Report");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
//   to provide some additional information to our report
		
		Properties configProp = new Properties();
//	    File configPropFile = new File("C:/Users/User/eclipse-workspace/TutourialsNinjaProj/test-output/ExtentReports/extentReport.html");
	    File configPropFile =new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutourialsninja\\qa\\config\\config.properties");
		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
				configProp.load(fisConfigProp);
			
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validpassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
	} catch (IOException e) {
		e.printStackTrace();
	}	
		return extentReport;
	}

//	public static String getExtentReportFilePath() {
//        File extentReportFilePath = new File("C:/Users/User/eclipse-workspace/TutourialsNinjaProj/test-output/ExtentReports/extentReport.html");
//        return extentReportFilePath;
	}
	
	


