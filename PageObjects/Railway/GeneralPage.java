package Railway;

import java.awt.print.Pageable;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.browsingcontext.Locator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Common.RailwayPageTab;
import Constant.Constant;

public class GeneralPage {
	
	protected WebDriver driver;
	
	public static final Logger log = LoggerFactory.getLogger(GeneralPage.class);
	
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
	private final By btnCloseAdvertisement = By.xpath("//div[@id='card']/descendant::div[@id='dismiss-button']");
	
	protected WebElement getlblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}
	
	protected WebElement getbtnCloseAdvertisement() {
		return Constant.WEBDRIVER.findElement(btnCloseAdvertisement);
	}
	
	public void clickBtnCloseAdvertisement() {
		getbtnCloseAdvertisement().click();
	}
	
	public String getWelcomeMessage() {
		return this.getlblWelcomeMessage().getText();
	}
	
	public void clickTab(RailwayPageTab tab) {
	    Constant.WEBDRIVER.findElement(tab.getLocator()).click();
	}

	
	public LoginPage gotoLoginPage() {
		clickTab(RailwayPageTab.LOGIN);
		return new LoginPage();
	}
	
	//Gộp lại thành 1 step cho tất cả hàm goto
	//Dùng enum ngoài testcase
	
	public RegisterPage gotoRegisterPage() {
		clickTab(RailwayPageTab.REGISTER);
		return new RegisterPage();
	}
	
	public FAQPage gotoFAQPage() {
		clickTab(RailwayPageTab.FAQ);
		return new FAQPage();
	}
	
	public BookTicketPage gotoBookTicketPage() {
		clickTab(RailwayPageTab.BOOKTICKET);
		return new BookTicketPage();
	}
	
	public void logOutAccount() {
		clickTab(RailwayPageTab.LOGOUT);
	}
	
	public boolean verifyLogOutAccount() {
		
		try {
			return Constant.WEBDRIVER
					.findElement(RailwayPageTab.LOGOUT.getLocator())
					.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void closeAdIfPresent() {
		try {
	        Constant.WEBDRIVER.switchTo().defaultContent();

	        List<WebElement> frames = Constant.WEBDRIVER.findElements(By.tagName("iframe"));

	        for (WebElement frame : frames) {
	            Constant.WEBDRIVER.switchTo().frame(frame);

	            List<WebElement> closeBtns =
	                    Constant.WEBDRIVER.findElements(By.id("dismiss-button"));

	            if (!closeBtns.isEmpty()) {
	                closeBtns.get(0).click();
	                log.info("Ad popup closed");
	                Constant.WEBDRIVER.switchTo().defaultContent();
	                return;
	            }

	            Constant.WEBDRIVER.switchTo().defaultContent();
	        }

	        log.info("No ad popup displayed");

	    } catch (Exception e) {
	        log.info("Popup handling skipped");
	        Constant.WEBDRIVER.switchTo().defaultContent();
	    }
	}
	
	
		
}