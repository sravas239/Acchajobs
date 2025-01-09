package Tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Pages.AcchaJobsRegisterPage;
import Pages.Achaajobpage;
import Pages.AdminLoginPage;
import Pages.BaseTest;
import Pages.Superadminlogin;

public class AcchajobTest extends BaseTest {

    // Test Case 1: Search for a Job
    @Test(priority = 1)
    public void searchJobTest() {
        try {
            Achaajobpage jobsPage = new Achaajobpage(driver);

            // Enter search details
            jobsPage.enterSearchDetails("Software Developer | Bangalore | 2 years");

            // Handle overlapping element using JavaScript
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement overlappingElement = (WebElement) js.executeScript(
                "return document.elementFromPoint(arguments[0], arguments[1]);", 672, 260
            );
            if (overlappingElement.getAttribute("class").equals("option")) {
                js.executeScript("arguments[0].style.visibility='hidden';", overlappingElement);
            }

            // Click search button using JavaScript
            WebElement searchButton = driver.findElement(By.xpath("//button[@class='search-button']"));
            js.executeScript("arguments[0].click();", searchButton);

            System.out.println("Search for job completed successfully.");
        } catch (Exception e) {
            System.out.println("Test failed due to: " + e.getMessage());
            Assert.fail("Job search test failed.");
        }
    }

    // Test Case 2: Register a New User
   // @Test(priority = 2)
    public void registerNewUserTest() {
        try {
            // Verify the modal's title
            WebElement modalTitle = driver.findElement(By.xpath("//div[contains(@class, 'modal')]//h2"));
            Assert.assertEquals(modalTitle.getText(), "What do you want to do?");

            // Click on "I want a job" button
            WebElement iWantAJobButton = driver.findElement(By.xpath("//button[contains(text(), 'I want a job')]"));
            iWantAJobButton.click();

            // Initialize page object
            AcchaJobsRegisterPage registerPage = new AcchaJobsRegisterPage(driver);

            // Navigate to the registration page
            registerPage.clickRegisterNow();

            // Fill out the registration form
            registerPage.enterFullName("John Doe");
            registerPage.enterUsername("john.doe123");
            registerPage.enterEmail("john.doe@example.com");
            registerPage.selectGender("Male");
            registerPage.enterMobileNumber("9876543210");
            registerPage.enterPassword("Test@1234");
            registerPage.enterconfirmPassword("Test@1234");

            // Submit the form
            registerPage.clickRegisterNow();

            // Validate success message
            String successMessage = registerPage.getSuccessMessage();
            Assert.assertEquals(successMessage, "Registration successful!", "Validation of success message failed.");

            System.out.println("User registration test passed.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("User registration test failed due to: " + e.getMessage());
        }
    }

    // Test Case 3: Admin Login and Post Job
    @Test(priority = 3)
    public void adminLoginTest() {
        try {
            AdminLoginPage adminLoginPage = new AdminLoginPage(driver);

            // Open Admin Login Page
            driver.get("https://www.acchajobs.com/adminlogindynamic");

            // Wait for the login form to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your username']")));

            // Perform Login
            adminLoginPage.enterUsername("seenium@123");
            adminLoginPage.enterPassword("Test@1234");
            adminLoginPage.clickLogin();

            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText());
            alert.accept(); // Accept the alert

            // Wait for URL redirection and validate
            wait.until(ExpectedConditions.urlContains("save-job"));
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("save-job"), "Redirected to unexpected URL: " + currentUrl);

            System.out.println("Admin login executed successfully.");
        } catch (Exception e) {
            System.out.println("Test failed due to: " + e.getMessage());
            Assert.fail("Admin login test failed.");
        }
    }

   //@Test(priority = 4)
    public void superAdminLoginTest() {
        try {
            Superadminlogin superadminlogin = new Superadminlogin(driver);

            // Open Super Admin Login Page
            driver.get("https://www.acchajobs.com/superadminlogin");

            // Wait for Super Admin login form
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username']")));

            // Perform Super Admin login
            superadminlogin.enterUsername("admin");
            superadminlogin.enterPassword("admin@123");
            superadminlogin.clickLogin();

            System.out.println("Superadmin login test executed successfully.");
        } catch (Exception e) {
            System.out.println("Test failed due to: " + e.getMessage());
            Assert.fail("Super Admin login test failed.");
        }
    }
}