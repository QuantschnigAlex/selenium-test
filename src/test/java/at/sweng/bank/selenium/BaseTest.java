package at.sweng.bank.selenium;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "http://127.0.0.1:52663/rap";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get(baseUrl);
        WebElement username = driver.findElement(By.cssSelector("input[type='text'][autocomplete='off']"));
        username.sendKeys("a");

        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys("b");
        WebElement loginButton = driver
            .findElement(By.xpath("//div[contains(@style, 'cursor: pointer')]//div[text()='Login']"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
