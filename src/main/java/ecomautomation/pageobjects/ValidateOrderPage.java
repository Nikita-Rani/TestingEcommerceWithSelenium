package ecomautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import ecomautomation.abstractcomponents.AbstractComponent;

public class ValidateOrderPage extends AbstractComponent {
	
	WebDriver driver;

	public ValidateOrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (css=".cartWrap h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutBtn;
	
	
	
	
	public List<WebElement> getCartProducts() {
		return cartProducts;
	}
	
	public PlaceOrderPage validateOrderIsAddedToCart(String productName){
		Boolean match=getCartProducts().stream().anyMatch(cartProduct->
		cartProduct.getText().equalsIgnoreCase(productName));
		if(match==true) {
			checkOutBtn.click();
			PlaceOrderPage placeOrder= new PlaceOrderPage(driver);
			return placeOrder;
		}else {
			System.out.println("Order Not Placed");
			return null;
		}
	}
	public boolean validateProductIsPresentInCart(String productName){
		Boolean match=getCartProducts().stream().anyMatch(cartProduct->
		cartProduct.getText().equalsIgnoreCase(productName));
		if(match==true) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
