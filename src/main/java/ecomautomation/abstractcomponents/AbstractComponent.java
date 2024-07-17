package ecomautomation.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecomautomation.pageobjects.ValidateOrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	@FindBy (css="[routerlink*='cart']")
	WebElement cartPageButton;
	
	

	public void waitForElementToAppear(By FindBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	public void waitForWebElementToAppear(WebElement FindBy) {
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public ValidateOrderPage goToCartPage() {
		cartPageButton.click();
		ValidateOrderPage validateOrder= new ValidateOrderPage(driver);
		return validateOrder;
		
	}

	
	
	
}
