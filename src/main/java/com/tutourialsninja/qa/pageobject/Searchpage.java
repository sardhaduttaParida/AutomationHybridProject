package com.tutourialsninja.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Searchpage {

	WebDriver driver;
	
	@FindBy(xpath = "//a[normalize-space()='HP LP3065']")
	private WebElement validHPProduct;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessagElement;
	
	public Searchpage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver,this);
	}
	
	public boolean displayStatusOfHPValidProduct() {
		boolean validProductDisplayString = validHPProduct.isDisplayed();
		return validProductDisplayString;
	}

    public String displayNoProductMessage() {
    	String displayNoProductMessageTextString = noProductMessagElement.getText();
    	return displayNoProductMessageTextString;
    }
}
