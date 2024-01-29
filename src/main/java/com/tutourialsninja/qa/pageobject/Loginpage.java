package com.tutourialsninja.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {

	WebDriver driver;
    
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailAddressField;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordFieldElement;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
		
	
	
// I have create a construtor and pass a parameter
	
	public Loginpage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	public void enterEmailAddress(String emailText) {
		
		emailAddressField.sendKeys(emailText);
	}
	public void enterPassword(String passwordText) {
		 
		passwordFieldElement.sendKeys(passwordText);
	}
	
	public Accountpage clickOnLoginButton() {
		
		loginButton.click();
		return new Accountpage(driver);
		
	}
	public String getEmailPasswordNotWarningMessage() {
		
		String warningText= emailPasswordNotMatchingWarning.getText();
		return warningText;
		
	}
}

