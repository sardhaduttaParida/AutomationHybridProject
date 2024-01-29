package com.tutourialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutourials.qa.utils.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	//done public so that it can access in child class
	//here i have made a method to call the property file 
	
	public void loadpropertiesFile() {
		//will create an object for property file
		
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutourialsninja\\qa\\config\\config.properties");
		
		dataProp =new Properties();
		File datapropfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutourialsninja\\qa\\testdata\\testdata.properties");
		
		try {
		FileInputStream datafis = new FileInputStream(datapropfile);
	    dataProp.load(datafis);
		} catch (Exception e) {
			// exception is the parent throwable also i can give it is grand parent.
			//if any exception comes we can use
			e.printStackTrace(); 
		}
		
		
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
			
		} catch (Exception e) {
			// exception is the parent throwable also i can give it is grand parent.
			//if any exception comes we can use
			e.printStackTrace(); 
		}
		
	}
	
	//here will write a method to initialse browser and here we will recieve a browser as parameter 
	
//	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
//		
//		//String browserName = "chrome";
//		//above string is the method we can change the browser as we want to do.
//		
//		if (browserName.equalsIgnoreCase("chrome")) {
////   here i have use the ignore case in case there is any issue in property file for uppercase or lowercase			
//		    driver = new ChromeDriver();
//		} else if (browserName.equalsIgnoreCase("firefox")) {
//		    driver = new FirefoxDriver();
//		} else if (browserName.equalsIgnoreCase("edge")) {
////		   EdgeOptions options = new EdgeOptions();
////			options.setCapability("ms:edgeOptions", "--disable-popup-blocking");
////		    driver = new EdgeDriver(options);
//		    driver = new EdgeDriver();
//		}
//
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
//		driver.get(prop.getProperty("url"));	
////      		
////		EdgeOptions options = new EdgeOptions();
////		options.setCapability("ms:edgeOptions", "--disable-popup-blocking");
//		return driver;
//		
//		//whatever the driver that is created here we are going to return ,return driver and return type is webdriver like 
//		//like this method will be created a reusable method since i am returniing the this particular driver using 
//		//which browser is launched ,here same driver is returning back to the method which has called this particular method back to class
//		//which has all this method 
//	}
//
//}
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
		driver = createDriverInstance(browserName);
		
		configureDriver();
		return driver;
	}
	
	private WebDriver createDriverInstance(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver-win64\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver-win64\\chromedriver.exe");
			return new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			return new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			return new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Invalid browser name: " + browserName);
		}
	}
	
	private void configureDriver() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
		driver.get(prop.getProperty("url"));
	}
}