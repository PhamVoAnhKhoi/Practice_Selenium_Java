package Railway;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class RegisterPage extends GeneralPage {
	private final By inputUsername = By.xpath("//input[@id='email']");
	private final By inputPassword = By.xpath("//input[@id='password']");
	private final By inputConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By inputPID = By.xpath("//input[@id='pid']");
	private final By btnRegister = By.xpath("//input[@title='Register']");
	
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
	
	public void registerAccount(String email, String password, String confirmPassword, String pid) {
		waitForVisible(btnRegister);
		getInputUsername().sendKeys(email);
		getInputPassword().sendKeys(password);
		getInputConfirmPassword().sendKeys(confirmPassword);
		getInputPID().sendKeys(pid);
		
		scrollToElement(getBtnRegister());
		
		getBtnRegister().click();
	}
}
	