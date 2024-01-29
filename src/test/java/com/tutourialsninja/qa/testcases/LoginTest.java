package com.tutourialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutourials.qa.utils.Utilities;
import com.tutourialsninja.qa.base.Base;
import com.tutourialsninja.qa.pageobject.Accountpage;
import com.tutourialsninja.qa.pageobject.HomePage;
import com.tutourialsninja.qa.pageobject.Loginpage;

//cntrl+shift+O  to remove unused imports

public class LoginTest extends Base {
	
	Loginpage loginpage;
	//once login.java extends base once you import this all the properties of the base class will be part of login and you can call this method directly
	//here we have declared the webdriver golbally and remove the web driver from local
	public WebDriver driver;//here we have declared as public because we want to take failed testcase screenshot
	
	//here we have declared the quit method golbally by using @aftermethod we dont have to provide inside method
	//here we have created before method whichi we are using in common ,before every test method before test will executed.
	
	@BeforeMethod
	public void setup() throws InterruptedException {
//     as we know login is the child class of base we can call directly the method.
		
		loadpropertiesFile();
//		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		driver=initializeBrowserAndOpenApplicationURL("chrome");
		HomePage homePage =new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		loginpage = homePage.selectLoginOption();
//Here the login page is not accesible as it is local to the setup method.
	}
	//No matter what test get pass or fail windows will be closed after each and every method.
	
	
	@AfterMethod
	public void teardown() {
		if (driver != null) {
            driver.quit();
		}
		
	}

	@Test(priority=1,dataProvider = "supplyTestData")
	public void verifyLoginWithValidCredential(Object[] data) throws InterruptedException{
		
		String email = data[0].toString();  // Convert to String
	    String password = data[1].toString();  // Convert to String
		System.out.println("Email: " + email);
	    System.out.println("Password: " + password);
	    Thread.sleep(3000);
	   //login instance created  
	   // Loginpage loginpage =new Loginpage(driver);
	    loginpage.enterEmailAddress(email);
	    loginpage.enterPassword(password);
	    Accountpage accountpage= loginpage.clickOnLoginButton();	    
	    //Accountpage accountpage = new Accountpage(driver);
	    Assert.assertTrue(accountpage.getDisplayStatusOfEditAccountOption(),"Edit Account option not displayed");
		
	}
	@DataProvider(name = "supplyTestData")
	public Object[][] supplyTestData() {
		Object [][] data= Utilities.getTestDataFromExcel("login");	
	    return data;			
	}
	 
	
	
	@Test(priority=2)
	public void verifyLoginWithinvalidCredential() throws InterruptedException {
		
		//Loginpage loginpage =new Loginpage(driver);
		Thread.sleep(3000);
	    loginpage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
	    loginpage.enterPassword(dataProp.getProperty("InvalidPassword"));
	    loginpage.clickOnLoginButton();
		
		//String actualWarningMessage = loginpage.getEmailPasswordNotWarningMessage(); 
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginpage.getEmailPasswordNotWarningMessage().contains(expectedWarningMessage),"Expected Warning message is not displayed");
		
		
	}
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() throws InterruptedException {
		
		Thread.sleep(3000);	
		//Loginpage loginpage =new Loginpage(driver);
	    loginpage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
	    loginpage.enterPassword(prop.getProperty("validpassword"));
	    loginpage.clickOnLoginButton();
		
		//String actualWarningMessage = loginpage.getEmailPasswordNotWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginpage.getEmailPasswordNotWarningMessage().contains(expectedWarningMessage),"Expected Warning message is not displayed");
		//driver.quit();
		
		
		
	}
	    @Test(priority=4)
	    public void verifyLoginWithValidEmailAndInvalidPassword() throws InterruptedException {
		//WebDriver driver= new ChromeDriver();
	    	Thread.sleep(3000);
	    //Loginpage loginpage =new Loginpage(driver);
	    loginpage.enterEmailAddress(prop.getProperty("validEmail"));
	    loginpage.enterPassword(dataProp.getProperty("InvalidPassword"));
	    loginpage.clickOnLoginButton();
		
		//String actualWarningMessage = loginpage.getEmailPasswordNotWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginpage.getEmailPasswordNotWarningMessage().contains(expectedWarningMessage),"Expected Warning message is not displayed");
		//driver.quit();
		
	}
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredential() throws InterruptedException {
		//WebDriver driver= new ChromeDriver();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("");
		//Loginpage loginpage =new Loginpage(driver);
		loginpage.clickOnLoginButton();
		
		String actualWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
		//driver.quit();
	}
	}

