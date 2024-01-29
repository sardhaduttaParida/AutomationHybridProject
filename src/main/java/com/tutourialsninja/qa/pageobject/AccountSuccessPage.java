package com.tutourialsninja.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

	WebDriver driver;
	
//declaration	
	@FindBy(xpath="//div[@id='content']//h1")
	private WebElement accountSuccessPageHeading;
	
	
//initialization	
	public AccountSuccessPage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver,this);
	}
	
//utilization	
	public String accountSuccesspageHeadingDisplay() {
		String accountSuccessPageHeadingText= accountSuccessPageHeading.getText();
		return accountSuccessPageHeadingText;
	}
}
