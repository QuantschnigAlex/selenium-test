package at.sweng.bank.selenium;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageTest extends BaseTest{

	@Test
	public void testLayout() throws InterruptedException {
		WebElement appImage = driver.findElement(By.cssSelector("div[style*='fed33f7e.png']"));
		WebElement customerInfo = driver.findElement(By.xpath(
				"//div[contains(., 'Kunde:') and contains(., 'Anton Benko') and contains(., 'Konto:') and contains(., 'AT022011100003429660')]"));
		WebElement reloadButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Reload']"));
		WebElement ueberweisungButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']"));
		WebElement logoutButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Logout']"));

		WebElement sendername = driver.findElement(By.xpath("//div[text()='Sendername']"));
		WebElement account = driver.findElement(By.xpath("//div[text()='Sender-Konto']"));
		WebElement empaenger = driver.findElement(By.xpath("//div[text()='Empfängername']"));
		WebElement empfaengerAccount = driver.findElement(By.xpath("//div[text()='Empfänger-Konto']"));
		WebElement betrag = driver.findElement(By.xpath("//div[text()='Betrag']"));

		assertTrue(customerInfo.isDisplayed(), "Customer info should be visible");
		assertTrue(reloadButton.isDisplayed());
		assertTrue(ueberweisungButton.isDisplayed());
		assertTrue(logoutButton.isDisplayed());
		assertTrue(appImage.isDisplayed());
		assertTrue(sendername.isDisplayed(), "sender name");
		assertTrue(account.isDisplayed(), "account");
		assertTrue(empfaengerAccount.isDisplayed(), "empfaenger account");
		assertTrue(empaenger.isDisplayed(), "empfaenger");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", betrag);
		Thread.sleep(500);
		assertTrue(betrag.isDisplayed(), "betrag");
	}

	@Test
	public void testLogout() {
		WebElement logoutButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Logout']"));
		logoutButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

		WebElement appicon = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[style*='e9f1fd4d.png']")));
		assertTrue(appicon.isDisplayed());
	}
}
