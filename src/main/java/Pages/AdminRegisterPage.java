package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminRegisterPage {
    WebDriver driver;

    // Locators (updated based on the screenshot)
    By nameField = By.id("name");
    By mobileField = By.id("mobileNo");
    By usernameField = By.id("username");
    By passwordField = By.id("password");
    By emailField = By.id("email");
    By registerButton = By.xpath("//button[text()='Register']");

    // Constructor
    public AdminRegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with the web elements
    public void enterName(String name) {
        WebElement nameInput = driver.findElement(nameField);
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterMobile(String mobile) {
        WebElement mobileInput = driver.findElement(mobileField);
        mobileInput.clear();
        mobileInput.sendKeys(mobile);
    }

    public void enterUsername(String username) {
        WebElement usernameInput = driver.findElement(usernameField);
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordField);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(emailField);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

	

	public void selectGender(String string) {
		// TODO Auto-generated method stub
		
	}

	public void clickSubmit() {
		// TODO Auto-generated method stub
		
	}
}
