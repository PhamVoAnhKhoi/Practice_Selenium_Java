package Railway;

import Common.RailwayPageTab;
import Common.Utilities;
import Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {
	
	private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
	
	LoginPage loginPage;
	GeneralPage generalPage;
	
	@BeforeMethod
	public void initalTest() {
		HomePage homePage = new HomePage();
		homePage.open();
		loginPage = homePage.gotoPage(RailwayPageTab.LOGIN);
		generalPage = new GeneralPage();
	}
	
	@Test
	public void TC01() {
		log.info("TC01-User can log into Railway with valid username and password");
		
		//Login with valid account
		log.info("1. Navigate to QA Railway Website");
		log.info("2. Click on 'Login' tab");
		log.info("3. Enter valid Email and Password");
		
		loginPage.login(Constant.USERNAME, Constant.PASSWORD);
		
		//Verify user is logged into Railway. Welcome user message is displayed.
		Assert.assertTrue(loginPage.isLoginSuccess());
		log.info("Expected Result: Login Successfull");
	}
	
	@Test
	public void TC02() {
		log.info("TC02-User cannot login with blank Username");
		
		//Login with empty user
		log.info("1. Navigate to QA Railway Website");
		log.info("2. Click on 'Login' tab");
		log.info("3. User doesn't type any words into 'Username' textbox but enter valid information into 'Password' textbox ");
		log.info("4. Click on 'Login' button");
		loginPage.login(Constant.EMPTYUSERNAME, Constant.PASSWORD);
		
		//Verify user can't login and message "There was a problem with your login and/or errors exist in your form. " appears.
		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		Assert.assertTrue(loginPage.getInvalidErrorIsVisible());
		Assert.assertEquals(errorMsg,Constant.LOGINWITHEMPTYACCOUNTERRORMSG);
		log.info("Expected Result: Login Failed");
	}
	
	@Test
	public void TC03() {
		log.info("TC03-User cannot log into Railway with invalid password ");
		
		//Login with invalid account
		log.info("1. Navigate to QA Railway Website");
		log.info("2. Click on 'Login' tab");
		log.info("3. Enter valid Email and invalid Password");
		log.info("4. Click on 'Login' button");
		loginPage.login(Constant.INVALIDUSERNAME, Constant.INVALIDPASSWORD);
		
		//Verify Error message "There was a problem with your login and/or errors exist in your form." is displayed
		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		
		Assert.assertTrue(loginPage.getInvalidErrorIsVisible());
		Assert.assertEquals(errorMsg,Constant.LOGINWITHINVALIDPASSWORDERRORMSG);
		
		log.info("Expected Result: Login Failed");
	}
	
	@Test
	public void TC04() {
		
		log.info("TC04 - Login with wrong password many times");
		
		//Login with valid username and invalid password
		//Times of login: 5
		log.info("1. Navigate to QA Railway Website");
		log.info("2. Click on 'Login' tab");
		log.info("3. Enter valid information into 'Username' textbox except 'Password' textbox.");
		log.info("4. Click on 'Login' button");
		loginPage.loginFailManyTime(Constant.USERNAME, Constant.INVALIDPASSWORD, 5);

		//Verify: "Invalid username or password. Please try again" is shown
		Assert.assertTrue(loginPage.getInvalidErrorIsVisible());
		
		//Verify: User can't login and message "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes." appears.
		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		
		Assert.assertEquals(errorMsg,Constant.LOGGINFAILMANYTIMEERRORMSG);
		log.info("Error Message: Login in fail many times is displayed");
		log.info("Expected Result: Login failed");
	}
	
	@Test
	public void TC05() {
		log.info("TC05 - User can't login with an account hasn't been activated");
		
		//Pre-condition: a not-active account is existing
		//Login with no active account
		log.info("Pre-condition: a not-active account is existing");
		log.info("1. Navigate to QA Railway Website");
		log.info("2. Click on 'Login' tab");
		log.info("3. Enter username and password of account hasn't been activated.");
		log.info("4. Click on 'Login' button");
		loginPage.login(Constant.USERNAMENOTACTIVE, Constant.PASSWORDNOACTIVE);
		
		//Verify: User can't login and message "Invalid username or password. Please try again." appears.
		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		log.info("User can't login and message 'Invalid username or password. Please try again.' appears.");
		Assert.assertEquals(errorMsg,Constant.LOGINNOACTIVEACCOUNTERRORMSG);
		
	}
	
	@Test
	public void TC06() {
		log.info("TC06 - User is redirected to Home page after logging out");

		//Login with valid Email and Password
		log.info("1. Navigate to QA Railway Website");
		log.info("2. Login with valid Email and Password");
		loginPage.login(Constant.USERNAME, Constant.PASSWORD);
		
		//Navigate to FAQ tab
		log.info("3. Click on 'FAQ' tab");
		generalPage.gotoPage(RailwayPageTab.LOGIN);
		
		//LogOut Account
		log.info("4. Click on 'Log out' tab");
		generalPage.logOutAccount();
		
		//Verify: "Log out" tab is disappeared.
		Assert.assertFalse(generalPage.verifyLogOutAccount());
		log.info("Home page displays. 'Log out' tab is disappeared.");
		
	}
}