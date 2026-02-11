package Railway;

import Common.Utilities;
import Common.Account;
import Common.RailwayPageTab;
import Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WindowType;


public class RegisterTest extends BaseTest {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterTest.class);
	RandomEmailPage randomEmailPage;
	RegisterPage registerPage;
	GeneralPage generalPage;
	HomePage homePage;
	
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
	}
	
	@Test
	public void TC07() {
		log.info("TC07 - User can't create account with an already in-use email");
		
		log.info("Pre-condition: an actived account is existing");
		//Create random email account
		createRandomEmail();
		
		//Register new account
		createAccout(account.getEmail(), account.getPassword(), account.getPassword(), account.getPID());
		
		//Confirm create account
		confirmCreationRequest();
		
		//Switch to Railway tab
		switchToRailwayTab();
		log.info("Create account successfully");
		
		//Go to register page
		log.info("1. Navigate to QA Railway Website");
		homePage.open();
		
		log.info("2. Click on 'Register' tab");
		generalPage.gotoPage(RailwayPageTab.LOGIN);
		generalPage.gotoPage(RailwayPageTab.REGISTER);
		
		//Register account with existed account
		log.info("3. Enter information of the created account in Pre-condition");
		log.info("4. Click on 'Register' button");
		registerPage.registerAccount(account.getEmail(), account.getPassword(), account.getPassword(), account.getPID());
		
		//Verify: Error message "This email address is already in use." displays above the form.
		Assert.assertTrue(registerPage.verifyErrorMsgIsDisplay());
		Assert.assertEquals(registerPage.getTextRegisterErrorMsg(), Constant.REGISTEREMAILALREADYINUSEERRORMSG);
		log.info("Error message 'This email address is already in use.' displays above the form.");
	}
	
	@Test
	public void TC08() {
		
		//Create account
		//Create random email account
		createRandomEmail();
		
		//Create account with empty Password and PID
		log.info("1. Navigate to QA Railway Website");
		log.info("2. Click on 'Register' tab");
		log.info("3. Enter valid email address and leave other fields empty");
		log.info("4. Click on 'Register' button");
		createAccout(account.getEmail(), "", "", "");
		
		//Verify message "There're errors in the form. Please correct the errors and try again." appears above the form.
		Assert.assertTrue(registerPage.verifyErrorMsgIsDisplay());
		Assert.assertEquals(registerPage.getTextRegisterErrorMsg(), Constant.REGISTEREMPTYACCOUNTERRORMSG);
		log.info("Expected result: Message 'There're errors in the form. Please correct the errors and try again.' appears above the form.");
		
		//Verify error message "Invalid password length." displays
		Assert.assertTrue(registerPage.verifyPasswordErrorMsgIsDisplay());
		Assert.assertEquals(registerPage.getTextPasswordErrorMsg(),Constant.REGISTEREMPTYPASSWORDERRORMSG);
		log.info("Next to password fields, error message 'Invalid password length.' displays");
		
		//Verify error message "Invalid ID length." displays
		Assert.assertTrue(registerPage.verifyErrorMsgIsDisplay());
		Assert.assertEquals(registerPage.getTextRegisterErrorMsg(), Constant.REGISTEREMPTYPIDERRORMSG);
		log.info("Next to PID field, error message 'Invalid ID length.' displays");
	}
	
	@Test
	public void TC09() {
		
		//Create account
		//Create random email account
		createRandomEmail();
		
		//Navigate to Railway website
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);   
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);
        railwayTab = Constant.WEBDRIVER.getWindowHandle();
        log.info("Railway Tab: " + railwayTab);
        
        //Open HomePage
        homePage.open();
        
        //Navigate to Register tab
        homePage.gotoRegisterPageByURL();
        
        //Register new Account
        registerPage.registerAccount(account.getEmail(), account.getPassword(), account.getPassword(), account.getPID());     
        
        log.info("Wait for active account");
        
        //Verify: "Thank you for registering your account" is shown
        Assert.assertTrue(registerPage.verifyRegisterContent(Constant.REGISTERSTEP1SUCCESS));
        
        //Confirm create account
        confirmCreationRequest();
        
        //Switch to Railway tab
        switchToRailwayTab();
        
        //Verify message "Registration Confirmed! You can now log in to the site" is shown
        Assert.assertTrue(registerPage.verifyRegisterContent(Constant.REGISTERCONFIRMSUCESS));
        
	}
	
	public void createAccout(String email, String password, String confirmPassword, String pid) {
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
	
	public void createRandomEmail() {
        Constant.WEBDRIVER.get(Constant.RANDOMEMAIL_URL);
        randomEmailTab = Constant.WEBDRIVER.getWindowHandle();
        log.info("Random Email Tab: " + randomEmailTab);

        emailName = Utilities.generateRandomString(6);
		account = randomEmailPage.generateAccount(emailName);
        
        

        log.info("Generated Email: " + account.getEmail());
        log.info("Generated Password: " + account.getPassword());
	}
	
	public void confirmCreationRequest() {
        Constant.WEBDRIVER.switchTo().window(randomEmailTab);
        log.info("Switched back to Random Email tab");
        generalPage.closeAdIfPresent();
        randomEmailPage.confirmCreatedAccountByEmail(account.getEmail(),"confirm");
        //randomEmailPage.confirmCreatedAccountByEmail();
        log.info("Confirm successfully");
        
	}
	
	public void switchToRailwayTab() {
	    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	    	Constant.WEBDRIVER.switchTo().window(handle);

	        if (Constant.WEBDRIVER.getCurrentUrl().contains("railway")) {
	            break;
	        }
	    }
	}

}