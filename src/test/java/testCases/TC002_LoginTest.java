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
            //
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
		} finally {
			logger.info("****** Finished TC_002_LoginTest *****");
			driver.quit();
			//driver.close();
			//
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
			// lp.setEmail(p.getProperty("email") + "11");
			lp.setEmail("hana@123");
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();

			String emailActualError = lp.Email_pwd_ErrorValidations();

			Assert.assertEquals(emailActualError, "Warning: No match for E-Mail Address and/or Password.");
			logger.info("Test Passed");
		} catch (Exception e) {
			logger.info("Test Failed : " + e.getMessage());
			Assert.fail("Test Failed : " + e.getMessage());
		} finally {
			logger.info("Browser closed");
			// driver.close();
			driver.quit();
		}
	}

	@Test(groups = { "Sanity", "Master" }, priority = 3)
	public void verify_login_inValid_pwd() {
		logger.info("****** Startign TC_002_LoginTest_Method 3 *****");

		try {
			HomePage hm = new HomePage(driver);
			hm.clickMyAccount();
			hm.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			// lp.setPassword(p.getProperty("password")+"11");
			lp.setPassword("111");
			lp.clickLogin();

			String emailActualError = lp.Email_pwd_ErrorValidations();

			Assert.assertEquals(emailActualError, "Warning: No match for E-Mail Address and/or Password.");
			logger.info("Test Passed");
		} catch (Exception e) {
			logger.info("Test Failed : " + e.getMessage());
			Assert.fail("Test Failed : " + e.getMessage());
		} finally {
			logger.info("Browser closed");
			// driver.close();
			driver.quit();
		}
	}

	@Test(groups = { "Sanity", "Master" }, priority = 4)
	public void verify_login_inValid_pwd_Email() {
		logger.info("****** Startign TC_002_LoginTest_Method 4 *****");

		try {
			HomePage hm = new HomePage(driver);
			hm.clickMyAccount();
			// hm.clickRegister();
			hm.clickLogin();

			LoginPage lp = new LoginPage(driver);
			// lp.setEmail(p.getProperty("email") + "11");
			lp.setEmail("hana@123");
			// lp.setPassword(p.getProperty("password")+"11");
			lp.setPassword("111");
			lp.clickLogin();

			String error = lp.Email_pwd_ErrorValidations();
			Assert.assertEquals(error, "Warning: No match for E-Mail Address and/or Password.");
			logger.info("Test Passed");
		} catch (Exception e) {
			logger.info("Test Failed : " + e.getMessage());
			Assert.fail("Test Failed : " + e.getMessage());
		} finally {
			logger.info("Test completed");
			driver.quit();
		}

	}

	@Test(groups = { "Sanity", "Master" }, priority = 5)
	public void verify_login_withoutEmail_pwd() {
		logger.info("****** Startign TC_002_LoginTest_Method 4 *****");

		try {
			HomePage hm = new HomePage(driver);
			hm.clickMyAccount();
			// hm.clickRegister();
			hm.clickLogin();

			LoginPage lp = new LoginPage(driver);

			lp.clickLogin();

			String error = lp.Email_pwd_ErrorValidations();
			Assert.assertEquals(error, "Warning: No match for E-Mail Address and/or Password.");
			logger.info("Test Passed");
		} catch (Exception e) {
			logger.info("Test Failed : " + e.getMessage());
			Assert.fail("Test Failed : " + e.getMessage());
		} finally {
			logger.info("Test completed");
			driver.quit();
		}

	}

	@Test(groups = { "Sanity", "Master" }, priority = 6)
	public void verify_login_Forget_Password_Evaluation() {
		logger.info("****** Startign TC_002_LoginTest_Method 4 *****");

		try {
			HomePage hm = new HomePage(driver);
			hm.clickMyAccount();
			hm.clickLogin();

			LoginPage lp = new LoginPage(driver);
			boolean results = lp.ForgetPassword();

			Assert.assertEquals(results, true);
		} catch (Exception e) {
			logger.info("Test Failed : " + e.getMessage());
			Assert.fail();
		} finally {
			logger.info("Test Completed");
			driver.quit();
		}

	}
}