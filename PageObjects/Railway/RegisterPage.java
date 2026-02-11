package Railway;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class RegisterPage extends GeneralPage {
	private final By inputUsername = By.xpath("//input[@id='email']");
	private final By inputPassword = By.xpath("//input[@id='password']");
	private final By inputConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By inputPID = By.xpath("//input[@id='pid']");
	private final By btnRegister = By.xpath("//input[@title='Register']");
	private final By registerErrorMsg = By.xpath("//p[@class='message error']");
	private final By passwordErrorMsg = By.xpath("//li[@class='password']/descendant::label[@class='validation-error']");
	private final By pidErrorMsg = By.xpath("//li[@class='pid']/descendant::label[@class='validation-error']");
	
	
	public WebElement getInputUsername() {
		return Constant.WEBDRIVER.findElement(inputUsername);
	}
	
	public WebElement getInputPassword() {
		return Constant.WEBDRIVER.findElement(inputPassword);
	}
	
	public WebElement getInputConfirmPassword() {
		return Constant.WEBDRIVER.findElement(inputConfirmPassword);
	}
	
	public WebElement getInputPID() {
		return Constant.WEBDRIVER.findElement(inputPID);
	}
	
	public WebElement getBtnRegister() {
		return Constant.WEBDRIVER.findElement(btnRegister);
	}
	
	//Register error message
	public WebElement getRegisterErrorMsg() {
		return Constant.WEBDRIVER.findElement(registerErrorMsg);
	}
	
	public String getTextRegisterErrorMsg() {
		return Constant.WEBDRIVER.findElement(registerErrorMsg).getText();
	}
	
	//Password error message
	public WebElement getPasswordErrorMsg() {
		return Constant.WEBDRIVER.findElement(passwordErrorMsg);
	}
	
	public String getTextPasswordErrorMsg() {
		return Constant.WEBDRIVER.findElement(passwordErrorMsg).getText();
	}
	
	//PID error message
	public WebElement getPIDErrorMsg() {
		return Constant.WEBDRIVER.findElement(pidErrorMsg);
	}
	
	public String getTextPIDErrorMsg() {
		return Constant.WEBDRIVER.findElement(pidErrorMsg).getText();
	}
	
	public By lblRegisterContent(String text) {
		return By.xpath("//div[@id='content']//*[contains(normalize-space(.),'" + text + "')]");
	}
	
//	public WebElement getLblRegisterContent(String text) {
//		return Constant.WEBDRIVER.findElement(lblRegisterContent(text));
//	}
	
	public void registerAccount(String email, String password, String confirmPassword, String pid) {
		Utilities.waitForVisible(btnRegister);
		getInputUsername().sendKeys(email);
		log.info("Email: " + email);
		getInputPassword().sendKeys(password);
		log.info("Password: " + password);
		getInputConfirmPassword().sendKeys(confirmPassword);
		getInputPID().sendKeys(pid);
		log.info("PID: " + pid);
		
		Utilities.scrollToElement(getBtnRegister());
		
		getBtnRegister().click();
		
	}
	
	public boolean verifyErrorMsgIsDisplay() {
		Utilities.waitForVisible(registerErrorMsg);
		try {
			
			log.info("Error msg: " + getRegisterErrorMsg().getText());
			return getRegisterErrorMsg().isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean verifyPasswordErrorMsgIsDisplay() {
		try {
			Utilities.waitForVisible(passwordErrorMsg);
			return getPasswordErrorMsg().isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean verifyPIDErrorMsgIsDisplay() {
		try {
			Utilities.waitForVisible(pidErrorMsg);
			return getPIDErrorMsg().isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean verifyRegisterContent(String content) {
		Utilities.waitForVisible(lblRegisterContent(content));
		try {
			
			log.info("Content: " + Constant.WEBDRIVER.findElement(lblRegisterContent(content)).getText());
			return Constant.WEBDRIVER.findElement(lblRegisterContent(content)).isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	
}
	