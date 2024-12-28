package at.sweng.bank.selenium;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

class DialogTest extends BaseTest {

	@Test
	void testTransferDialogElements() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement ueberweisungButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']"));
		ueberweisungButton.click();

		WebElement dialog = driver.findElement(By.xpath(
				"//div[contains(@style, 'position: fixed')]//div[text()='Neue Überweisung']/ancestor::div[contains(@style, 'position: fixed')]"));

		WebElement dialogTitle = dialog.findElement(By.xpath(".//div[text()='Neue Überweisung']"));
		
		assertTrue(dialogTitle.isDisplayed(), "Dialog-Titel sollte sichtbar sein");
		assertTrue(dialog.findElement(By.cssSelector("div[style*='4cdd7012.png']")).isDisplayed());
		assertTrue(dialog.findElement(By.xpath(".//div[text()='Sendername']")).isDisplayed());
		assertTrue(dialog.findElement(By.xpath(".//div[text()='Sender-Konto']")).isDisplayed());
		assertTrue(dialog.findElement(By.xpath(".//div[text()='Empfängername']")).isDisplayed());
		assertTrue(dialog.findElement(By.xpath(".//div[text()='Empfänger-Konto']")).isDisplayed());
		assertTrue(dialog.findElement(By.xpath(".//div[text()='Betrag']")).isDisplayed());
		
		List<WebElement> inputFields = dialog.findElements(By.cssSelector("input[type='text']"));
	    assertEquals(5, inputFields.size(), "Es sollten 5 Eingabefelder vorhanden sein");

	    assertTrue(inputFields.get(0).getAttribute("readonly") != null, 
	        "Sendername-Feld sollte readonly sein");
	    assertTrue(inputFields.get(1).getAttribute("readonly") != null, 
	        "Sender-Konto-Feld sollte readonly sein");

	    assertTrue(dialog.findElement(By.xpath(".//div[text()='Ok']")).isDisplayed(), 
	        "OK-Button sollte sichtbar sein");
	    assertTrue(dialog.findElement(By.xpath(".//div[text()='Abbrechen']")).isDisplayed(), 
	        "Abbrechen-Button sollte sichtbar sein");
	    
	    assertTrue(dialog.findElement(By.xpath(".//div[text()='Ok']")).isEnabled(), 
	        "OK-Button sollte aktiviert sein");
	    assertTrue(dialog.findElement(By.xpath(".//div[text()='Abbrechen']")).isEnabled(), 
	        "Abbrechen-Button sollte aktiviert sein");
	}
	
	
	@Test
	void testTransferDialogCloseButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement ueberweisungButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']"));
		ueberweisungButton.click();

		WebElement dialog = driver.findElement(By.xpath(
				"//div[contains(@style, 'position: fixed')]//div[text()='Neue Überweisung']/ancestor::div[contains(@style, 'position: fixed')]"));
		WebElement closeButton = dialog.findElement(By.cssSelector("div[style*='4cdd7012.png']"));
		
		closeButton.click();
		
		assertFalse(dialog.isDisplayed());
		
	}

}
