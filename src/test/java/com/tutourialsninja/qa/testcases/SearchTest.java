package com.tutourialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutourialsninja.qa.base.Base;
import com.tutourialsninja.qa.pageobject.HomePage;
import com.tutourialsninja.qa.pageobject.Searchpage;

import junit.framework.Assert;

public class SearchTest extends Base{
	 
	Searchpage searchpage;
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		loadpropertiesFile();
//		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser")); 
		driver=initializeBrowserAndOpenApplicationURL("chrome");
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("productname"));
		
		searchpage = homePage.clickOnSearchbtn();
		Assert.assertTrue(searchpage.displayStatusOfHPValidProduct());
		
		
		
	}
    @Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		
    	
    	HomePage homePage = new HomePage(driver);
    	homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidpraduct"));
    	searchpage= homePage.clickOnSearchbtn();
    	
    	
    	//Searchpage searchpage = new Searchpage(driver);
		String expectedSearchMessage = "There is no product that matches the search criteria.";
		String actualSearchMessage = (searchpage.displayNoProductMessage()); 
		Assert.assertTrue(actualSearchMessage.trim().startsWith(expectedSearchMessage));
		
	}
	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {
		
		HomePage homePage = new HomePage(driver);
		searchpage= homePage.clickOnSearchbtn();
		
		//Searchpage searchpage = new Searchpage(driver);
		String expectedSearchMessage = "There is no product that matches the search criteria.";
		String actualSearchMessage = (searchpage.displayNoProductMessage()); 
		Assert.assertTrue(actualSearchMessage.trim().startsWith(expectedSearchMessage));
		
	}
}
