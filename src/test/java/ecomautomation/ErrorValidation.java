package ecomautomation;

import org.testng.Assert;
import org.testng.annotations.Test;

import ecomautomation.basetest.BaseTest;
import ecomautomation.pageobjects.PlaceOrderPage;
import ecomautomation.pageobjects.ProductCataloguePage;
import ecomautomation.pageobjects.ValidateOrderPage;


public class ErrorValidation extends BaseTest{
	
		@Test(groups= {"loginerrorValidations"})
		public void loginErrorValidation() {
			landingPage.loginValidUsers("nikita123@gmail.com","Ni23");
			Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		}
			
		@Test(groups= {"producterrorValidations"})
		public void productErrorValidation() throws InterruptedException {
			String productToSearch="ZARA COAT 333";

			//add to cart code
			ProductCataloguePage productCatalogue=landingPage.loginValidUsers("nikita123@gmail.com","Nikita123@gmail");
			productCatalogue.AddProductToCart("ZARA COAT 3");

			//validate order
			ValidateOrderPage validateOrder=productCatalogue.goToCartPage();
			Boolean match=validateOrder.validateProductIsPresentInCart(productToSearch);
			Assert.assertFalse(match);
			
		}
			

}
