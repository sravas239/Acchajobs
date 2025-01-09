package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;

import Pages.Superadminlogin;

import java.time.Duration;

public class AdminLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Initialize WebDriver and WebDriverWait
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    
  
    @Test(priority = 1)
    public void adminLogin() {
        // Navigate to admin login page
        driver.get("https://www.acchajobs.com/adminlogindynamic");

        // Log in as admin
        fillField(By.xpath("//input[@placeholder='Enter your username']"), "seenium@123");
        fillField(By.xpath("//input[@placeholder='Enter your password']"), "Test@1234");
        clickButton(By.xpath("//button[@type='submit']"));

        // Handle pop-up alert (if displayed)
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept(); // Click OK on the alert
        System.out.println("Admin logged in successfully.");
    }

    @Test(priority = 2, dependsOnMethods = "adminLogin")
    public void navigateToJobsPage() {
        // Navigate to the job posting page
        clickButton(By.xpath("//a[contains(text(), \"Job's\")]"));
        System.out.println("Navigated to Job's page.");
    }

    @Test(priority = 3, dependsOnMethods = "navigateToJobsPage")
    public void fillJobDetails() throws InterruptedException {
        // Fill out the job posting form
        fillField(By.id("title"), "Software Engineer");
        fillField(By.id("category"), "IT");
        fillField(By.id("location"), "Remote");

        // Select dropdown values
        selectDropdownByVisibleText(By.name("employmentType"), "Full Time");
        selectDropdownByVisibleText(By.name("workModel"), "Hybrid");
        selectDropdownByVisibleText(By.name("status"), "Active");

        fillField(By.id("experience"), "2 years");
        fillField(By.id("salary"), "100000");
        fillField(By.id("jobDescription"), "Job description text.");
        fillField(By.name("skills"), "Java, Python, Selenium");
        fillField(By.name("company"), "Accha Jobs Pvt Ltd");
        Thread.sleep(2000);
        fillField(By.id("openingStartDate"), "12/19/2024");
        fillField(By.id("lastApplyDate"), "12/31/2024");
        fillField(By.name("numberOfOpenings"), "5");
        Thread.sleep(2000);
        fillField(By.name("perks"), "Health Insurance, Paid Time Off");
        Thread.sleep(2000);
        fillField(By.id("companyDescription"), "Leading tech company.");
        Thread.sleep(2000);

        // Use JavaScript to enable the "Save Job" button if it remains disabled
        WebElement saveJobButton = driver.findElement(By.xpath("//button[@type='submit']"));
        if (!saveJobButton.isEnabled()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].disabled = false;", saveJobButton);
            System.out.println("Save Job button enabled using JavaScript.");
        }

        // Click the "Save Job" button
        saveJobButton.click();
        System.out.println("Job details saved successfully.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    // Helper method to fill text fields
    private void fillField(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
        field.sendKeys(value);
    }

    // Helper method to select dropdown values
    private void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    // Helper method to click buttons
    private void clickButton(By locator) {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));
        button.click();
    }
}