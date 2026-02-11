package Common;

import org.openqa.selenium.By;

public enum RailwayPageTab {

    LOGIN("Login"),
    LOGOUT("Logout"),
    REGISTER("Register"),
    FAQ("FAQ"),
    BOOKTICKET("Book ticket");

    private final String tabName;

    RailwayPageTab(String tabName) {
        this.tabName = tabName;
    }

    public By getLocator() {
        return By.xpath("//div[@id='menu']//a[normalize-space()='" + tabName + "']");
    }
}
