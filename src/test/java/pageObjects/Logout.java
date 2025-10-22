package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Logout extends BasePage {

	public Logout(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//*[@title='My Account']")
	WebElement myAccount;

	@FindBy(xpath = "//div[@class='list-group']/a")
	List<WebElement> optionsRightside;

	@FindBy(xpath = "(//*[text()='Logout'])[1]")
	WebElement logout;

	@FindBy(xpath = "//*[@class='dropdown-menu dropdown-menu-right']/li")
	List<WebElement> alloptions;

	@FindBy(xpath = "//h1[text()='Account Logout']")
	WebElement successfulLogout;

	public void clickMyAccount() {
		myAccount.click();
	}

	public void clickLogout() {
		logout.click();
	}

	public List<String> ValidateOptions() {
		List<String> optionsList = new ArrayList<>();

		try {
			for (int i = 0; i < optionsRightside.size(); i++) {
				String value = optionsRightside.get(i).getText();
				optionsList.add(value);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return optionsList;
	}

	public String logoutValidations() {

		try {
			return successfulLogout.getText();
		} catch (Exception e) {
			return e.getMessage();
		}

	}
}