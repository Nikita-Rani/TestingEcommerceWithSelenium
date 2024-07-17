package ecomautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecomautomation.abstractcomponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {
	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By ListOfProducts=By.cssSelector(".mb-3");
	By desiredProduct=By.cssSelector("b");
	By addProductToCart=By.xpath("//div[@class='card-body']/button[2]");
	By addProductToCart2=By.xpath("//button[2]");
	By toast=By.id("toast-container");
	
	
	public List<WebElement> getListOfProducts(){
		waitForElementToAppear(ListOfProducts);
		return products;	
	}
	
	public WebElement searchProduct(String productName) {
		WebElement prod=getListOfProducts().stream().filter(product->
		product.findElement(desiredProduct).getText().equals(productName)).findFirst().orElse(null);
		System.out.println(prod.getText());
		return prod;
	}
	public void AddProductToCart(String productName) throws InterruptedException {
		System.out.println(productName);
		WebElement prod=searchProduct(productName);
		prod.findElement(addProductToCart2).click();
		waitForElementToAppear(toast);
		waitForElementToDisappear(spinner);
		Thread.sleep(4000);
	}
	
}
