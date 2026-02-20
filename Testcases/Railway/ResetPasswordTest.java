package Railway;

import Common.Utilities;
import Common.Account;
import Common.RailwayPageTab;
import Common.Ticket;
import Common.TimetableAction;
import Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WindowType;


public class ResetPasswordTest extends BaseTest {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterTest.class);
	
	RandomEmailPage randomEmailPage;
	RegisterPage registerPage;
	GeneralPage generalPage;
	HomePage homePage;
	LoginPage loginPage;
	
	private String randomEmailTab;
    private String railwayTab;
    // Generate random email account
    String emailName;
    Account account;
	
	@BeforeMethod
	public void initalTest() {
		randomEmailPage = new RandomEmailPage();
		registerPage = new RegisterPage();
		generalPage = new GeneralPage();
		homePage = new HomePage();
		loginPage = new LoginPage();
		
		log.info("Pre-condition: an actived account is existing");
		createRandomEmail();
		createAccout(account.getEmail(), account.getPassword(), account.getPassword(), account.getPID());
		confirmCreationRequest();
		switchToRailwayTab();
		
		log.info("1. Navigate to QA Railway Login page");
	    homePage.open();
	    generalPage.gotoPage(RailwayPageTab.LOGIN);
	}
	
	@Test
	public void TC10() {
	   loginPage.navigateToForgotPasswordForm();
	   loginPage.sendEmailReset(account.getEmail());
	   
	}	
	
	
	private void createAccout(String email, String password, String confirmPassword, String pid) {
        Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);

        railwayTab = Constant.WEBDRIVER.getWindowHandle();
        log.info("Railway Tab: " + railwayTab);
        
        generalPage.gotoPage(RailwayPageTab.REGISTER);
        registerPage.registerAccount(email, password, confirmPassword, pid);
        
        log.info("AC Email: " + account.getEmail());
        log.info("AC Password: " + account.getPassword());
        log.info("AC PID: " + account.getPID());  
	}
	
	private void createRandomEmail() {
        Constant.WEBDRIVER.get(Constant.RANDOMEMAIL_URL);
        randomEmailTab = Constant.WEBDRIVER.getWindowHandle();
        log.info("Random Email Tab: " + randomEmailTab);

        emailName = Utilities.generateRandomString(6);
		account = randomEmailPage.generateAccount(emailName);
        
        

        log.info("Generated Email: " + account.getEmail());
        log.info("Generated Password: " + account.getPassword());
	}
	
	private void confirmCreationRequest() {
        Constant.WEBDRIVER.switchTo().window(randomEmailTab);
        log.info("Switched back to Random Email tab");
        generalPage.closeAdIfPresent();
        randomEmailPage.confirmCreatedAccountByEmail(account.getEmail(),"confirm");
        //randomEmailPage.confirmCreatedAccountByEmail();
        log.info("Confirm successfully");
        
	}
	
	private void switchToRailwayTab() {
	    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	    	Constant.WEBDRIVER.switchTo().window(handle);

	        if (Constant.WEBDRIVER.getCurrentUrl().contains("railway")) {
	            break;
	        }
	    }
	}
}
	    