package ecomautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecomautomation.abstractcomponents.AbstractComponent;

public class PlaceOrderPage extends AbstractComponent {
	WebDriver driver;
	public PlaceOrderPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryInput;
	
	@FindBy(css=".ta-results button span ")
	List<WebElement> countryDrpDwn;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderBtn;
	
	
	public List<WebElement> getCountryDropDownList() {
		return countryDrpDwn;
	}
	
	public void setCountry(String countryName) {
		countryInput.sendKeys(countryName);
		WebElement country=getCountryDropDownList().stream().filter(result->
		result.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		country.click();
	}
	
	public ConfirmationPage placeOrder(String countryName) {
		setCountry(countryName);
		placeOrderBtn.click();
		ConfirmationPage confirmPage= new ConfirmationPage(driver);
		return confirmPage;
		
	}
	
}
