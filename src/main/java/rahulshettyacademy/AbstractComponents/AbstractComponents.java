package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) 
	{
		// child sending driver object to parent class - and it's catching by constructor
		this.driver=driver;
		PageFactory.initElements(driver, this);   // This is used for @Findby
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement OrderHeader;
	
	
	public void waitForElementToAppear(By findby) 
	{
		
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
		
		
	}
	
	public void waitForWebElementToAppear(WebElement findby) 
	{
		
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	     wait.until(ExpectedConditions.visibilityOf(findby));
		
		
	}
	
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	public void waitForElementToDisappear(WebElement ele) 
	{
		
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.invisibilityOf(ele));
		
		
	}
	
	public CartPage goToCartPage() 
	{
		cartHeader.click();
		CartPage crtpg = new CartPage(driver);
		return crtpg;
	}
	
	public OrderPage goToOrdersPage() 
	{
		OrderHeader.click();
		OrderPage ordpg = new OrderPage(driver);
		return ordpg;
	}
	
	
	

}
