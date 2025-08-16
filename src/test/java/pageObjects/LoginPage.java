package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmailAddress;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement Email_pwd_Error;

	@FindBy(xpath = "(//*[text()='Forgotten Password'])[1]")
	WebElement forgotPasswordLink;

	@FindBy(xpath = "((//div[@class='col-sm-6'])[1]//p)[2]")
	WebElement RegisterAccount;

	@FindBy(xpath = "//div[@class='list-group']/a")
	List<WebElement> optionsRightside;

	public void setEmail(String email) {
		txtEmailAddress.sendKeys(email);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin() {
		btnLogin.click();
	}

	public String Email_pwd_ErrorValidations() {

		try {
			return Email_pwd_Error.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public boolean ForgetPassword() {

		try {
			return forgotPasswordLink.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean Validate_RegisterAccount() {

		try {
			return RegisterAccount.isDisplayed();
		} catch (Exception e) {
			return false;
		}
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
}