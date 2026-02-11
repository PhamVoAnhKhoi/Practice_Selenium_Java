package Common;

import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class Utilities {
	// Get project root path
	public static String getProjectPath() {
	        return Paths.get("").toAbsolutePath().toString();
    }
	
	// Generate random string
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }
    
    // Generate random email
    public static String generateRandomEmail() {
        return "user" + UUID.randomUUID().toString().substring(0, 5) + "@mail.com";
    }

    // Generate random number

    public static String generateRandomNumber() {
        Random random = new Random();
        int number = 10000000 + random.nextInt(90000000);
        return String.valueOf(number);
    }

    
    public static String generateRandomEmailWithTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return "user" + timestamp + "@mail.com";
    }
    
public static WebDriverWait wait = 
	        new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(20));

    	public static WebElement waitForVisible(By locator) {
	        return wait.until(
	            ExpectedConditions.visibilityOfElementLocated(locator)
	        );
	    }
	    
	    public static WebElement waitForClickable(By locator) {
	        return wait.until(
	            ExpectedConditions.elementToBeClickable(locator)
	        );
	    }

	    public static boolean waitForInvisible(By locator) {
	        return wait.until(
	            ExpectedConditions.invisibilityOfElementLocated(locator)
	        );
	    }
	    
	    public static void scrollToElement(WebElement element) {
	    	JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
	    	js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	    }
}