package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyWishList extends BasePage {

	public MyWishList(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//p[text()='Your wish list is empty.']")
	WebElement wishList;

	public boolean WishTextValidations() {

		try {
			return wishList.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
