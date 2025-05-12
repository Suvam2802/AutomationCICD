package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	
WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;	
		PageFactory.initElements(driver, this);   // This is used for @Findby
	}
	
	@FindBy(css = ".hero-primary")
	WebElement confirmationTxt;
	
	public String getConfirmationMessage() 
	{
		return confirmationTxt.getText();
	}

}
