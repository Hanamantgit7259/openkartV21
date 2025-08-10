package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLasttname;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone' ]")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password' ]")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "(//*[@name='newsletter'])[1]")
	WebElement newsletterYes;

	@FindBy(xpath = "(//*[@name='newsletter'])[2]")
	WebElement newsletterNo;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkdPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!' ]")
	WebElement msgConfirmation;

	@FindBy(xpath = "//div[text()='Password confirmation does not match password!']")
	WebElement pwdMismatchError;

	@FindBy(xpath = "//div[text()='Warning: You must agree to the Privacy Policy!']")
	WebElement privacyPolicyWarning;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement emailDuplicateError;

	@FindBy(xpath = "//div[text()='E-Mail Address does not appear to be valid!']")
	WebElement inValidEmail;

	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLasttname.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);

	}

	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);

	}

	public void selectNewsLetterYes() {
		newsletterYes.click();
	}

	// Method to check if "No" is selected by default
	public boolean isNewsletterNoSelected() {
		return newsletterNo.isSelected();
	}

	public void setPrivacyPolicy() {
		chkdPolicy.click();
	}

	public String passwordMismatchValidation() {

		try {
			return (pwdMismatchError.getText());
		} catch (Exception e) {
			return (e.getMessage());

		}
	}

	public String privacyWarning() {

		try {
			return privacyPolicyWarning.getText();
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	public String EmailDuplicateValidation() {

		try {
			return emailDuplicateError.getText();
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	public String inValidaEmailValidation() {

		try {
			return inValidEmail.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public void clickContinue() {
		// sol1
		btnContinue.click();

		// so12
		// btnContinue.submit();

		// so13
		// Actions act=new Actions(driver);
		// act.moveToElement(btnContinue).click().perform();

		// so14
		// JavascriptExecutor js=(JavascriptExecutor)driver;
		// js.executeScript("arguments[0].click();", btnContinue);

		// Sol 5
		// btnContinue.sendKeys(Keys.RETURN);

		// So16
		// WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}

	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());

		}

	}

}