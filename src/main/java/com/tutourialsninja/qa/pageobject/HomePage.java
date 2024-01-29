package com.tutourialsninja.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	//here webelements 
	//objects
	WebDriver driver;
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	private WebElement myAccountDropMenu;
	
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	private WebElement loginOption;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	private WebElement registerOptionElement;
	
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement searchOptionElement;
	
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement searchbuttonElement;

	
	
	
	public HomePage(WebDriver driver) {
		
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	 
	//Actions
	
	public void clickOnMyAccountDropMenu() {
		
		myAccountDropMenu.click();
		
		
	}
	
	public Loginpage selectLoginOption() {
		
		loginOption.click();
		return new Loginpage(driver);
// we know that once we select login option we will take to the login page 
// here what i have done i have returned an object of the login page and pass the driver and return type is loginpage.		
	}
	
	public Registerpage selectRegisterOption() {
		
		registerOptionElement.click();
		return new Registerpage(driver);
	}
	
	public void enterProductIntoSearchBoxField(String productText) {
		
		searchOptionElement.sendKeys(productText);
		
	}
	public Searchpage clickOnSearchbtn() {
		
		searchbuttonElement.click();
		return new Searchpage(driver);
	}
}