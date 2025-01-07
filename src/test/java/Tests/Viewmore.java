package Tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import java.io.File;
import java.time.Duration;

public class Viewmore {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver
    	 WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the webpage
        driver.get("https://www.acchajobs.com/view-all-jobs");
    }

 //@Test
    public void testViewMoreButton() throws Exception {
        try {
            // Locate the "View More" button
            WebElement viewMoreButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='job-cards-grid']//div[1]//button[1]")));

            // Scroll to and click the button
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewMoreButton);
            viewMoreButton.click();
            System.out.println("View More button clicked.");

            // Wait for job details element
            WebElement jobDetails = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("jobDetails")));

            // Validate the presence and content of job details
            Assert.assertTrue(jobDetails.isDisplayed(), "Job details are not displayed.");
            Assert.assertTrue(jobDetails.getText().contains("Job Description"), "Job description is incorrect.");
            System.out.println("Job details verified successfully.");
        } catch (Exception e) {
            // Capture screenshot and log failure
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("failure-screenshot.png"));

            System.err.println("Test failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}