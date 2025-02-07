package Tests;

import Pages.AdminRegisterPage;
import Pages.Superadminlogin;
import io.github.bonigarcia.wdm.WebDriverManager;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Adminregistertest {
    WebDriver driver;
    AdminRegisterPage registerPage;
    Superadminlogin superadminlogin;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.acchajobs.com/adminregister");
        driver.manage().window().maximize();
        registerPage = new AdminRegisterPage(driver);
        superadminlogin = new Superadminlogin(driver); // Initialize the Superadminlogin page object
    }

    @Test(priority = 1)
    public void testValidRegistration() {
        // Perform registration
        registerPage.enterName("sravani");
        registerPage.enterMobile("9603661643");
        registerPage.enterUsername("seenium@123");
        registerPage.enterPassword("Test@1234");
        registerPage.enterEmail("sravani08.guduru@gmail.com");
        registerPage.clickRegister();

        // Handle the alert after registration
        handleAlert("Admin registered successfully. Please wait until Super Admin approves your application.");
    }

   // @Test(priority = 2)
    public void superAdminLoginTest() {
        try {
            driver.get("https://www.acchajobs.com/superadminlogin");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username']")));

            superadminlogin.enterUsername("admin");
            superadminlogin.enterPassword("admin@123");
            superadminlogin.clickLogin();

            // Handle the unexpected alert if any
            handleAlert(null);

            WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[text()=' Post Management ']")
            ));
            Assert.assertTrue(dashboardElement.isDisplayed(), "Super Admin login was not successful.");

            System.out.println("Super Admin login test executed successfully.");
        } catch (Exception e) {
            System.out.println("Login test failed: " + e.getMessage());
            Assert.fail("Super Admin login test failed.");
        }
    }

   // @Test(priority = 3)
    public void testApproveUser() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for the row containing user ID 'selenium'
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[normalize-space(text())='selenium']")));

            // Wait for the "Approve" button to be clickable
            WebElement approveButton = driver.findElement(By.xpath("(//button[text()='Approve'])[last()]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", approveButton);
            wait.until(ExpectedConditions.elementToBeClickable(approveButton)).click();

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

    // Helper method to handle alerts
    private void handleAlert(String expectedAlertText) {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());

            String alertText = alert.getText();
            if (expectedAlertText != null) {
                System.out.println("Alert message: " + alertText);
                Assert.assertEquals(alertText, expectedAlertText, "Alert text does not match!");
            }

            alert.accept(); // Accept the alert
        } catch (Exception e) {
            // No alert found, continue execution
            System.out.println("No alert present or an error occurred: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
