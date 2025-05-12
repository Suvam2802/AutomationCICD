package rahulshettyacademy.Tests;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {

	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
    public  void LoginErrorMessage() throws IOException {
        String productName = "ZARA COAT 3";
        String ConfirmationMessage = "THANKYOU FOR THE ORDER.";
        
        landingpage.LoginApplication("TestFail@gmail.com", "Testing@2802");
        Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
         //Incorrect email or password
    }
	
	@Test
    public  void ProductErrorValidation() throws IOException {
        String productName = "ZARA COAT 3";
        String ConfirmationMessage = "THANKYOU FOR THE ORDER.";
        
        ProductCatalogue pc =  landingpage.LoginApplication("biswa.relevel.classes@gmail.com", "Biswa@2022");
        
        //ProductCatalogue pc = new ProductCatalogue(driver);
        List<WebElement> products = pc.getProductList();
 
        // PageFactory will not work with in webelemenet.findelement // Interview question
        pc.addProductToCart(productName);
        CartPage crtpg =  pc.goToCartPage();
        
        //CartPage crtpg = new CartPage(driver);
        Boolean match = crtpg.VerifyProductDisplay(productName);

        Assert.assertTrue(match);
        

        
    }
}
