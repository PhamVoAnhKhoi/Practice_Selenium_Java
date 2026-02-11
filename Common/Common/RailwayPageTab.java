package Common;

import org.openqa.selenium.By;

import Railway.BookTicketPage;
import Railway.FAQPage;
import Railway.GeneralPage;
import Railway.HomePage;
import Railway.LoginPage;
import Railway.MyTicketPage;
import Railway.RegisterPage;
import Railway.TimeTablePage;

public enum RailwayPageTab {

    LOGIN("Login") {
        public GeneralPage getPage() { return new LoginPage(); }
    },
    REGISTER("Register") {
        public GeneralPage getPage() { return new RegisterPage(); }
    },
    FAQ("FAQ") {
        public GeneralPage getPage() { return new FAQPage(); }
    },
    BOOKTICKET("Book ticket") {
        public GeneralPage getPage() { return new BookTicketPage(); }
    },
    LOGOUT("Logout") {
        public GeneralPage getPage() { return new HomePage(); }
    },
	TIMETABLE("Timetable"){
		public GeneralPage getPage() { return new TimeTablePage();}
	},
	MYTICKET("My ticket"){
		public GeneralPage getPage() { return new MyTicketPage();}
	};

    private final String tabName;

    RailwayPageTab(String tabName) {
        this.tabName = tabName;
    }

    public By getLocator() {
        return By.xpath("//div[@id='menu']//a[normalize-space()='" + tabName + "']");
    }

    public abstract GeneralPage getPage();
}
