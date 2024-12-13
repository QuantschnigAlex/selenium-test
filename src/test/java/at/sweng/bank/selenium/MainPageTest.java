package at.sweng.bank.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageTest {
	
	private WebDriver driver;
	private String baseUrl;

	@BeforeEach
	public void setUp() {
		driver = new ChromeDriver();
		baseUrl = "http://127.0.0.1:62712/rap";
	}
	
	@Test
	public void testLayout() {
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		WebElement username = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text'][autocomplete='off']")));
		username.sendKeys("a");

		WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
		password.sendKeys("c");
	
	}
	
	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
