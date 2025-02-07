package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Achaajobpage {

    // Constructor to initialize PageFactory elements
    public Achaajobpage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Locators using @FindBy annotation
    @FindBy(xpath = "//input[@placeholder='Enter skills/designations | Enter location | Enter Experience']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class='search-button']")
    private WebElement searchButton;

    @FindBy(xpath = "//a[contains(text(),'Register Now')]")
    private WebElement registerNowButton;

    @FindBy(xpath = "//button[@class='login-button']")
    private WebElement loginButton;
    
	@FindBy(id="username") 
    WebElement txtUsername;
	
	@FindBy(id="password") 
    WebElement txtPassword;
	
	@FindBy(id="location") 
    WebElement txtLocation;
	
	@FindBy(id="minSalary") 
    WebElement txtminSalary;
	
	@FindBy(id="maxSalary") 
    WebElement txtmaxSalary;
	
	@FindBy(id="workMode") 
    WebElement txtworkMode;
	
	@FindBy(id="experience") 
    WebElement txtexperience;
	
	@FindBy(xpath="//button[text()='Apply Filters']") 
    WebElement txtapplyFilter;
	
	@FindBy(xpath="//button[text()='Clear Filters']") 
    WebElement txtclearFilter;
	
	@FindBy(xpath="(//button[text()='Apply Now'])[1]") 
    public WebElement txtapplyJobs;
	
	
	
	public void fillLocation(String location)
    {
		txtLocation.sendKeys(location);
    }
	
	public void fillminSalary(String minSalary)
    {
		txtminSalary.sendKeys(minSalary);
    }
	
	public void fillmaxSalary(String maxSalary)
    {
		txtmaxSalary.sendKeys(maxSalary);
    }
	
	public void fillworkMode()
    {
		Select s=new Select(txtworkMode);
		s.selectByVisibleText("WFH");
    }
	
	public void fillexperience()
    {
		Select s=new Select(txtexperience);
		s.selectByVisibleText("3-5 years");
    }
	
	public void clickOnApplyFilter()
    {
		txtapplyFilter.click();
    }
	
	public void clickOnResetFilter()
    {
		txtclearFilter.click();
    }
	public void clickOnApplyJobs()
    {
		txtapplyJobs.click();
    }

    // Actions/Methods to interact with elements
    public void enterSearchDetails(String details) {
        searchField.clear();
        searchField.sendKeys(details);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void clickRegisterNow() {
        registerNowButton.click();
    }

    public void clickLogin() {
        loginButton.click();
    }
}
