package testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass {

	@Test(groups = { "Regression", "Master" }, priority = 1)
	public void verify_account_registration() {
		logger.info("***** Starting TC001_AccountRegistrationTest  ****");
		logger.debug("This is a debug log message");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link.. ");

			hp.clickRegister();
			logger.info("Clicked on Register Link.. ");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

			logger.info("Providing customer details...");
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString() + "@gmail.com");// randomly generated the email
			regpage.setTelephone(randomeNumber());

			String password = randomeAlphaNumberic();

			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();

			logger.info("Validating expected message..");

			String confmsg = regpage.getConfirmationMsg();
			Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");

			logger.info("Test passed");
		} catch (Exception e) {
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		} finally {
			logger.info("***** Finished TC001_AccountRegistrationTest *****");
		}

	}

	@Test(groups = { "Regression", "Master" }, priority = 2)
	public void verify_account_registration_EmailConfirmation() {
		logger.info("****TC001_verify_account_registration_EmailConfirmation****");
		logger.debug("This is a debug log message");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link.. ");

			hp.clickRegister();
			logger.info("Clicked on Register Link.. ");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

			logger.info("Providing customer details...");
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			// regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the
			// email
			regpage.setEmail(randomeString() + "@gmail.com");
			regpage.setTelephone(randomeNumber());

			String password = randomeAlphaNumberic();

			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();

			logger.info("Validating expected message..");

			String confmsg = regpage.getConfirmationMsg();
			Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");

			logger.info("Test passed");
		} catch (Exception e) {
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		} finally {
			logger.info("***** Finished Test 2 *****");
		}

	}

	@Test(groups = { "Regression", "Master" }, priority = 3)
	public void verify_account_registration_Newsletter_No_Validation() {
		logger.info("****verify_account_registration_Newsletter_No_Validation****");
		logger.debug("This is a debug log message");
		try {
			HomePage hm = new HomePage(driver);
			hm.clickMyAccount();
			hm.clickRegister();
			logger.info("Account Registartion page is opened");

			logger.info("Filling the register account details");
			AccountRegistrationPage ar = new AccountRegistrationPage(driver);
			ar.setFirstName(randomeString().toUpperCase());
			ar.setLastName(randomeString().toUpperCase());
			ar.setEmail(randomeString() + "@gmail.com");
			ar.setTelephone(randomeNumber());

			logger.info("Your Password details");
			String password = randomeAlphaNumberic();
			ar.setPassword(password);
			ar.setConfirmPassword(password);

			logger.info("Newsletter default option selection validation");
			boolean isSelected = ar.isNewsletterNoSelected();
			Assert.assertTrue(isSelected, "'No' radio button should be selected by default"); // ER1
			ar.setPrivacyPolicy();
			ar.clickContinue();

			logger.info("Validating expected message..");

			String confmsg = ar.getConfirmationMsg();
			Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");

			logger.info("Test passed");
		} catch (Exception e) {
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		} finally {
			logger.info("***** Finished 3rd test cases*****");
		}

	}

	@Test(groups = { "Regression", "Master" }, priority = 3)
	public void verify_account_registration_Newsletter_yes_Validation() {
		logger.info("****verify_account_registration_Newsletter_No_Validation****");
		logger.debug("This is a debug log message");

		logger.info("Currently on home page clicking on My account and register");
		try {
		HomePage hm = new HomePage(driver);
		hm.clickMyAccount();
		hm.clickRegister();

		logger.info("Clicked Register and Navigated to Account Registration page");
		logger.info("Filling persnal information details");
		AccountRegistrationPage accreg = new AccountRegistrationPage(driver);
		accreg.setFirstName(randomeString().toUpperCase());
		accreg.setLastName(randomeString().toUpperCase());
		accreg.setEmail(randomeString() + "@gmail.com");
		accreg.setTelephone(randomeNumber());
		logger.info("Personal information is done");

		logger.info("Filling Your Password details");
		String password = randomeAlphaNumberic();
		accreg.setPassword(password);
		accreg.setConfirmPassword(password);
		
		logger.info("Selecting Yes radio button in Newsletter");
		accreg.selectNewsLetterYes();
		
		accreg.setPrivacyPolicy();
		accreg.clickContinue();
		
		logger.info("Validating expected message..");

		String confmsg = accreg.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");
		
		}
		catch(Exception e) {
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		finally {
			logger.info("***** Finished 4th test case test cases*****");
		}
	}

}