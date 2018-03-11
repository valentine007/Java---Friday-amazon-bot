package amazon.service;

import org.openqa.selenium.WebDriver;

public class BotRunner {
	public static void main(String[] args) {
		
		WebDriver driver = BotService.getFirefoxDriver();
		
		Account account = new Account("Valentina.Demc123@gmail.com", "123456789q", "Valia", "Demchenko");
		driver = BotService.registerAccount(driver, account);
		
		if (driver != null) {
			driver = BotService.addGoodToCart(driver, "");
			driver.quit();
			}
		}
	}