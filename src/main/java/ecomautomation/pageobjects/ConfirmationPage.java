package ecomautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecomautomation.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public boolean CheckConfirmationMessage(String expectedData) {
		String messageData=confirmationMessage.getText();
		if(messageData.equalsIgnoreCase(expectedData)) {
			return true;
		}else {
			return false;
		}
		
	}
	

}
