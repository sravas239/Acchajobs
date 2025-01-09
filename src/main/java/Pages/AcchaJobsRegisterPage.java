package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AcchaJobsRegisterPage {
    WebDriver driver;

    // Constructor to initialize elements using PageFactory
    public AcchaJobsRegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators for form fields
    @FindBy(xpath = "//input[@id='fullName']")
    private WebElement fullNameField;

    @FindBy(xpath = "//input[@id='userName']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='emailId']")
    private WebElement emailField;

    @FindBy(xpath = "//select[@id='gender']")
    private WebElement genderDropdown;

    @FindBy(xpath = "//input[@id='mobileNo']")
    private WebElement mobileNumberField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;
    
    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;
    
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    // Actions for interacting with form elements
    public void enterFullName(String fullName) {
        fullNameField.sendKeys(fullName);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void selectGender(String gender) {
        genderDropdown.sendKeys(gender); // Use Select class for dropdown if necessary
    }

    public void enterMobileNumber(String mobileNumber) {
        mobileNumberField.sendKeys(mobileNumber);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    public void enterconfirmPassword(String confirmPassword) {
    	confirmPasswordField.sendKeys(confirmPassword);
    }
    public void clickSubmit() {
        submitButton.click();
    }

	

	public void clickRegisterNow() {
		// TODO Auto-generated method stub
		
	}

	public String getSuccessMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
