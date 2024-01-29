package com.tutourialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutourials.qa.utils.Utilities;
import com.tutourialsninja.qa.base.Base;
import com.tutourialsninja.qa.pageobject.AccountSuccessPage;
import com.tutourialsninja.qa.pageobject.HomePage;
import com.tutourialsninja.qa.pageobject.Registerpage;

public class RegisterTest extends Base {
	AccountSuccessPage accountSuccessPage;
	Registerpage registerpage;
	public WebDriver driver;
	
	
	//common methods
	@BeforeMethod
	public void setup() throws InterruptedException {
		
		loadpropertiesFile();
//		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		driver = initializeBrowserAndOpenApplicationURL("chrome");
		HomePage homePage =new HomePage(driver);
		homePage.clickOnMyAccountDropMenu(); 
		registerpage = homePage.selectRegisterOption();
		
	}
	
	
	@AfterMethod
	public void teardown() {
		
		driver.quit();
		
	}
	
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() throws InterruptedException {
		Thread.sleep(3000);
		accountSuccessPage= registerpage.registerWithMandetoryFields(dataProp.getProperty("firstname"), dataProp.getProperty("lastname"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone_no"), prop.getProperty("validpassword"));
		
//        registerpage.enterFirstName(dataProp.getProperty("firstname"));
//        registerpage.enterLastName(dataProp.getProperty("lastname"));
//        registerpage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
//        registerpage.enterTelephone(dataProp.getProperty("telephone_no"));
//        registerpage.enterPassword(prop.getProperty("validpassword"));
//        registerpage.enterConfirmPassword(prop.getProperty("validpassword"));
//        registerpage.clickOnPrivacyPolicy();
//        registerpage.clickOnContinueBtn();
 		
		AccountSuccessPage accountSuccessPage =new AccountSuccessPage(driver);
		//String actualSuccessHeading = accountSuccessPage.accountSuccesspageHeadingDisplay();
		Assert.assertEquals(accountSuccessPage.accountSuccesspageHeadingDisplay(),"Your Account Has Been Created!","Account Success page is not Displayed");
		//driver.quit();
	}
	//here we can see that in every class we are using this time stamp method to over come we will use the inheritance method.
	
	
	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFielids() throws InterruptedException {
		
		Thread.sleep(3000);
		accountSuccessPage=registerpage.registerWithAllFields(dataProp.getProperty("firstname"), dataProp.getProperty("lastname"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone_no"), prop.getProperty("validpassword"));		
     // AccountSuccessPage accountSuccessPage =new AccountSuccessPage(driver);
	  //String actualSuccessHeading = accountSuccessPage.accountSuccesspageHeadingDisplay();
	  Assert.assertEquals(accountSuccessPage.accountSuccesspageHeadingDisplay(),"Your Account Has Been Created!","Account Success page is not Displayed");
		
		
	}
	@Test(priority = 3)
	 public void verifyRegisteringAccountWithExistingEmailAddress() throws InterruptedException {
	
		Thread.sleep(3000);
	registerpage.registerWithAllFields(dataProp.getProperty("firstname"), dataProp.getProperty("lastname"),prop.getProperty("validEmail"),dataProp.getProperty("telephone_no"),prop.getProperty("validpassword"));	
	String actualSuccessHeading = registerpage.retrieveDuplicateEmailWarning();
	Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("duplicate_email_warining_message"),"Account Cannot Be Created");
	//driver.quit();
}
	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() throws InterruptedException {
		Thread.sleep(3000);
		registerpage.clickOnContinueBtn();
		
		
//		String actualprivacyPolicyWarning = registerpage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(registerpage.retrievePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy policy Warning Message Not Dispalyed");
		Thread.sleep(3000);
		Assert.assertEquals(registerpage.retrievefirstNameWarning(),dataProp.getProperty("FirstNameWarning"),"First Name Warning Is Not Displayed");
		Thread.sleep(3000);
		Assert.assertEquals(registerpage.retrieveLastNameWarning(),dataProp.getProperty("LastNameWarning"),"Last Name Warning Is Not Displayed");
		Thread.sleep(3000);
		Assert.assertEquals(registerpage.retrieveemailWarning(),dataProp.getProperty("EmailWarning"),"Email Warning Is Not Displayed");
		Thread.sleep(3000);
		Assert.assertEquals(registerpage.retrieveTelephoneWarning(),dataProp.getProperty("TelephoneWarning"),"Telephone Warning Is Not Displayed");
		Thread.sleep(3000);
		Assert.assertEquals(registerpage.retrievePasswordWarning(),dataProp.getProperty("PasswordWarning"),"Password Warning Is Not Displayed");
		
	}
}

