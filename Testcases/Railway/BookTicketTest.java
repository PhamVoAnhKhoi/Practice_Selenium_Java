package Railway;

import Common.Utilities;
import Common.Account;
import Common.Ticket;
import Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WindowType;


public class BookTicketTest extends BaseTest {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterTest.class);
	RandomEmailPage randomEmailPage;
	RegisterPage registerPage;
	GeneralPage generalPage;
	HomePage homePage;
	LoginPage loginPage;
	BookTicketPage bookTicketPage;
	
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
		bookTicketPage = new BookTicketPage();
		
		//Create new account
		log.info("Pre-condition: an actived account is existing");
//		createRandomEmail();
//		createAccout(account.getEmail(), account.getPassword(), account.getPassword(), account.getPID());
//		confirmCreationRequest();
//		switchToRailwayTab();
	}
	
	@Test
	public void TC12() {

	    log.info("1. Navigate to QA Railway Website");
	    homePage.open();

	    log.info("2. Login with valid account");
	    generalPage.gotoLoginPage();
	    //loginPage.login(account.getEmail(), account.getPassword());
	    loginPage.login(Constant.USERNAME, Constant.PASSWORD);
	    
	    log.info("3. Navigate to Book Ticket page");
	    generalPage.gotoBookTicketPage();

	    log.info("4. Select Depart Date (+2 days)");
	    bookTicketPage.selectDepartDate(2);
	    String expectedDepartDate = bookTicketPage.getSelectedDepartDate();

	    log.info("5. Select Depart & Arrive station");
	    String expectedDepart = "Nha Trang";
	    String expectedArrive = "Sài Gòn";
	    bookTicketPage.selectOptionDepartStation(expectedDepart);
	    bookTicketPage.selectOptionArriveStation(expectedArrive);

	    log.info("6. Select Seat Type");
	    String expectedSeat = "Hard bed";
	    bookTicketPage.selectOptionSeatType(expectedSeat);

	    log.info("7. Select Ticket Amount");
	    String expectedAmount = "1";
	    bookTicketPage.selectOptionTicketAmount(1);

	    log.info("8. Click Book Ticket");
	    
	    Utilities.scrollToElement(bookTicketPage.getBtnBookTicket());
	    
	    bookTicketPage.getBtnBookTicket().click();

	    log.info("9. Verify success message");
	    Assert.assertTrue(bookTicketPage.verifyLBLTicketBookedSuccessfullyIsDisplay());

	    log.info("10. Verify booked ticket information in table");

	    log.info("Result: ");
	    log.info("expectedDepart: " + expectedDepart);
	    log.info("expectedArrive: " + expectedArrive);
	    log.info("expectedSeat: " + expectedSeat);
	    log.info("expectedDepartDate: " + expectedDepartDate);
	    log.info("expectedAmount: " + expectedAmount);
	    
	    Ticket expectedTicket = new Ticket(
	            expectedDepart,
	            expectedArrive,
	            expectedSeat,
	            expectedDepartDate,
	            null,
	            null,
	            expectedAmount,
	            null
	    );

	    Ticket actualTicket = bookTicketPage.getBookedTicket();

	    log.info("Expected: " + expectedTicket);
	    log.info("Actual: " + actualTicket);

	    Assert.assertEquals(actualTicket, expectedTicket);
	}

	
	public void createAccout(String email, String password, String confirmPassword, String pid) {
        Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);

        railwayTab = Constant.WEBDRIVER.getWindowHandle();
        log.info("Railway Tab: " + railwayTab);
        
        generalPage.gotoRegisterPage();
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
        randomEmailPage.confirmCreatedAccountByEmail();
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