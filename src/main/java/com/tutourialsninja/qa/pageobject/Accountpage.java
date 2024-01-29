package com.tutourialsninja.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Accountpage {
	
	WebDriver driver;
	@FindBy(xpath="//a[text()='Edit your account information']")
	private WebElement editYourAccountInformationOption;
	
	@FindBy(xpath="//*[@id=\"content\"]/h1")
	private WebElement accountCreatedConformationElement;
	
	public Accountpage(WebDriver driver) {
		 
		this.driver =driver;
		PageFactory.initElements(driver,this);
		
	}
    public boolean getDisplayStatusOfEditAccountOption() {
		
    	return editYourAccountInformationOption.isDisplayed();
    	
	}
    
    
}
