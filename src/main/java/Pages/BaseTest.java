package Pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeSuite
    public void setupDriver() {
        // Simplify WebDriverManager setup
        WebDriverManager.chromedriver().setup();
        
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Set base URL (consider externalizing this)
        String baseUrl = System.getProperty("baseUrl", "https://www.acchajobs.com");
        driver.get(baseUrl);
        
        // Optional: Set implicit wait timeout
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        
    }

    @AfterSuite
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
