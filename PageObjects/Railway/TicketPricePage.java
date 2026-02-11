package Railway;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketPricePage extends GeneralPage {

    private final By headerTitle =
            By.xpath("//table[contains(@class,'MedTable')]//th[@colspan]");

    private final By seatRow =
            By.xpath("//table[contains(@class,'MedTable')]//tr[2]/td");

    private final By priceRow =
            By.xpath("//table[contains(@class,'MedTable')]//tr[3]/td");

    public boolean isCorrectRoute(String depart, String arrive) {

        String expected =
                "Ticket price from "
                        + depart + " to " + arrive;

        return Constant.WEBDRIVER
                .findElement(headerTitle)
                .getText()
                .trim()
                .equals(expected);
    }

    public Map<String, String> getSeatPriceMap() {

        List<WebElement> seats =
                Constant.WEBDRIVER.findElements(seatRow);

        List<WebElement> prices =
                Constant.WEBDRIVER.findElements(priceRow);

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < seats.size(); i++) {
            map.put(
                    seats.get(i).getText().trim(),
                    prices.get(i).getText().trim()
            );
        }

        return map;
    }
}
