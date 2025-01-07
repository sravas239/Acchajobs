package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ResourcesTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for navigation items
    private static final By HOME_NAV = By.linkText("Home");
    private static final By INTERNSHIPS_NAV = By.linkText("Internships");
    private static final By ONLINE_TRAINING_NAV = By.linkText("Online Training");
    private static final By LOGIN_BUTTON = By.xpath("//button[normalize-space()='Login']");

    @BeforeClass
    public void setUp() {
        // Setup WebDriver and WebDriverWait
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Navigate to the application
        driver.get("https://www.acchajobs.com");
    }

    @Test(priority = 1)
    public void validateNavigationItems() {
        // Validate navigation items are displayed
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(HOME_NAV)).isDisplayed(),
                "Home navigation item is not visible.");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(INTERNSHIPS_NAV)).isDisplayed(),
                "Internships navigation item is not visible.");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(ONLINE_TRAINING_NAV)).isDisplayed(),
                "Online Training navigation item is not visible.");
        System.out.println("All navigation items are visible.");
    }

    @Test(priority = 2)
    public void validateLoginButtonFunctionality() {
        // Click the Login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        loginButton.click();

        // Validate redirection to the login page
        String expectedUrl = "https://www.acchajobs.com/login"; // Replace with actual login page URL if different
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Login button redirection failed.");
        System.out.println("Login button redirection successful.");
    }

    @AfterClass
    public void tearDown() {
        // Quit the driver after tests are done
        if (driver != null) {
            driver.quit();
        }
    }
}
