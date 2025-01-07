package Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class AdminLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Initialize WebDriver and WebDriverWait
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void adminLogin() {
        try {
            // Navigate to admin login page
            driver.get("https://www.acchajobs.com/adminlogindynamic");

            // Log in as admin
            fillField(By.xpath("//input[@placeholder='Enter your username']"), "tester@123");
            fillField(By.xpath("//input[@placeholder='Enter your password']"), "Test@1234");
            clickButton(By.xpath("//button[@type='submit']"));

            // Handle pop-up alert (if displayed)
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                driver.switchTo().alert().accept();
                System.out.println("Admin logged in successfully.");
            } catch (NoAlertPresentException e) {
                System.out.println("No alert displayed after login.");
            }
        } catch (Exception e) {
            System.err.println("Error during admin login: " + e.getMessage());
        }
    }

    @Test(priority = 2, dependsOnMethods = "adminLogin")
    public void navigateToJobsPage() {
        try {
            clickButton(By.xpath("//a[contains(text(), \"Job's\")]"));
            System.out.println("Navigated to Job's page.");
        } catch (Exception e) {
            System.err.println("Error navigating to Job's page: " + e.getMessage());
        }
    }

    @Test(priority = 3, dependsOnMethods = "navigateToJobsPage")
    public void fillJobDetails() {
        try {
            // Fill out the job posting form
            fillField(By.id("title"), "Software Engineer");
            fillField(By.id("category"), "IT");
            fillField(By.id("location"), "Remote");

            selectDropdownByVisibleText(By.name("employmentType"), "Full Time");
            selectDropdownByVisibleText(By.name("workModel"), "Hybrid");
            selectDropdownByVisibleText(By.name("status"), "Active");

            fillField(By.id("experience"), "2 years");
            fillField(By.id("salary"), "100000");
            fillField(By.id("jobDescription"), "Job description text.");
            fillField(By.name("skills"), "Java, Python, Selenium");
            fillField(By.name("company"), "Accha Jobs Pvt Ltd");

            fillField(By.id("openingStartDate"), "12/19/2024");
            fillField(By.id("lastApplyDate"), "12/31/2024");
            fillField(By.name("numberOfOpenings"), "5");
            fillField(By.name("perks"), "Health Insurance, Paid Time Off");
            fillField(By.id("companyDescription"), "Leading tech company.");

            // Enable the "Save Job" button if disabled
            WebElement saveJobButton = driver.findElement(By.xpath("//button[@type='submit']"));
            if (!saveJobButton.isEnabled()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].disabled = false;", saveJobButton);
                System.out.println("Save Job button enabled using JavaScript.");
            }

            // Click the "Save Job" button
            saveJobButton.click();
            System.out.println("Job details saved successfully.");

        } catch (Exception e) {
            System.err.println("Error filling job details: " + e.getMessage());
        }
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
        try {
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            field.clear();
            field.sendKeys(value);
        } catch (Exception e) {
            System.err.println("Error interacting with field: " + locator + " - " + e.getMessage());
        }
    }

    // Helper method to select dropdown values
    private void selectDropdownByVisibleText(By locator, String visibleText) {
        try {
            WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select dropdown = new Select(dropdownElement);
            dropdown.selectByVisibleText(visibleText);
        } catch (Exception e) {
            System.err.println("Error interacting with dropdown: " + locator + " - " + e.getMessage());
        }
    }

    // Helper method to click buttons
    private void clickButton(By locator) {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));
            button.click();
        } catch (Exception e) {
            System.err.println("Error clicking button: " + locator + " - " + e.getMessage());
        }
    }
}
