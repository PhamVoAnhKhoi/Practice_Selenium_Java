package Railway;

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
		loginPage = homePage.gotoLoginPage();
		generalPage = new GeneralPage();
	}
	
	@Test
	public void TC01() {
		log.info("TC01-User can log into Railway with valid username and password");
		
		loginPage.login(Constant.USERNAME, Constant.PASSWORD);
		log.info("Login Successfull");
		
		Assert.assertTrue(loginPage.isLoginSuccess());
	}
	
	@Test
	public void TC02() {
		log.info("TC02-User cannot login with blank Username");
		
		loginPage.login(Constant.EMPTYUSERNAME, Constant.PASSWORD);
		log.info("Login Failed");
		
		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		
		Assert.assertTrue(loginPage.getInvalidErrorIsVisible());
		Assert.assertEquals(errorMsg,Constant.EMPTYACCOUNTERRORMSG);
	}
	
	@Test
	public void TC03() {
		log.info("TC03-User cannot log into Railway with invalid password ");
		
		loginPage.login(Constant.EMPTYUSERNAME, Constant.PASSWORD);
		log.info("Login Failed");
		
		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		
		Assert.assertTrue(loginPage.getInvalidErrorIsVisible());
		Assert.assertEquals(errorMsg,Constant.EMPTYACCOUNTERRORMSG);
	}
	
	@Test
	public void TC04() {
		
		log.info("TC04 - Login with wrong password many times");
		
		loginPage.loginFailManyTime(Constant.USERNAME, Constant.PASSWORD, 5);

		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		Assert.assertTrue(loginPage.getInvalidErrorIsVisible());
		Assert.assertEquals(errorMsg,Constant.LOGGINFAILMANYTIMEERRORMSG);
		
	}
	
	@Test
	public void TC05() {
		log.info("TC05 - Login with wrong password many times");
		
		loginPage.login(Constant.USERNAMENOTACTIVE, Constant.PASSWORDNOACTIVE);
		
		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		Assert.assertEquals(errorMsg,Constant.NOACTIVEACCOUNTLOGIN);
	}
	
	@Test
	public void TC06() {
		log.info("TC06 - User is redirected to Home page after logging out");
		
		loginPage.login(Constant.USERNAME, Constant.PASSWORD);
		generalPage.gotoFAQPage();
		generalPage.logOutAccount();
		
		Boolean result = generalPage.verifyLogOutAccount();
		log.info("Result: "+ result);
		
		Assert.assertFalse(generalPage.verifyLogOutAccount());
		
	}
}
