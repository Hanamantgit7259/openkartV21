package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = { "Sanity", "Master" }, priority = 1)
	public void verify_login_Valid_Credentials() {
		logger.info("****** Startign TC_002_LoginTest *****");

		try {
			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();

			// MyAccount
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();

			Assert.assertTrue(targetPage);// Assert.assertEquals(targetPage, true,"Login failed");
		} catch (Exception e) {
			Assert.fail();
		}
		finally {
		logger.info("****** Finished TC_002_LoginTest *****");
		driver.quit();
		}
	}

	@Test(groups = { "Sanity", "Master" }, priority = 2)
	public void verify_login_inValid_Credentials() {
		logger.info("****** Startign TC_002_LoginTest_Method 2 *****");

		try {
			HomePage hm = new HomePage(driver);
			hm.clickMyAccount();
			hm.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email")+"11");
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();

			String emailActualError = lp.EmailErrorValidations();

			Assert.assertEquals(emailActualError, "Warning: No match for E-Mail Address and/or Password.");
			logger.info("Test Passed");
		} catch (Exception e) {
			logger.info("Test Failed : " + e.getMessage());
			Assert.fail("Test Failed : " + e.getMessage());
		} finally {
			logger.info("Browser closed");
			//driver.close();
			driver.quit();
		}
	}
}