package Railway;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class LoginPage extends GeneralPage {
	
	private final By txtUsername = By.xpath("//input[@id='username']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By btnLogin = By.xpath("//input[@title='Login']");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	
	public WebElement getTxtUsername() {
		return Constant.WEBDRIVER.findElement(txtUsername);
	}
	
	public WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(txtPassword);
	}
	
	public WebElement getBtnLogin() {
		return Constant.WEBDRIVER.findElement(btnLogin);
	}
	
	public WebElement getLblLoginErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblLoginErrorMsg);
	}	
	
	public HomePage login(String username, String password) {
	    waitForVisible(txtUsername).sendKeys(username);

	    waitForVisible(txtPassword).clear();
	    waitForVisible(txtPassword).sendKeys(password);

	    waitForClickable(btnLogin).click();
	    
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
			System.out.println(passWord);
			login(username, "123456");

			Constant.WEBDRIVER.navigate().refresh();
			System.out.println("Login Failed " + i);
		}
	}
}
