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


import Constant.Constant;

public class GeneralPage {
	
	protected WebDriver driver;
	
	public static final Logger log = LoggerFactory.getLogger(GeneralPage.class);
	
	private final By tabLogin = By.xpath("//div[@id='menu']/descendant::a[normalize-space()='Login']");
	private final By tabLogout = By.xpath("//div[@id='menu']/descendant::a[normalize-space()='Logout']");
	private final By tabRegister = By.xpath("//div[@id='menu']/descendant::a[normalize-space()='Register']");
	private final By tabFAQ = By.xpath("//div[@id='menu']/descendant::a[normalize-space()='FAQ']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
	private final By btnCloseAdvertisement = By.xpath("//div[@id='card']/descendant::div[@id='dismiss-button']");
	
	protected WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(tabLogin);
	}
	
	protected WebElement getTabLogout() {
		return Constant.WEBDRIVER.findElement(tabLogout);
	}
	
	protected WebElement getTabFAQ() {
		return Constant.WEBDRIVER.findElement(tabFAQ);
	}
	
	protected WebElement getTabRegister() {
		return Constant.WEBDRIVER.findElement(tabRegister);
	}
	
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
	
	public LoginPage gotoLoginPage() {
		this.getTabLogin().click();
		return new LoginPage();
	}
	
	public RegisterPage gotoRegisterPage() {
		this.getTabRegister().click();
		return new RegisterPage();
	}
	public void navigateToRegisterPage() {
		this.getTabRegister().click();
	}
	
	public FAQPage gotoFAQPage() {
		this.getTabFAQ().click();
		return new FAQPage();
	}
	
	public void logOutAccount() {
		this.getTabLogout().click();
	}
	
	public boolean verifyLogOutAccount() {
		
		try {
			log.info("Is Display: " + getTabLogout().isDisplayed());
			return getTabLogout().isDisplayed();
		}
		catch(Exception e) {
			log.info("Expection");
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
	
	
		protected WebDriverWait wait = 
	        new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(20));

	    protected WebElement waitForVisible(By locator) {
	        return wait.until(
	            ExpectedConditions.visibilityOfElementLocated(locator)
	        );
	    }
	    
	    protected WebElement waitForClickable(By locator) {
	        return wait.until(
	            ExpectedConditions.elementToBeClickable(locator)
	        );
	    }

	    protected boolean waitForInvisible(By locator) {
	        return wait.until(
	            ExpectedConditions.invisibilityOfElementLocated(locator)
	        );
	    }
	    
	    public void scrollToElement(By locator) {
	    	WebElement element = waitForVisible(locator);
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	    }
	    
	    public static void scrollToElement(WebElement element) {
	    	JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
	    	js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	    }
}
