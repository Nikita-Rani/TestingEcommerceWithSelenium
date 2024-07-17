package ecomautomation;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecomautomation.TestComponents.Retry;
import ecomautomation.basetest.BaseTest;
import ecomautomation.pageobjects.ConfirmationPage;
import ecomautomation.pageobjects.LandingPage;
import ecomautomation.pageobjects.PlaceOrderPage;
import ecomautomation.pageobjects.ProductCataloguePage;
import ecomautomation.pageobjects.ValidateOrderPage;

public class SubmitOrderTest extends BaseTest {

	
		@Test(dataProvider="getDataFromJson",groups="purchase", retryAnalyzer=Retry.class)
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
			//System.out.println(productToSearch);
		//String productToSearch="ZARA COAT 3";

		//add to cart code
		ProductCataloguePage productCatalogue=landingPage.loginValidUsers(input.get("email"),input.get("password"));
		productCatalogue.AddProductToCart(input.get("product"));

		//validate order
		ValidateOrderPage validateOrder=productCatalogue.goToCartPage();
		
			
		//place order
		PlaceOrderPage placeOrder= validateOrder.validateOrderIsAddedToCart(input.get("product"));
			
		//confirm order
		ConfirmationPage confirmOrder= placeOrder.placeOrder("India");
		Boolean result=confirmOrder.CheckConfirmationMessage("THANKYOU FOR THE ORDER.");
		if(result==true) {
			System.out.println("order placed");
		}else {
			System.out.println("order not placed");
		}
		

		
	}
		
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"nikita123@gmail.com","Nikita123@gmail","ZARA COAT 3"},{"niki123@gmail.com","Nikita123@gmail","ADIDAS ORIGINAL"}};
	}
	
	@DataProvider
	public Object[][] getDataFromJson() throws IOException {
//		HashMap<String,String> map= new HashMap();
//		map.put("email", "nikita123@gmail.com");
//		map.put("password", "Nikita123@gmail");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1= new HashMap();
//		map1.put("email", "niki123@gmail.com");
//		map1.put("password", "Nikita123@gmail");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String,String>> data=convertJsonToHashMap(System.getProperty("user.dir")+"//src//test//java//ecomautomation//data//PurchaseOrder.json");
		
		return  new Object[][] {{data.get(0)},{data.get(1)}};
		
		
	}

}
