package Tests;

import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Pages.Superadminlogin;

public class ApproveUser {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.acchajobs.com/superadminlogin");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void superAdminLoginTest() {
        try {
            Superadminlogin superadminlogin = new Superadminlogin(driver);
            driver.get("https://www.acchajobs.com/superadminlogin");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username']")));

            superadminlogin.enterUsername("admin");
            superadminlogin.enterPassword("admin@123");
            superadminlogin.clickLogin();

            // Handle the unexpected alert
            try {
                WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
                Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
                alert.accept(); // Accept the alert
            } catch (Exception e) {
                // No alert found, continue execution
            }

            WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[text()=' Post Management ']")
            ));
            Thread.sleep(2000);
            Assert.assertTrue(dashboardElement.isDisplayed(), "Super Admin login was not successful.");

            System.out.println("Super Admin login test executed successfully.");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Login test failed: " + e.getMessage());
            Assert.fail("Super Admin login test failed.");
        }
    }

    //@Test(priority = 2)
    public void testApproveUser() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for the row containing user ID 'selenium'
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[normalize-space(text())='selenium']")));

            // Wait for the "Approve" button to be clickable
            WebElement approveButton = driver.findElement(By.xpath("(//button[text()='Approve'])[last()]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", approveButton);
            wait.until(ExpectedConditions.elementToBeClickable(approveButton)).click();

            // Click the approve button
            approveButton.click();

            // Validate approval success
            WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[normalize-space(text())='selenium']/following-sibling::td[contains(text(), 'Approved')]")
            ));
            Assert.assertTrue(statusElement.isDisplayed(), "User approval was not successful.");

            System.out.println("Approve button for user ID 'selenium' clicked successfully.");
        } catch (NoSuchElementException | TimeoutException e) {
            Assert.fail("Failed to find or interact with the approve button: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
            Assert.fail("Unexpected Error during test execution.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
