package pageObjects;

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
}