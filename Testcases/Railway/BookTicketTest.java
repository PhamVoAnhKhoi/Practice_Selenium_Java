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


public class BookTicketTest extends BaseTest {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterTest.class);
	RandomEmailPage randomEmailPage;
	RegisterPage registerPage;
	GeneralPage generalPage;
	HomePage homePage;
	LoginPage loginPage;
	BookTicketPage bookTicketPage;
	TimeTablePage timeTablePage;
	TicketPricePage ticketPricePage;
	
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
		timeTablePage = new TimeTablePage();
		ticketPricePage = new TicketPricePage();
		//Create new account
		log.info("Pre-condition: an actived account is existing");
		createRandomEmail();
		createAccout(account.getEmail(), account.getPassword(), account.getPassword(), account.getPID());
		confirmCreationRequest();
		switchToRailwayTab();
		log.info("1. Navigate to QA Railway Website");
	    homePage.open();

	    log.info("2. Login with valid account");
	    generalPage.gotoPage(RailwayPageTab.LOGIN);
	    loginPage.login(account.getEmail(), account.getPassword());
	    //loginPage.login(Constant.USERNAME, Constant.PASSWORD);
	}
	
	@Test
	public void TC12() {	   
	    
	    log.info("3. Navigate to Book Ticket page");
	    generalPage.gotoPage(RailwayPageTab.BOOKTICKET);

	    log.info("4. Select Depart Date (+2 days)");
	    bookTicketPage.selectDepartDate(2);
	    String expectedDepartDate = bookTicketPage.getSelectedDepartDate();

	    log.info("5. Select Depart & Arrive station");
	    String expectedDepart = "Nha Trang";
	    String expectedArrive = "Huế";
	    bookTicketPage.selectOptionDepartStation(expectedDepart);
	    bookTicketPage.selectOptionArriveStation(expectedArrive);

	    log.info("6. Select Seat Type");
	    String expectedSeat = "Soft bed with air conditioner";
	    bookTicketPage.selectOptionSeatType(expectedSeat);

	    log.info("7. Select Ticket Amount");
	    String expectedAmount = "1";
	    bookTicketPage.selectOptionTicketAmount(1);

	    log.info("8. Click Book Ticket");
	    
	    //Utilities.scrollToElement(bookTicketPage.getBtnBookTicket());
	    
	    bookTicketPage.getBtnBookTicket().click();

	    log.info("9. Verify success message");
	    Assert.assertTrue(bookTicketPage.verifyLBLTicketBookedSuccessfullyIsDisplay());

	    log.info("10. Verify booked ticket information in table");
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

	    List<Ticket> actualTickets = bookTicketPage.getAllTickets();

	    log.info("Expected: " + expectedTicket);
	    log.info("Actual: " + actualTickets);

	    Assert.assertTrue(actualTickets.contains(expectedTicket),
	            "Expected ticket is not found in table!");

	}
	
	@Test
	public void TC13() {
		
	    log.info("3. Navigate to Book Ticket page");
	    generalPage.gotoPage(RailwayPageTab.BOOKTICKET);
	
	    log.info("4. Select the next 25 days from 'Depart date'");
	    bookTicketPage.selectDepartDate(25);
	    String expectedDepartDate = bookTicketPage.getSelectedDepartDate();
	
	    log.info("5. Select Depart & Arrive station");
	    String expectedDepart = "Nha Trang";
	    String expectedArrive = "Sài Gòn";
	    bookTicketPage.selectOptionDepartStation(expectedDepart);
	    bookTicketPage.selectOptionArriveStation(expectedArrive);
	    
	    log.info("6. Select Seat Type");
	    String expectedSeat = "Soft bed with air conditioner";
	    bookTicketPage.selectOptionSeatType(expectedSeat);

	    log.info("7. Select Ticket Amount");
	    String expectedAmount = "5";
	    bookTicketPage.selectOptionTicketAmount(5);

	    log.info("8. Click Book Ticket");
	    
	    Utilities.scrollToElement(bookTicketPage.getBtnBookTicket());
	    
	    bookTicketPage.getBtnBookTicket().click();
	    
	    log.info("9. Verify success message");
	    Assert.assertTrue(bookTicketPage.verifyLBLTicketBookedSuccessfullyIsDisplay());

	    log.info("10. Verify booked ticket information in table");
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

	    List<Ticket> actualTickets = bookTicketPage.getAllTickets();

	    log.info("Expected: " + expectedTicket);
	    log.info("Actual: " + actualTickets);

	    Assert.assertTrue(actualTickets.contains(expectedTicket),
	            "Expected ticket is not found in table!");
	}
	
	@Test
	public void TC14() {
		log.info("3. Go to Timetable");
		generalPage.gotoPage(RailwayPageTab.TIMETABLE);
		
		log.info("4. Click check price from Đà Nẵng to Sài Gòn");
		timeTablePage.clickRoute(
	            "Đà Nẵng",
	            "Sài Gòn",
	            TimetableAction.CHECK_PRICE
	    );

	    Assert.assertTrue(
	            ticketPricePage.isCorrectRoute(
	                    "Đà Nẵng",
	                    "Sài Gòn")
	    );

	    Map<String, String> actual =
	            ticketPricePage.getSeatPriceMap();

	    Assert.assertEquals(actual.get("HS"), "310000");
	    Assert.assertEquals(actual.get("SS"), "335000");
	    Assert.assertEquals(actual.get("SSC"), "360000");
	    Assert.assertEquals(actual.get("HB"), "410000");
	    Assert.assertEquals(actual.get("SB"), "460000");
	    Assert.assertEquals(actual.get("SBC"), "510000");
	}
	
	@Test
	public void TC15() {
		log.info("3. Go to Timetable");
		generalPage.gotoPage(RailwayPageTab.TIMETABLE);
		
		log.info("4. Click on book ticket of route 'Quảng Ngãi' to 'Huế'");
		timeTablePage.clickRoute(
	            "Quảng Ngãi",
	            "Huế",
	            TimetableAction.BOOK_TICKET
	    );

	    bookTicketPage.selectDepartDate(1);
	    bookTicketPage.selectOptionTicketAmount(5);

	    bookTicketPage.getBtnBookTicket().click();

	    Assert.assertTrue(
	            bookTicketPage
	                    .verifyLBLTicketBookedSuccessfullyIsDisplay()
	    );
	}

	@Test
	public void TC16() {

	    log.info("3. Book a ticket");
	    generalPage.gotoPage(RailwayPageTab.BOOKTICKET);

	    bookTicketPage.selectDepartDate(1);
	    String departDate = bookTicketPage.getSelectedDepartDate();

	    String depart = "Nha Trang";
	    String arrive = "Huế";
	    String seat = "Soft bed with air conditioner";
	    String amount = "1";

	    bookTicketPage.selectOptionDepartStation(depart);
	    bookTicketPage.selectOptionArriveStation(arrive);
	    bookTicketPage.selectOptionSeatType(seat);
	    bookTicketPage.selectOptionTicketAmount(1);

	    bookTicketPage.getBtnBookTicket().click();

	    Assert.assertTrue(
	            bookTicketPage.verifyLBLTicketBookedSuccessfullyIsDisplay()
	    );

	    Ticket expectedTicket = new Ticket(
	            depart,
	            arrive,
	            seat,
	            departDate,
	            null,
	            null,
	            amount,
	            null
	    );

	    log.info("4. Go to My Ticket page");
	    generalPage.gotoPage(RailwayPageTab.MYTICKET);

	    MyTicketPage myTicketPage = new MyTicketPage();

	    Assert.assertTrue(
	            myTicketPage.isTicketPresent(expectedTicket)
	    );

	    log.info("5. Cancel ticket");
	    myTicketPage.cancelTicket(expectedTicket);

	    log.info("6. Verify ticket disappeared");

	    Assert.assertFalse(
	            myTicketPage.isTicketPresent(expectedTicket),
	            "Ticket was not removed!"
	    );
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