package ecomautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecomautomation.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPass;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
	WebElement errorBtn;
	
	public ProductCataloguePage loginValidUsers(String userEmaildata,String userPassdata) {
		userEmail.sendKeys(userEmaildata);
		userPass.sendKeys(userPassdata);
		loginBtn.click();
		ProductCataloguePage productCatalogue=new ProductCataloguePage(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorBtn);
		return errorBtn.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	
	

}
