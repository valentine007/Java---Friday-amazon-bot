package amazon.service;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BotService {
	
	private static final String BASE_URL = "https://www.amazon.com";
	
	public static WebDriver getFirefoxDriver() {
		String mainDir = System.getProperty("user.dir");
		String sep = System.getProperty("file.separator");
		String driverPath = mainDir + sep + "libs" + sep + "geckodriver.exe"; // ���� � ����������-��������

		System.setProperty("webdriver.gecko.driver", driverPath);

		FirefoxOptions options = new FirefoxOptions();

		// options.addArguments("--start-maximized");
		// DesiredCapabilities capabilities = new DesiredCapabilities();
		// capabilities.setCapability(capabilityName, value);

		WebDriver driver = new FirefoxDriver(options);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

		// �������� �������� ��������
		Timer.waitSec(5);
		driver.manage().window().maximize(); //������������ ����

		return driver;
	}
	
public static WebDriver registerAccount(WebDriver driver, Account account) {
		
		//������� �� �������� ������
		driver.get(BASE_URL);
		Timer.waitSec(5);
		
		//����� ��������
		WebElement registerDivElement = driver.findElement(By.id("nav-flyout-ya-newCust"));
		WebElement registerLinkElement = registerDivElement.findElement(By.tagName("a"));
		String registerLink = registerLinkElement.getAttribute("href");
		
		driver.get(registerLink);
		Timer.waitSec(5);
		
		//��������� ����� �����������
		WebElement inputNameElement = driver.findElement(By.id("ap_customer_name"));
		inputNameElement.sendKeys(account.getFirstName() + " " + account.getSecondName());
		Timer.waitSec(5);
		
		WebElement inputEmailElement = driver.findElement(By.id("ap_email"));
		inputEmailElement.sendKeys(account.getEmail());
		Timer.waitSec(5);
		
		WebElement inputPassworsElement = driver.findElement(By.id("ap_password"));
		inputPassworsElement.sendKeys(account.getPassowrd());
		Timer.waitSec(5);
		
		WebElement inputChecklElement = driver.findElement(By.id("ap_password_check"));
		inputChecklElement.sendKeys(account.getPassowrd());
		Timer.waitSec(5);
		
		WebElement inputSubmitlElement = driver.findElement(By.id("continue"));
		inputSubmitlElement.submit();
		
		//������������ ����� �� �������
		Timer.waitSec(5);
		String currentURL = driver.getCurrentUrl();
		driver.get(currentURL);																	//��������� DOM � ���� �������
		
		if(driver.getPageSource().contains("Hello, "))
			return driver;
		
		driver.quit();
		return null;
	}
	
	public static WebDriver addGoodToCart(WebDriver driver, String asin) {
		
		return null;
	}

}
