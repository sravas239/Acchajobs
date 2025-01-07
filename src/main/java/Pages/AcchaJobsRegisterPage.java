package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class AcchaJobsRegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions act;

    // Constructor to initialize PageFactory elements
    public AcchaJobsRegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // Locators using @FindBy annotation for the Register Form
    @FindBy(xpath = "//a[contains(text(),'Register Now')]")
    private WebElement btnRegisterNow;
   

    public void clickRegisterNow() {
        btnRegisterNow.click();
    }

    @FindBy(id = "fullName")
    private WebElement txtFullName;

    @FindBy(id = "username")
    private WebElement txtUsername;

    @FindBy(id = "email")
    private WebElement txtEmail;

    @FindBy(xpath = "//input[@value='Male']")
    private WebElement radioGenderMale;

    @FindBy(id = "mobileNumber")
    private WebElement txtMobileNumber;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id = "confirmPassword")
    private WebElement txtConfirmPassword;

    @FindBy(xpath = "//button[text()='Register']")
    private WebElement btnRegister;

    @FindBy(xpath = "//div[@class='success-message']")
    private WebElement lblSuccessMessage;

    public void enterFullName(String fullName) {
        txtFullName.sendKeys(fullName);
    }

    public void enterUsername(String username) {
        txtUsername.sendKeys(username);
    }

    public void enterEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void selectGender(String gender) {
        if ("Male".equalsIgnoreCase(gender)) {
            radioGenderMale.click();
        }
        // Add other gender options as needed
    }

    public void enterMobileNumber(String mobileNumber) {
        txtMobileNumber.sendKeys(mobileNumber);
    }

    public void enterPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        txtConfirmPassword.sendKeys(confirmPassword);
    }

    public void clickRegister() {
        btnRegister.click();
    }

    public String getSuccessMessage() {
        return lblSuccessMessage.getText();
    }
}