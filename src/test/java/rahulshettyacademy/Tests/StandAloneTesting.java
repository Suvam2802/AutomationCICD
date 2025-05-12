package rahulshettyacademy.Tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StandAloneTesting extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups = {"Purchase"})
    public  void SubmitOrder(HashMap<String, String> input) throws IOException {
        
        String ConfirmationMessage = "THANKYOU FOR THE ORDER.";
        
        ProductCatalogue pc =  landingpage.LoginApplication(input.get("email"), input.get("password"));
        
        //ProductCatalogue pc = new ProductCatalogue(driver);
        List<WebElement> products = pc.getProductList();
 
        // PageFactory will not work with in webelemenet.findelement // Interview question
        pc.addProductToCart(input.get("productName"));
        CartPage crtpg =  pc.goToCartPage();
        
        //CartPage crtpg = new CartPage(driver);
        Boolean match = crtpg.VerifyProductDisplay(input.get("productName"));

        Assert.assertTrue(match);
        
        CheckOutPage CheckOutPage = crtpg.goToCheckout();
        
        CheckOutPage.selectCountry("india");
        
        ConfirmationPage ConfirmationPage = CheckOutPage.submitorder();

        String confirmationTxt = ConfirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationTxt.equalsIgnoreCase(ConfirmationMessage));

        
    }
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void orderHistoryTest() 
	{
		 ProductCatalogue pc =  landingpage.LoginApplication("leyvingsuvam7488@gmail.com", "Testing@2802");
		 OrderPage Orderpage = pc.goToOrdersPage();
		 Assert.assertTrue(Orderpage.VerifyOrderDisplay(productName));
	}
	
	
	
	
	//Extent Reports
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		
		/*
		 * HashMap<String,String> map = new HashMap<String,String>();
		 * map.put("email","leyvingsuvam7488@gmail.com"); map.put("password",
		 * "Testing@2802"); map.put("productName", "ZARA COAT 3");
		 * 
		 * 
		 * HashMap<String,String> map1 = new HashMap<String,String>();
		 * map1.put("email","biswa.relevel.classes@gmail.com"); map1.put("password",
		 * "Biswa@2022"); map1.put("productName", "ADIDAS ORIGINAL");
		 */
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
}
