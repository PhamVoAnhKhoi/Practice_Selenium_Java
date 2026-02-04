package Constant;

import org.openqa.selenium.WebDriver;

public class Constant {
	public static WebDriver WEBDRIVER;
	public static final String RAILWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";
	
	public static final String USERNAME = "anhkhoiphamvo2909@gmail.com";
	public static final String PASSWORD = "123456789";
	public static final String EMPTYUSERNAME="";
	public static final String EMPTYPASSWORD="";
	public static final String INVALIDUSERNAME="";
	public static final String INVALIDPASSWORD="";
	
	public static final String USERNAMENOTACTIVE = "khoipham@gmail.com";
	public static final String PASSWORDNOACTIVE = "0123456789";
	
	public static final String EMPTYACCOUNTERRORMSG = "There was a problem with your login and/or errors exist in your form.";
	public static final String LOGGINFAILMANYTIMEERRORMSG = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
	public static final String NOACTIVEACCOUNTLOGIN = "Invalid username or password. Please try again.";

}
