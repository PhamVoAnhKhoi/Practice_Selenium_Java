package Railway;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WindowType;

import Constant.Constant;
import Common.Account;
import Common.Utilities;

public class RandomEmailPage extends GeneralPage  {
	
	private final By btnEmailName = By.xpath("//span[@id='inbox-id']");
	private final By inputEmailName = By.xpath("//span[@id='inbox-id']//descendant::input");
	private final By btnSet = By.xpath("//span[@id='inbox-id']//descendant::button[contains(@class,'save button')]");
	private final By chkBoxScrambleAddress  = By.xpath("//input[@id='use-alias']");
	private final By listHostEmail = By.xpath("//select[@id='gm-host-select']");
	private final By actualEmailRandom = By.xpath("//span[@id='email-widget']");
	private final By emailConfirmCreated = By.xpath("//tbody[@id='email_list']/descendant::td[contains(normalize-space(.),'thanhletraining03@gmail.com')]");
	private final By confirmLink = By.xpath("//div[@class='email']/descendant::a");
		
		
	public RandomEmailPage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RANDOMEMAIL_URL);
		return this;
	}
	
	public WebElement getBtnEmailName() {
		return Constant.WEBDRIVER.findElement(btnEmailName);
	}
	
	public WebElement getInputEmailName() {
	    return Constant.WEBDRIVER.findElement(inputEmailName);
	}
	
	public WebElement getBtnSet() {
	    return Constant.WEBDRIVER.findElement(btnSet);
	} 
	
	public WebElement getListHostEmail(){
		return Constant.WEBDRIVER.findElement(listHostEmail);
	}
	
	public WebElement getActualEmailRandom(){
		return Constant.WEBDRIVER.findElement(actualEmailRandom);
	}
	
	public WebElement getEmailConfirmCreated(){
		return Constant.WEBDRIVER.findElement(emailConfirmCreated);
	}
	
	public WebElement getConfirmLink(){
		return Constant.WEBDRIVER.findElement(confirmLink);
	}
	
	public String getRandomEmail(){
		return getActualEmailRandom().getText();
	}
	
	public String generateUniqueEmail(String emailName){
		getBtnEmailName().click();
		waitForVisible(inputEmailName);
		getInputEmailName().sendKeys(emailName);
		getBtnSet().click();
		toggleScrambleAddressIfSelected();
		waitForVisible(actualEmailRandom);
		
		
		return getActualEmailRandom().getText().trim();
	}
	
	public void toggleScrambleAddressIfSelected() {

	    WebElement checkbox = waitForVisible(chkBoxScrambleAddress);
	
	    if (checkbox.isSelected()) {
	        log.info("Checkbox already selected then clicking again to uncheck");
	        checkbox.click();
	    } else {
	        log.info("Checkbox is not selected");
	    }
	}
		

	public boolean verifyEmailRandomIsCorrect(String name){
		//String hostEmail = getListHostEmail().getText().trim();
		
		
		String expectedRandomEmail = name + "@" + getSelectedHostEmail();
		log.info("Expected: " + expectedRandomEmail);
		String actualRandomEmail = getRandomEmail().trim();
		log.info("Actual: " + actualRandomEmail);
		
		return actualRandomEmail.equals(expectedRandomEmail);
	} 
	
	public String getSelectedHostEmail() {
	    Select select = new Select(getListHostEmail());
	    return select.getFirstSelectedOption().getText().trim();
	}
	
	public Account generateAccount(String emailName){

	    String email = generateUniqueEmail(emailName);
	    String password = Utilities.generateRandomString(8);
	    String pid = Utilities.generateRandomNumber();
	
		log.info("Generated Account: " + email +", " + password + ", " + pid);
	    return new Account(email, password,pid);
	}
	
	public void switchToRailwayTab() {

		    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
		        Constant.WEBDRIVER.switchTo().window(handle);
		
		        if (Constant.WEBDRIVER.getCurrentUrl().contains("railway")) {
		            break;
		        }
		    }
		}
		
	public RandomEmailPage openInNewTab() {

	    Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
	    Constant.WEBDRIVER.get(Constant.RANDOMEMAIL_URL);
	
	    return this;
	}
	
	public void confirmCreatedAccountByEmail(){
		waitForVisible(emailConfirmCreated);
		getEmailConfirmCreated().click();
		waitForVisible(confirmLink);
		getConfirmLink().click();
	}
	
}