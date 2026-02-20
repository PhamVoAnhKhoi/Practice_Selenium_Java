package Railway;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class LoginPage extends GeneralPage {
	
	
	private final By inputUsername = By.xpath("//input[@id='username']");
	private final By inputPassword = By.xpath("//input[@id='password']");
	private final By btnLogin = By.xpath("//input[@title='Login']");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	private final By lblForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");
	private final By inputEmail = By.xpath("//input[@id='email']");
	private final By btnSendInstructions = By.xpath("//input[@value='Send Instructions']");
	
	public WebElement getTxtUsername() {
		return Constant.WEBDRIVER.findElement(inputUsername);
	}
	
	public WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(inputPassword);
	}
	
	public WebElement getBtnLogin() {
		return Constant.WEBDRIVER.findElement(btnLogin);
	}
	
	public WebElement getLblLoginErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblLoginErrorMsg);
	}	
	
	public WebElement getInputEmail() {
		return Constant.WEBDRIVER.findElement(inputEmail);
	}
	
	public WebElement getBtnSendInstructions() {
		return Constant.WEBDRIVER.findElement(btnSendInstructions);
	}
	
	public WebElement getLBLForgotPassword() {
		return Constant.WEBDRIVER.findElement(lblForgotPassword);
	}
	
	public HomePage login(String username, String password) {
		
		Utilities.waitForVisible(inputUsername).sendKeys(username);
		Utilities.waitForVisible(inputPassword).clear();
		Utilities.waitForVisible(inputPassword).sendKeys(password);

		Utilities.scrollToElement(getBtnLogin());
	    getBtnLogin().click();
	    
	    return new HomePage();
	}
	
	public boolean isLoginSuccess() {
		try {
			return getlblWelcomeMessage().isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean getInvalidErrorIsVisible() {
		try {
			return getLblLoginErrorMsg().isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void loginFailManyTime(String username, String password, int number) {
		String passWord;
		for(int i = 0; i<number; i++) {
			// getTabLogin().click();
			passWord = password + "" + i;
			log.info(passWord);
			login(username, "123456");

			Constant.WEBDRIVER.navigate().refresh();
			log.info("Login Failed " + i);
		}
	}
	
	public void navigateToForgotPasswordForm() {
		Utilities.waitForVisible(lblForgotPassword);
		getLBLForgotPassword().click();
	}
	
	public void sendEmailReset(String email) {
		Utilities.scrollToElement(getBtnSendInstructions());;
		getInputEmail().sendKeys(email);
		getBtnSendInstructions().click();
	}
	
}