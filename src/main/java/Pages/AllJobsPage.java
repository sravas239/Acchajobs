package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AllJobsPage extends BaseTest{

	public AllJobsPage(WebDriver driver)
	{
		super();
	}
	
	//Job's Section Locators and Methods
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
}	
