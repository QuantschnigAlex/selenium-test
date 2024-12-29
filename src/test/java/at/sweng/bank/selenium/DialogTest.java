package at.sweng.bank.selenium;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class DialogTest extends BaseTest {

	@Test
	void testTransferDialogElements() {
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

		assertTrue(inputFields.get(0).getAttribute("readonly") != null, "Sendername-Feld sollte readonly sein");
		assertTrue(inputFields.get(1).getAttribute("readonly") != null, "Sender-Konto-Feld sollte readonly sein");

		assertTrue(dialog.findElement(By.xpath(".//div[text()='Ok']")).isDisplayed(), "OK-Button sollte sichtbar sein");
		assertTrue(dialog.findElement(By.xpath(".//div[text()='Abbrechen']")).isDisplayed(),
				"Abbrechen-Button sollte sichtbar sein");

		assertTrue(dialog.findElement(By.xpath(".//div[text()='Ok']")).isEnabled(), "OK-Button sollte aktiviert sein");
		assertTrue(dialog.findElement(By.xpath(".//div[text()='Abbrechen']")).isEnabled(),
				"Abbrechen-Button sollte aktiviert sein");
	}

	@Test
	void testTransferDialogCloseButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

		WebElement ueberweisungButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']"));
		ueberweisungButton.click();

		WebElement dialog = driver.findElement(By.xpath(
				"//div[contains(@style, 'position: fixed')]//div[text()='Neue Überweisung']/ancestor::div[contains(@style, 'position: fixed')]"));

		WebElement closeButton = dialog.findElement(
				By.xpath("//div[contains(@style, 'background: url') and contains(@style, '4cdd7012.png')]"));
		closeButton.click();

		boolean dialogClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[contains(@style, 'position: fixed')]//div[text()='Neue Überweisung']")));

		assertTrue(dialogClosed, "Dialog sollte geschlossen sein");

	}

	@Test
	void testTransferDialogAbbrechenButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

		WebElement ueberweisungButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']"));
		ueberweisungButton.click();

		WebElement dialog = driver.findElement(By.xpath(
				"//div[contains(@style, 'position: fixed')]//div[text()='Neue Überweisung']/ancestor::div[contains(@style, 'position: fixed')]"));

		WebElement closeButton = dialog.findElement(By.xpath(".//div[text()='Abbrechen']"));
		closeButton.click();

		boolean dialogClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[contains(@style, 'position: fixed')]//div[text()='Neue Überweisung']")));

		assertTrue(dialogClosed, "Dialog sollte geschlossen sein");

	}

	@Test
	void testInvalidIBAN() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

		WebElement ueberweisungButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']"));
		ueberweisungButton.click();

		WebElement dialog = driver.findElement(By.xpath(
				"//div[contains(@style, 'position: fixed')]//div[text()='Neue Überweisung']/ancestor::div[contains(@style, 'position: fixed')]"));

		List<WebElement> inputFields = dialog.findElements(By.cssSelector("input[type='text']"));
		inputFields.get(2).sendKeys("Thomas Meier");
		inputFields.get(3).sendKeys("AT2323");
		inputFields.get(4).sendKeys("50");

		WebElement okButton = dialog.findElement(By.xpath(".//div[text()='Ok']"));
		okButton.click();

		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[contains(@style, 'position: fixed')]//div[contains(@style, 'color: rgb(255, 0, 0)')]//div[text()='Ungültige Empfänger-IBAN!']")));
		assertTrue(errorMessage.isDisplayed());

		assertTrue(dialog.isDisplayed(), "Dialog sollte noch geöffnet sein");
	}

	@Test
	void testInvalidAmount() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

		WebElement ueberweisungButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']"));
		ueberweisungButton.click();

		WebElement dialog = driver.findElement(By.xpath(
				"//div[contains(@style, 'position: fixed')]//div[text()='Neue Überweisung']/ancestor::div[contains(@style, 'position: fixed')]"));

		List<WebElement> inputFields = dialog.findElements(By.cssSelector("input[type='text']"));
		inputFields.get(2).sendKeys("Thomas Meier");
		inputFields.get(3).sendKeys("AT021200000703447144");

		WebElement okButton = dialog.findElement(By.xpath(".//div[text()='Ok']"));
		okButton.click();

		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOf(dialog.findElement(
				By.xpath(".//div[contains(@style, 'color: rgb(255, 0, 0)')]//div[text()='Ungültiger Betrag!']"))));
		assertTrue(errorMessage.isDisplayed());
		assertTrue(dialog.isDisplayed(), "Dialog sollte noch geöffnet sein");

	}

	@Test
	void testValidTransfer() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

		WebElement ueberweisungButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']"));
		ueberweisungButton.click();

		WebElement dialog = driver.findElement(By.xpath(
				"//div[contains(@style, 'position: fixed')]//div[text()='Neue Überweisung']/ancestor::div[contains(@style, 'position: fixed')]"));

		List<WebElement> inputFields = dialog.findElements(By.cssSelector("input[type='text']"));
		inputFields.get(2).sendKeys("Thomas Meier");
		inputFields.get(3).sendKeys("AT021200000703447144");
		inputFields.get(4).sendKeys("100");

		List<WebElement> rowsBefore = driver.findElements(By.xpath(
				"//div[contains(@style, 'height: 27px')][.//div[@data-cell-index='0']/following-sibling::div[string-length(text()) > 0]]"));
		int initialRowCount = rowsBefore.size();

		WebElement okButton = dialog.findElement(By.xpath(".//div[text()='Ok']"));
		okButton.click();

		wait.until(ExpectedConditions.invisibilityOf(dialog));

		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(
				"//div[contains(@style, 'height: 27px')][.//div[@data-cell-index='0']/following-sibling::div[string-length(text()) > 0]]"),
				initialRowCount));

		List<WebElement> currentRows = driver.findElements(By.xpath(
				"//div[contains(@style, 'height: 27px')][.//div[@data-cell-index='0']/following-sibling::div[string-length(text()) > 0]]"));

		WebElement newRow = currentRows.get(currentRows.size() - 1);

		String rowText = newRow.getText();

		assertTrue(rowText.contains("Anton Benko"), "Sollte Anton Benko enthalten");
		assertTrue(rowText.contains("AT022011100003429660"), "Sollte AT022011100003429660 enthalten");
		assertTrue(rowText.contains("Thomas Meier"), "Sollte Thomas Meier enthalten");
		assertTrue(rowText.contains("AT021200000703447144"), "Sollte AT021200000703447144 enthalten");

		WebElement tableContainer = newRow.findElement(By.xpath("ancestor::div[contains(@style, 'width: 1250px')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].parentElement.scrollLeft=500", tableContainer);

		wait.until(driver -> {
			String text = newRow.getText();
			return text.contains("100,00");
		});
	}

	@Test
	void testTransferWithInvalidAmount() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

		List<WebElement> rowsBefore = driver.findElements(By.xpath(
				"//div[contains(@style, 'height: 27px')][.//div[@data-cell-index='0']/following-sibling::div[string-length(text()) > 0]]"));
		int initialRowCount = rowsBefore.size();
		System.out.println("Zeilen vor dem Test: " + initialRowCount);

		WebElement ueberweisungButton = driver
				.findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Überweisung']"));
		ueberweisungButton.click();

		WebElement dialog = driver.findElement(By.xpath(
				"//div[contains(@style, 'position: fixed')]//div[text()='Neue Überweisung']/ancestor::div[contains(@style, 'position: fixed')]"));

		List<WebElement> inputFields = dialog.findElements(By.cssSelector("input[type='text']"));
		inputFields.get(2).sendKeys("Thomas Meier");
		inputFields.get(3).sendKeys("AT021200000703447144");
		inputFields.get(4).sendKeys("10000000");

		WebElement okButton = dialog.findElement(By.xpath(".//div[text()='Ok']"));
		okButton.click();

		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Ungültiger Betrag!']")));
		assertTrue(errorMessage.isDisplayed(), "Fehlermeldung sollte angezeigt werden");

		assertTrue(dialog.isDisplayed(), "Dialog sollte noch sichtbar sein");

		List<WebElement> currentRows = driver.findElements(By.xpath(
				"//div[contains(@style, 'height: 27px')][.//div[@data-cell-index='0']/following-sibling::div[string-length(text()) > 0]]"));
		assertEquals(initialRowCount, currentRows.size(), "Es sollte keine neue Zeile hinzugekommen sein");

		System.out.println("Zeilen nach dem Test: " + currentRows.size());
	}
}
