package Railway;

import Common.Utilities;
import Common.Account;
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
	public void TC07_Register_New_Account() {

		createAccout();
		log.info("Create account successfully");
		homePage.open();
		generalPage.navigateToRegisterPage();
		
		//generalPage.gotoRegisterPage();
		log.info("Navigate to Register PAge");
		registerPage.registerAccount(account.getEmail(), account.getPassword(), account.getPassword(), account.getPID());
        
		
		
	}
	
	public void createAccout() {
		// ===== STEP 1: Mở Tab 1 - Random Email =====
        Constant.WEBDRIVER.get(Constant.RANDOMEMAIL_URL);
        randomEmailTab = Constant.WEBDRIVER.getWindowHandle();
        log.info("Random Email Tab: " + randomEmailTab);

        emailName = Utilities.generateRandomString(6);
		account = randomEmailPage.generateAccount(emailName);
        
        

        log.info("Generated Email: " + account.getEmail());
        log.info("Generated Password: " + account.getPassword());

        // ===== STEP 2: Mở Tab 2 - Railway =====
        Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);

        railwayTab = Constant.WEBDRIVER.getWindowHandle();
        log.info("Railway Tab: " + railwayTab);
        
        generalPage.gotoRegisterPage();
        registerPage.registerAccount(account.getEmail(), account.getPassword(), account.getPassword(), account.getPID());
        
        // ===== STEP 3: Switch lại tab Random (nếu cần kiểm tra) =====
        Constant.WEBDRIVER.switchTo().window(randomEmailTab);
        log.info("Switched back to Random Email tab");
        generalPage.closeAdIfPresent();
        randomEmailPage.confirmCreatedAccountByEmail();
	}
}