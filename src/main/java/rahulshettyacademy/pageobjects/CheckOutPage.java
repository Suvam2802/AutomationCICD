package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	
WebDriver driver;
	
	public CheckOutPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;	
		PageFactory.initElements(driver, this);   // This is used for @Findby
	}
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
	WebElement selectCountry;
	
	By Results = By.cssSelector(".ta-results");
	
	
	public void selectCountry(String countryName) 
	{
		Actions act = new Actions(driver);
        act.sendKeys(Country, countryName).build().perform();
        
        waitForElementToAppear(Results);
        
        selectCountry.click();
        
        
        
		
		
	}
	
	public ConfirmationPage submitorder() 
	{
		
		submit.click();
		return new ConfirmationPage(driver);
		
	}
	

}
