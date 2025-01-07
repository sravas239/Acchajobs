package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdminLoginPage {
    WebDriver driver;

    // Constructor to initialize PageFactory elements
    public AdminLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators using @FindBy annotation

    // Login elements
    @FindBy(xpath = "//input[@placeholder='Enter your username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@placeholder='Enter your password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    // Job form elements
    @FindBy(xpath = "//a[@class='nav-link router-link-active']")
    private WebElement jobLink;

    @FindBy(id = "title")
    private WebElement titleInput;

    @FindBy(name = "category")
    private WebElement categoryInput;

    @FindBy(xpath = "//input[@id='location']")
    private WebElement locationInput;

    @FindBy(name = "employmentType")
    private WebElement employmentTypeDropdown;

    @FindBy(id = "workModel")
    private WebElement workModelDropdown;

    @FindBy(xpath = "//input[@id='experience']")
    private WebElement experienceInput;

    @FindBy(id = "salary")
    private WebElement salaryInput;

    @FindBy(id = "status")
    private WebElement statusCheckbox;

    @FindBy(id = "jobDescription")
    private WebElement jobDescriptionInput;

    @FindBy(name = "skills")
    private WebElement skillsInput;

    @FindBy(name = "company")
    private WebElement companyInput;

    @FindBy(id = "openingStartDate")
    private WebElement openingStartDateInput;

    @FindBy(id = "lastApplyDate")
    private WebElement lastApplyDateInput;

    @FindBy(name = "numberOfOpenings")
    private WebElement numberOfOpeningsInput;

    @FindBy(name = "perks")
    private WebElement perksInput;

    @FindBy(id = "companyDescription")
    private WebElement companyDescriptionInput;

    @FindBy(xpath = "//button[contains(text(),'Save Job')]")
    private WebElement saveJobButton;

    // Super Admin login link
    @FindBy(xpath = "//a[normalize-space()='Super Admin login']")
    private WebElement superAdminLink;

    // Actions

    // Login actions
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void clickSuperAdminLogin() {
        superAdminLink.click();
    }

    // Navigate to Job section
    public void navigateToJobSection() {
        jobLink.click();
    }

    // Fill job form details
    public void fillJobDetails(String title, String category, String location, String employmentType, 
                               String workModel, String experience, String salary, boolean isActive, 
                               String jobDescription, String skills, String company, String openingStartDate, 
                               String lastApplyDate, String numberOfOpenings, String perks, String companyDescription) {

        titleInput.sendKeys(title);
        categoryInput.sendKeys(category);
        locationInput.sendKeys(location);
        employmentTypeDropdown.sendKeys(employmentType);
        workModelDropdown.sendKeys(workModel);
        experienceInput.sendKeys(experience);
        salaryInput.sendKeys(salary);

        if (isActive) {
            if (!statusCheckbox.isSelected()) {
                statusCheckbox.click();
            }
        } else {
            if (statusCheckbox.isSelected()) {
                statusCheckbox.click();
            }
        }

        jobDescriptionInput.sendKeys(jobDescription);
        skillsInput.sendKeys(skills);
        companyInput.sendKeys(company);
        openingStartDateInput.sendKeys(openingStartDate);
        lastApplyDateInput.sendKeys(lastApplyDate);
        numberOfOpeningsInput.sendKeys(numberOfOpenings);
        perksInput.sendKeys(perks);
        companyDescriptionInput.sendKeys(companyDescription);
    }

    // Save job
    public void saveJob() {
        saveJobButton.click();
    }

	public WebElement getUsernameFieldLocator() {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement getPostNewJobButtonLocator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void job() {
		// TODO Auto-generated method stub
		
	}
}