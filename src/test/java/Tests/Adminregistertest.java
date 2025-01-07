package Tests;

import Pages.AdminRegisterPage;
import Pages.Superadminlogin;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Adminregistertest {
    WebDriver driver;
    AdminRegisterPage registerPage;

    @BeforeMethod
    public void setUp() {
    	  WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.acchajobs.com/adminregister");
        driver.manage().window().maximize();
        registerPage = new AdminRegisterPage(driver);
    }

    @Test
    public void testValidRegistration() throws InterruptedException {
        registerPage.enterName("John Doe");
        registerPage.enterMobile("9876543210");
        registerPage.enterUsername("tester@123");
        registerPage.enterPassword("Test@1234");
        registerPage.enterEmail("john.doe@example.com");
        registerPage.clickRegister();

        // Handle the alert
        Thread.sleep(2000); // Optional: Wait for the alert to appear
        String alertText = registerPage.getAlertText();
        System.out.println("Alert message: " + alertText);
        
        // Verify the alert text
        Assert.assertEquals(alertText, "Admin registered successfully. Please wait until Super Admin approves your application.", "Alert text does not match!");
        
        // Accept the alert
        registerPage.acceptAlert();
    }
   
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
