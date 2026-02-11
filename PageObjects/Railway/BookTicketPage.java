package Railway;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Ticket;
import Common.Utilities;
import Constant.Constant;

public class BookTicketPage extends GeneralPage {
	
	//Locator
	private final By ddlDepartDate = By.xpath("//select[@name='Date']");
	private final By ddlDepartStation = By.xpath("//select[@name='DepartStation']");
	private final By ddlArriveStation = By.xpath("//select[@name='ArriveStation']");
	private final By ddlSeatType = By.xpath("//select[@name='SeatType']");
	private final By ddlTicketAmount = By.xpath("//select[@name='TicketAmount']");
	private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");
	private final By lblTicketBookedSuccessfully = By.xpath("//div[@id='content']/descendant::h1[contains(normalize-space(.),'Ticket booked successfully!')]");
	
	private final By tableHeaders = By.xpath("//table[contains(@class,'MyTable')]//tr[@class='TableSmallHeader']/th");
	private final By tableRows = By.xpath("//table[contains(@class,'MyTable')]//tr[@class='OddRow']");
	
	

	
	//Arrives station
	public WebElement getDDLArriveStation() {
		return Constant.WEBDRIVER.findElement(ddlArriveStation);
	}
	
	//Depart station
	public WebElement getDDLDepartStation() {
		return Constant.WEBDRIVER.findElement(ddlDepartStation);
	}
	
	//Seat Type
	public WebElement getDDLSeatType() {
		return Constant.WEBDRIVER.findElement(ddlSeatType);
	}
	
	//Ticket amount
	public WebElement getDDLTicketAmount() {
		return Constant.WEBDRIVER.findElement(ddlTicketAmount);
	}

	//Button book ticket
	public WebElement getBtnBookTicket() {
		Utilities.scrollToElement(Constant.WEBDRIVER.findElement(btnBookTicket));
		return Constant.WEBDRIVER.findElement(btnBookTicket);
	}
	
	public WebElement getLBLTicketBookedSuccessfully() {
		return Constant.WEBDRIVER.findElement(lblTicketBookedSuccessfully);
	}
	
	//Depart date
	public WebElement getDDLDepartDate() {
		return Constant.WEBDRIVER.findElement(ddlDepartDate);
	}
	
	public String getSelectedDepartDate() {

	    Select select = new Select(getDDLDepartDate());

	    return select.getFirstSelectedOption()
	                 .getText()
	                 .trim();
	}

	
	//Select Depart Date
	public void selectDepartDate(int plusDays) {
		Select select = new Select(Constant.WEBDRIVER.findElement(ddlDepartDate));
		
	    // 1. Take default selected option
	    String defaultDateText = select.getFirstSelectedOption().getText().trim();

	    // 2. Convert sang LocalDate
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
	    LocalDate defaultDate = LocalDate.parse(defaultDateText, formatter);

	    // 3. Plus day
	    LocalDate expectedDate = defaultDate.plusDays(plusDays);

	    // 4. Format string following format dropdown
	    String expectedDateText = expectedDate.format(formatter);

	    // 5. Select option following text input
	    select.selectByVisibleText(expectedDateText);
	}
	
	//Select option Depart Station
	public void selectOptionDepartStation(String option) {	
		
		Select depart = new Select(getDDLDepartStation());

	    // Storage option visible in ArriveStation
	    int oldSize = new Select(getDDLArriveStation()).getOptions().size();

	    depart.selectByVisibleText(option);

	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

	    // Wait for number of option change
	    wait.until(driver -> {
	        int newSize = new Select(getDDLArriveStation()).getOptions().size();
	        return newSize != oldSize;
	    });
	}
	
	//Select option Arrive Station
	public void selectOptionArriveStation(String option) {
		Utilities.wait.until(driver -> { 
			Select select = new Select(getDDLArriveStation());
	        return select.getOptions()
	                .stream()
	                .anyMatch(opt -> opt.getText().trim().equals(option));
		});
		
		Select select = new Select(getDDLArriveStation());
	    select.selectByVisibleText(option);
	}
	
	//Select option seat type
	public void selectOptionSeatType(String option) {
		Utilities.wait.until(driver -> { 
			Select select = new Select(getDDLSeatType());
	        return select.getOptions()
	                .stream()
	                .anyMatch(opt -> opt.getText().trim().equals(option));
		});
		
		Select select = new Select(getDDLSeatType());
	    select.selectByVisibleText(option);
	}
	
	//Select option ticket amount
	public void selectOptionTicketAmount(int option) {
		String optionText = String.valueOf(option);

	    Utilities.wait.until(driver -> { 
	        Select select = new Select(getDDLTicketAmount());
	        return select.getOptions()
	                .stream()
	                .anyMatch(opt -> opt.getText().trim().equals(optionText));
	    });

	    Select select = new Select(getDDLTicketAmount());
	    select.selectByVisibleText(optionText);
	}
	
	//Verify label Ticket booked successfully
	public boolean verifyLBLTicketBookedSuccessfullyIsDisplay() {
		Utilities.waitForVisible(lblTicketBookedSuccessfully);
		try {
			return getLBLTicketBookedSuccessfully().isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	
	private Map<String, Integer> getHeaderIndexMap() {

	    List<WebElement> headers =
	            Constant.WEBDRIVER.findElements(tableHeaders);

	    Map<String, Integer> headerMap = new HashMap<>();

	    for (int i = 0; i < headers.size(); i++) {
	        headerMap.put(headers.get(i).getText().trim(), i);
	        
	    }
	    log.info("HeaderMap: " + headerMap);
	    return headerMap;
	}

	
	public List<Ticket> getAllTickets() {

	    Utilities.waitForVisible(tableHeaders);

	    Map<String, Integer> headerMap = getHeaderIndexMap();

	    List<WebElement> rows =
	            Constant.WEBDRIVER.findElements(tableRows);

	    List<Ticket> tickets = new ArrayList<>();

	    for (WebElement row : rows) {

	        List<WebElement> cells =
	                row.findElements(By.tagName("td"));

	        Ticket ticket = new Ticket(
	                getCell(cells, headerMap, "Depart Station"),
	                getCell(cells, headerMap, "Arrive Station"),
	                getCell(cells, headerMap, "Seat Type"),
	                getCell(cells, headerMap, "Depart Date"),
	                getCell(cells, headerMap, "Book Date"),
	                getCell(cells, headerMap, "Expired Date"),
	                getCell(cells, headerMap, "Amount"),
	                getCell(cells, headerMap, "Total Price")
	        );

	        tickets.add(ticket);
	    }

	    return tickets;
	}


	
	private String getCell(List<WebElement> cells, Map<String, Integer> headerMap, String columnName) {
	
		Integer index = headerMap.get(columnName);
		
		if (index == null || index >= cells.size()) {
		return "";
		}
		
		return cells.get(index).getText().trim();
	}


}
	