package at.sweng.bank.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import at.sweng.bank.login.LoginModel;
import at.sweng.bank.login.User;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class LoginTest {
	private WebDriver driver;
	private String baseUrl;

	@BeforeEach
	public void setUp() {
		driver = new ChromeDriver();
		baseUrl = "http://127.0.0.1:64579/rap";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	}

	@Test
	public void testLoginView() {
		driver.get(baseUrl);

		WebElement appImage = driver.findElement(By.cssSelector("div[style*='e9f1fd4d.png']"));
		WebElement formTitle = driver.findElement(By.xpath("//div[text()='Anmeldung']"));
		WebElement userNameLable = driver.findElement(By.xpath("//div[text()='Username']"));
		WebElement passwordLable = driver.findElement(By.xpath("//div[text()='Passwort']"));
		WebElement username = driver.findElement(By.cssSelector("input[type='text'][autocomplete='off']"));
		WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
		WebElement loginButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Login']"));
		WebElement abbrechenButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Abbrechen']"));

		assertTrue(appImage.isDisplayed());
		assertTrue(formTitle.isDisplayed());
		assertTrue(userNameLable.isDisplayed());
		assertTrue(passwordLable.isDisplayed());
		assertTrue(username.isDisplayed());
		assertTrue(password.isDisplayed());
		assertTrue(loginButton.isDisplayed());
		assertTrue(abbrechenButton.isDisplayed());
	}

	@Test
	public void testLoginWithValidCredentials() {
		driver.get(baseUrl);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

		WebElement username = driver.findElement(By.cssSelector("input[type='text'][autocomplete='off']"));
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
	public void testLoginWithUppercaseUsername() {
		driver.get(baseUrl);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

		WebElement username = driver.findElement(By.cssSelector("input[type='text'][autocomplete='off']"));
		username.sendKeys("A");

		WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
		password.sendKeys("b");

		WebElement loginButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Login']"));
		loginButton.click();

		WebElement transferButton = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']")));
		assertFalse(transferButton.isDisplayed());
	}

	@Test
	public void testLoginWithEnterKey() {
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

		WebElement username = driver.findElement(By.cssSelector("input[type='text'][autocomplete='off']"));
		username.sendKeys("a");

		WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
		password.sendKeys("b");
		password.click();
		password.sendKeys(Keys.ENTER);

		WebElement transferButton = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']")));
		assertTrue(transferButton.isDisplayed());
	}

	@Test
	public void testLoginModelVerification() {
		LoginModel loginModel = new LoginModel();

		// Test für erfolgreichen Login
		assertTrue(loginModel.verifyUser("a", "b"));
		assertNotNull(loginModel.getLogin());
		assertEquals("a", loginModel.getLogin().getUsername());

		// Prüfen ob User-Objekt korrekt gefüllt ist
		User loggedInUser = loginModel.getLogin();
		assertNotNull(loggedInUser.getEmail());
		assertNotNull(loggedInUser.getVorname());
		assertNotNull(loggedInUser.getNachname());
		assertNotNull(loggedInUser.getAccount());
	}

	@Test
	public void testLoginWithInvalidCredentials() {
		driver.get(baseUrl);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

		WebElement username = driver.findElement(By.cssSelector("input[type='text'][autocomplete='off']"));
		username.sendKeys("b");

		WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
		password.sendKeys("a");

		WebElement loginButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Login']"));
		loginButton.click();

		WebElement errorMessage = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Falscher Username oder Passwort') "
						+ "and contains(@style, 'position: absolute') "
						+ "and contains(@style, 'overflow: hidden')]")));

		assertTrue(errorMessage.isDisplayed());
	}

	@Test
	public void testLoginWithoutCredentials() {
		driver.get(baseUrl);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

		WebElement loginButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Login']"));
		loginButton.click();

		WebElement errorMessage = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Falscher Username oder Passwort') "
						+ "and contains(@style, 'position: absolute') "
						+ "and contains(@style, 'overflow: hidden')]")));

		assertTrue(errorMessage.isDisplayed());
	}

	@Test
	public void testClearLogin() {
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		WebElement username = driver.findElement(By.cssSelector("input[type='text'][autocomplete='off']"));
		username.sendKeys("a");

		WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
		password.sendKeys("b");

		WebElement abbrechenButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Abbrechen']"));
		abbrechenButton.click();

		assertTrue(password.getText().isEmpty());
		assertTrue(username.getText().isEmpty());
	}

	@Test
	public void testTabNavigation() {
		driver.get(baseUrl);
		driver.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Login']"));
		driver.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Abbrechen']"));
		driver.findElement(By.cssSelector("input[type='text'][autocomplete='off']"));
		driver.findElement(By.cssSelector("input[type='password']"));

		WebElement activeElement = driver.switchTo().activeElement();

		assertTrue(activeElement.getAttribute("type").equals("text"));

		for (int i = 0; i < 3; i++) {
			new Actions(driver).sendKeys(Keys.TAB).perform();
			activeElement = driver.switchTo().activeElement();

			switch (i) {
			case 0:
				assertTrue(activeElement.getAttribute("type").equals("password"));
				break;
			case 1:
				assertTrue(activeElement.getText().equals("Login"));
				break;
			case 2:
				assertTrue(activeElement.getText().equals("Abbrechen"));
				break;
			}
		}
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
