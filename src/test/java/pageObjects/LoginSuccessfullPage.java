package pageObjects;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginSuccessfullPage extends BasePage {

	public LoginSuccessfullPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//h2[text()='My Orders']")
	WebElement myorders;

	@FindBy(xpath = "//h2[text()='Newsletter']")
	WebElement newsletter;
	
	@FindBy(xpath="//a[text()='Modify your wish list']")
	WebElement modifyWishList;

	public String myOrderTextvalidation() {

		try {
			return myorders.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String newsLetterTextvalidation() {

		try {
			return newsletter.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public void ClickModifyWishList() {
		modifyWishList.click();
		String mainwindow =driver.getWindowHandle();
		Set<String> otherwindows=driver.getWindowHandles();
		
		for(String currWindow : otherwindows) {
			
			String title =driver.switchTo().window(currWindow).getTitle();
			 if(title.contentEquals("My Wish List")) {
				 System.out.println("GetCurrentUrl : "+ driver.getCurrentUrl());
			 }
		}
		
	}
}