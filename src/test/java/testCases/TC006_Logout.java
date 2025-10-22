package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.Logout;
import testBase.BaseClass;

public class TC006_Logout extends BaseClass {
	@Test(groups = { "Sanity", "Master" }, priority = 1)
	public void logoutValidations() {

		logger.info("Test Started :------");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();

			Logout lo = new Logout(driver);
			lo.clickMyAccount();
			lo.clickLogout();

			String res = lo.logoutValidations();
			Assert.assertEquals(res, "Account Logout");
		} catch (Exception e) {
			Assert.fail();
		} finally {
			logger.info("****** Finished TC_002_LoginTest *****");
			driver.quit();
		}

	}
}
