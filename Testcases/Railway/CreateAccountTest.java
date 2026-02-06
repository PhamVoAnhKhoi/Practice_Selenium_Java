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


public class CreateAccountTest extends BaseTest {
	
	private static final Logger log = LoggerFactory.getLogger(CreateAccountTest.class);
	
	LoginPage loginPage;
	GeneralPage generalPage;
	HomePage homePage;
	
	@BeforeMethod
	public void initalTest() {
		homePage = new HomePage();
		homePage.open();
		loginPage = homePage.gotoLoginPage();
		generalPage = new GeneralPage();
	}
	
	@Test
	public void TC07() {
		log.info("TC07-User can't create account with an already in-use email");
		//String email = Utilities.generateRandomEmailWithTimestamp();
	}
}