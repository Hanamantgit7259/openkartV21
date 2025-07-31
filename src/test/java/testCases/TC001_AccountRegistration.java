package testCases;

import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

import org.testng.Assert;

public class TC001_AccountRegistration extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {

		logger.info("***** Starting TC001_AccountRegistration ******");

		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount");

			hp.clickRegister();
			logger.info(" Clicked on Register ");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

			logger.info(" Providing customer details ");
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString() + "@gmail.com");// randomly generated the email
			regpage.setTelephone(randomeNumber());

			String password = randomeAlphaNumberic();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();

			logger.info("Validating expected message");
			String confmsg = regpage.getConfirmationMsg();

			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test failed .. ");
				logger.debug("Debug logs .. ");
				Assert.assertTrue(false);
			}

			// Assert.assertEquals(confmsg, "Your Account Has Been Created! !! ");
		}

		catch (Exception e) {
			// logger.error("Test failed .. ");
			// logger.debug("Debug logs .. ");
			Assert.fail();
		}
		logger.info("TC001_AccountRegistration Finished");
	}
}
