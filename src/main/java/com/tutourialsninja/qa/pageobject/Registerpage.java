package com.tutourialsninja.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registerpage {
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@id='input-firstname']")
	private WebElement firstnameField;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	private WebElement lastNameFieldElement;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailAdressField;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	private WebElement telephoneField;
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordFieldElement;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	private WebElement passwordConfrimFieldElement;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement pricacyPolicyFieldElement;
	

	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButtonElement;
	
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewLetterOptionElement;
	
	@FindBy(xpath = "//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement duplicateEmailAdressWarning;
	
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarningElement;
	
	@FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameWarning;
	
	
	@FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameWarning;
	
	
	@FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement emailWarning;
	
	
	@FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement passwordWarningElement;
	
	
	public Registerpage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver,this);
		
		
	}
	
		public void enterFirstName(String firstNameText) {
			
			firstnameField.sendKeys(firstNameText);
		}
		
		public void enterLastName(String lastNmaeText) {
			 
			lastNameFieldElement.sendKeys(lastNmaeText);
		}
		
		public void enterEmailAddress(String emailText) {
			
			emailAdressField.sendKeys(emailText);
			
		}
		
		public void enterTelephone(String telephoneText) {
			
			telephoneField.sendKeys(telephoneText);
			
		}
		
		public void enterPassword(String passwordText) {
			
			passwordFieldElement.sendKeys(passwordText);
			
		}
		
		public void enterConfirmPassword(String passwordText) {
			
			passwordConfrimFieldElement.sendKeys(passwordText);
			
		}
		
		public void clickOnPrivacyPolicy() {
			
			pricacyPolicyFieldElement.click();
			
		}
		
		public AccountSuccessPage clickOnContinueBtn() {
			
			continueButtonElement.click();
			return new AccountSuccessPage(driver);
			
		}
		
		public void selectyesNewLetterOptionElement() {
			
			yesNewLetterOptionElement.click();
			
		}
		
		public String retrieveDuplicateEmailWarning() {
			
			String duplicateEmailWarningTextString= duplicateEmailAdressWarning.getText();
			return duplicateEmailWarningTextString;
		}
		
		public String retrievePrivacyPolicyWarning() {
			
			String privacyPolicyWarningText= privacyPolicyWarningElement.getText();
			return 	privacyPolicyWarningText;	
			
		}
		
		public String retrievefirstNameWarning() {
			
			String firstNameWarningText= firstNameWarning.getText();
			return firstNameWarningText;
		}
		
		public String retrieveLastNameWarning() {
			
			String lastNameWarningText = lastNameWarning.getText();
			return lastNameWarningText;
			
		}
		
		public String retrieveemailWarning() {
			
			String emailWarningTextString = emailWarning.getText();
			return emailWarningTextString;
			
		}
		
		public String  retrieveTelephoneWarning() {
			
			String telephoneWarningText = telephoneWarning.getText();
			return telephoneWarningText;
		}
		
		public String retrievePasswordWarning() {
			
			String passwordWarningText = passwordWarningElement.getText();
			return passwordWarningText;
			
		}
		
		public AccountSuccessPage registerWithMandetoryFields (String firstNameText,String lastNmaeText,String emailText, String telephoneText ,String passwordText) {
			
			
			firstnameField.sendKeys(firstNameText);
			lastNameFieldElement.sendKeys(lastNmaeText);
			emailAdressField.sendKeys(emailText);
			telephoneField.sendKeys(telephoneText);
			passwordFieldElement.sendKeys(passwordText);
			passwordConfrimFieldElement.sendKeys(passwordText);
			pricacyPolicyFieldElement.click();
			continueButtonElement.click();
			return new AccountSuccessPage(driver);
			
		}
		
		public AccountSuccessPage registerWithAllFields (String firstNameText,String lastNmaeText,String emailText, String telephoneText ,String passwordText) {

		firstnameField.sendKeys(firstNameText);
		lastNameFieldElement.sendKeys(lastNmaeText);
		emailAdressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordFieldElement.sendKeys(passwordText);
		passwordConfrimFieldElement.sendKeys(passwordText);
		yesNewLetterOptionElement.click();
		pricacyPolicyFieldElement.click();
		continueButtonElement.click();
		return new AccountSuccessPage(driver);
	}
}


