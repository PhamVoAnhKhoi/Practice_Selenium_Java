package Common;

import Common.Ticket;
import Common.Utilities;
import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

public class TicketTableComponent {

    private final By tableHeaders =
            By.xpath("//table[contains(@class,'MyTable')]//tr[@class='TableSmallHeader']/th");

    private final By tableRows =
            By.xpath("//table[contains(@class,'MyTable')]//tr[contains(@class,'Row')]");

    private final By btnCancel =
            By.xpath("//input[@value='Cancel']");

    private Map<String, Integer> getHeaderIndexMap() {

        Utilities.waitForVisible(tableHeaders);

        List<WebElement> headers =
                Constant.WEBDRIVER.findElements(tableHeaders);

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < headers.size(); i++) {
            map.put(headers.get(i).getText().trim(), i);
        }

        return map;
    }

    public List<Ticket> getAllTickets() {

        Map<String, Integer> headerMap = getHeaderIndexMap();

        List<WebElement> rows =
                Constant.WEBDRIVER.findElements(tableRows);

        List<Ticket> tickets = new ArrayList<>();

        for (WebElement row : rows) {

            List<WebElement> cells =
                    row.findElements(By.tagName("td"));

            if (cells.isEmpty()) continue;

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

    public boolean isTicketPresent(Ticket ticket) {
        return getAllTickets().contains(ticket);
    }

    public void cancelTicket(Ticket ticket) {

        int rowCountBefore =
                Constant.WEBDRIVER.findElements(tableRows).size();

        WebElement cancelButton = findCancelButton(ticket);

        Utilities.scrollToElement(cancelButton);

        Utilities.wait.until(
                ExpectedConditions.elementToBeClickable(cancelButton)
        );

        cancelButton.click();

        // Wait alert appear
        Utilities.wait.until(ExpectedConditions.alertIsPresent());

        // Accept confirm popup
        Constant.WEBDRIVER.switchTo().alert().accept();

        // Wait until row count decrease
        Utilities.wait.until(driver ->
                Constant.WEBDRIVER.findElements(tableRows).size()
                        < rowCountBefore
        );
    }

    private WebElement findCancelButton(Ticket expectedTicket) {

        Map<String, Integer> headerMap = getHeaderIndexMap();

        List<WebElement> rows =
                Constant.WEBDRIVER.findElements(tableRows);

        for (WebElement row : rows) {

            List<WebElement> cells =
                    row.findElements(By.tagName("td"));

            if (cells.isEmpty()) continue;

            Ticket actualTicket = new Ticket(
                    getCell(cells, headerMap, "Depart Station"),
                    getCell(cells, headerMap, "Arrive Station"),
                    getCell(cells, headerMap, "Seat Type"),
                    getCell(cells, headerMap, "Depart Date"),
                    getCell(cells, headerMap, "Book Date"),
                    getCell(cells, headerMap, "Expired Date"),
                    getCell(cells, headerMap, "Amount"),
                    getCell(cells, headerMap, "Total Price")
            );

            if (actualTicket.equals(expectedTicket)) {

                return row.findElement(btnCancel);
            }
        }

        throw new RuntimeException(
                "Cancel button not found for ticket: " + expectedTicket
        );
    }

    private String getCell(List<WebElement> cells,
                           Map<String, Integer> headerMap,
                           String columnName) {

        Integer index = headerMap.get(columnName);

        if (index == null || index >= cells.size())
            return "";

        return cells.get(index).getText().trim();
    }
}
