package Railway;

import java.awt.print.Pageable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.browsingcontext.Locator;
import Constant.Constant;

public class GeneralPage {
	
	protected WebDriver driver;
	
	private final By tabLogin = By.xpath("//div[@id='menu']/descendant::a[normalize-space()='Login']");
	private final By tabLogout = By.xpath("//div[@id='menu']/descendant::a[normalize-space()='Logout']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
	
	protected WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(tabLogin);
	}
	
	protected WebElement getTabLogout() {
		return Constant.WEBDRIVER.findElement(tabLogout);
	}
	
	protected WebElement getlblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}
	
	public String getWelcomeMessage() {
		return this.getlblWelcomeMessage().getText();
	}
	
	public LoginPage gotoLoginPage() {
		this.getTabLogin().click();
		return new LoginPage();
	}
}
