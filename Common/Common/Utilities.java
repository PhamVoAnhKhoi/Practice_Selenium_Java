package Common;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

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
}
