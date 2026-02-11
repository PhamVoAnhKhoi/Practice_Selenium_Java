package Railway;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.TimetableAction;
import Common.Utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeTablePage extends GeneralPage {

    private final By tableHeaders =
            By.xpath("//table[contains(@class,'MyTable')]//thead//th");

    private final By tableRows =
            By.xpath("//table[contains(@class,'MyTable')]//tbody//tr");

    /**
     * Build dynamic header index map
     */
    private Map<String, Integer> getHeaderIndexMap() {

        List<WebElement> headers =
                Constant.WEBDRIVER.findElements(tableHeaders);

        Map<String, Integer> headerMap = new HashMap<>();

        for (int i = 0; i < headers.size(); i++) {
            headerMap.put(headers.get(i).getText().trim(), i);
        }

        return headerMap;
    }

    public void clickRoute(String depart,
                           String arrive,
                           TimetableAction action) {

        Map<String, Integer> headerMap = getHeaderIndexMap();

        List<WebElement> rows =
                Constant.WEBDRIVER.findElements(tableRows);

        for (WebElement row : rows) {

            List<WebElement> cells =
                    row.findElements(By.tagName("td"));

            if (cells.isEmpty()) continue;

            String departText =
                    cells.get(headerMap.get("Depart Station"))
                            .getText().trim();

            String arriveText =
                    cells.get(headerMap.get("Arrive Station"))
                            .getText().trim();

            if (departText.equals(depart)
                    && arriveText.equals(arrive)) {

            	Utilities.scrollToElement(cells.get(headerMap.get(action.getColumnName()))
                        .findElement(By.tagName("a")));
            	
                cells.get(headerMap.get(action.getColumnName()))
                        .findElement(By.tagName("a"))
                        .click();

                return;
            }
        }

        throw new RuntimeException(
                "Route not found: "
                        + depart + " -> " + arrive);
    }
}
