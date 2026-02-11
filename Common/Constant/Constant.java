package Constant;

import org.openqa.selenium.WebDriver;

public class Constant {
	public static WebDriver WEBDRIVER;
	
	//Set timeout
	public static final int TIMEOUT = 20;
	
	//Browser url
	public static final String RAILWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";
	public static final String RANDOMEMAIL_URL = "https://www.guerrillamail.com/";
	
	//=====================<Account>=====================
	//Valid Account
	public static final String USERNAME = "anhkhoiphamvo2909@gmail.com";
	public static final String PASSWORD = "123456789";
	
	//Invalid Account
	public static final String INVALIDUSERNAME="abc";
	public static final String INVALIDPASSWORD="123";
	
	//Empty Account
	public static final String EMPTYUSERNAME="";
	public static final String EMPTYPASSWORD="";
	
	//Account without actived
	public static final String USERNAMENOTACTIVE = "khoipham@gmail.com";
	public static final String PASSWORDNOACTIVE = "0123456789";
	
	//=====================<Website Status>=====================
	//LoginPage status
	public static final String LOGINWITHEMPTYACCOUNTERRORMSG = "There was a problem with your login and/or errors exist in your form.";
	public static final String LOGGINFAILMANYTIMEERRORMSG = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
	public static final String LOGINNOACTIVEACCOUNTERRORMSG = "Invalid username or password. Please try again.";
	public static final String LOGINWITHINVALIDPASSWORDERRORMSG = "There was a problem with your login and/or errors exist in your form.";
	
	//Register status
	public static final String REGISTEREMAILALREADYINUSEERRORMSG = "This email address is already in use.";
	public static final String REGISTEREMPTYACCOUNTERRORMSG = "There're errors in the form. Please correct the errors and try again.";
	public static final String REGISTEREMPTYPASSWORDERRORMSG = "Invalid password length.";
	public static final String REGISTEREMPTYPIDERRORMSG = "Invalid ID length.";
	
	public static final String REGISTERSTEP1SUCCESS = "Thank you for registering your account";
	public static final String REGISTERCONFIRMSUCESS = "Registration Confirmed! You can now log in to the site";
}
