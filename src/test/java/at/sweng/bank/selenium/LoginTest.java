package at.sweng.bank.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class LoginTest {
	private WebDriver driver;
	private String baseUrl;

	@BeforeEach
	public void setUp() {
		driver = new ChromeDriver();
		baseUrl = "http://127.0.0.1:62712/rap";
	}

	@Test
	public void testLoginWithValidCredentials() {
		driver.get(baseUrl);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

		WebElement username = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text'][autocomplete='off']")));
		username.sendKeys("a");

		WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
		password.sendKeys("b");

		WebElement loginButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Login']"));
		loginButton.click();

		WebElement transferButton = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']")));
		assertTrue(transferButton.isDisplayed());
	}
	
	@Test
	public void testLoginWithInvalidCredentials() {
		driver.get(baseUrl);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

		WebElement username = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text'][autocomplete='off']")));
		username.sendKeys("a");

		WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
		password.sendKeys("c");

		WebElement loginButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Login']"));
		loginButton.click();

		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//div[contains(text(), 'Falscher Username oder Passwort') " +
		                     "and contains(@style, 'position: absolute') " +
		                     "and contains(@style, 'overflow: hidden')]")
		        ));
		
		assertTrue(errorMessage.isDisplayed());   
	}
	
	@Test
	public void testClearLogin() {
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		WebElement username = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text'][autocomplete='off']")));
		username.sendKeys("a");

		WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
		password.sendKeys("c");
		
		WebElement abbrechenButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Abbrechen']"));
		abbrechenButton.click();
	
		assertTrue(password.getText().isEmpty());
		assertTrue(username.getText().isEmpty());
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
