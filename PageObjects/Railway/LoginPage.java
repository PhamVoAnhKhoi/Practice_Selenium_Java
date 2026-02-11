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
	private final By lblForgotPassword = By.xpath("");
	
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
	
	
}