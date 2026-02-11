package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class HomePage extends GeneralPage {
	
	private final By urlCreateAccount = By.xpath("//div[@id='content']/descendant::a[@href=\"/Account/Register.cshtml\"]");
	
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	
	public WebElement getUrlCreateAccount() {
		return Constant.WEBDRIVER.findElement(urlCreateAccount);
	} 
	
	
	public RegisterPage gotoRegisterPageByURL() {
		this.getUrlCreateAccount().click();
		return new RegisterPage();
	}
}