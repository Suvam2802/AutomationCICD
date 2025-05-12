package rahulshettyacademy.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) 
	{
		super(driver);
		//initialization
		this.driver=driver;	
		PageFactory.initElements(driver, this);   // This is used for @Findby
	}
	
	//WebElement usermail =  driver.findElement(By.id("userEmail"));
	
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement usermail;
	
	@FindBy(id="userPassword")
	WebElement passwordele;
	
	@FindBy(id="login")
	WebElement Submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue LoginApplication(String Email, String Password) 
	{
		
		usermail.sendKeys(Email);
		passwordele.sendKeys(Password);
		Submit.click();
		 ProductCatalogue pc = new ProductCatalogue(driver);
		 return pc;
	}
	
	public String getErrorMessage() 
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void go_to() 
	{
		driver.get("https://rahulshettyacademy.com/client/");
        
	}
	

}
