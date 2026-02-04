package Railway;

import Common.Utilities;
import Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {
	
	
	@Test
	public void TC01() {
		System.out.println("TC01-User can log into Railway with valid username and password");
		HomePage homePage = new HomePage();
		homePage.open();
		
		LoginPage loginPage = homePage.gotoLoginPage();
		
		loginPage.login(Constant.USERNAME, Constant.PASSWORD);
		System.out.println("Login Successfull");
		
		Assert.assertTrue(loginPage.isLoginSuccess());
	}
	
	@Test
	public void TC02() {
		System.out.println("TC02-User cannot login with blank Username");
		HomePage homePage = new HomePage();
		homePage.open();
		
		LoginPage loginPage = homePage.gotoLoginPage();
		
		loginPage.login(Constant.EMPTYUSERNAME, Constant.PASSWORD);
		System.out.println("Login Failed");
		
		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		
		Assert.assertTrue(loginPage.getInvalidErrorIsVisible());
		Assert.assertEquals(errorMsg,Constant.EMPTYACCOUNTERRORMSG);
	}
	
	@Test
	public void TC03() {
		System.out.println("TC03-User cannot log into Railway with invalid password ");
		HomePage homePage = new HomePage();
		homePage.open();
		
		LoginPage loginPage = homePage.gotoLoginPage();
		
		loginPage.login(Constant.EMPTYUSERNAME, Constant.PASSWORD);
		System.out.println("Login Failed");
		
		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		
		Assert.assertTrue(loginPage.getInvalidErrorIsVisible());
		Assert.assertEquals(errorMsg,Constant.EMPTYACCOUNTERRORMSG);
	}
	
	@Test
	public void TC04() {
		
		System.out.println("TC04 - Login with wrong password many times");
		HomePage homePage = new HomePage();
		homePage.open();
		
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.loginFailManyTime(Constant.USERNAME, Constant.PASSWORD, 5);

		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		Assert.assertTrue(loginPage.getInvalidErrorIsVisible());
		Assert.assertEquals(errorMsg,Constant.LOGGINFAILMANYTIMEERRORMSG);
		
	}
	
	@Test
	public void TC05() {
		System.out.println("TC05 - Login with wrong password many times");
		HomePage homePage = new HomePage();
		homePage.open();
		
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Constant.USERNAMENOTACTIVE, Constant.PASSWORDNOACTIVE);
		
		String errorMsg = loginPage.getLblLoginErrorMsg().getText();
		Assert.assertEquals(errorMsg,Constant.NOACTIVEACCOUNTLOGIN);
	}
}
