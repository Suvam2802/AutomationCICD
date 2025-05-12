package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) 
	{
		//initialization
		super(driver);
		this.driver=driver;	
		PageFactory.initElements(driver, this);   // This is used for @Findby
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproducts ;
	
	
	//**********************************************************************************************************
	
	By ProductsBy = By.cssSelector(".mb-3");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() 
	{
		
		waitForElementToAppear(ProductsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) 
	{
		
		
		 WebElement prod = getProductList().stream()
		            .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
		            .findFirst().orElse(null);
		 return prod;
		
		
	}
	
	public void addProductToCart(String ProductName) 
	{
		
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click();
		
		waitForElementToAppear(toastMessage);
		
		waitForElementToDisappear(spinner);
		
	}
	

}
