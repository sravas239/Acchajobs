package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Superadminlogin {
    private WebDriver driver;

    // Constructor to initialize PageFactory elements
    public Superadminlogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators using @FindBy annotation
    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//button[text()=' Post Management ']")
    private WebElement lnkPostManagement;

    @FindBy(xpath = "((//*[@class='row'])/descendant::div)[last()]/button[1]")
    private WebElement lnkApproveJob;

    // Actions
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void clickOnPostManagement() {
        lnkPostManagement.click();
    }

    public void clickOnApprovePostRequest() {
        lnkApproveJob.click();
    }

    // Utility: Return WebElement for username field if needed elsewhere
    public WebElement getUsernameFieldLocator() {
        return usernameField;
    }
}
