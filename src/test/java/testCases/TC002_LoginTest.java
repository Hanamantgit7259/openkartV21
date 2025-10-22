package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.LoginSuccessfullPage;
import pageObjects.MyAccountPage;
import pageObjects.MyWishList;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	/*
    @Test(groups = { "Sanity", "Master" }, priority = 1)
    public void verify_login_Valid_Credentials() {
        logger.info("****** Starting TC_002_LoginTest *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("emailnew"));
            lp.setPassword(p.getProperty("passwordNew"));
            lp.clickLogin();

            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();

            Assert.assertTrue(targetPage);
        } catch (Exception e) {
            Assert.fail();
        } finally {
            logger.info("****** Finished TC_002_LoginTest *****");
            driver.quit();
        }
    }

    @Test(groups = { "Sanity", "Master" }, priority = 2)
    public void verify_login_inValid_Credentials() {
        logger.info("****** Starting TC_002_LoginTest_Method 2 *****");

        try {
            HomePage hm = new HomePage(driver);
            hm.clickMyAccount();
            hm.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail("hana@123");
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();

            String emailActualError = lp.Email_pwd_ErrorValidations();
            Assert.assertEquals(emailActualError,
                    "Warning: No match for E-Mail Address and/or Password.");
            logger.info("Test Passed");
        } catch (Exception e) {
            logger.info("Test Failed : " + e.getMessage());
            Assert.fail("Test Failed : " + e.getMessage());
        } finally {
            logger.info("Browser closed");
            driver.quit();
        }
    }

    @Test(groups = { "Sanity", "Master" }, priority = 3)
    public void verify_login_inValid_pwd() {
        logger.info("****** Starting TC_002_LoginTest_Method 3 *****");

        try {
            HomePage hm = new HomePage(driver);
            hm.clickMyAccount();
            hm.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email"));
            lp.setPassword("111");
            lp.clickLogin();

            String emailActualError = lp.Email_pwd_ErrorValidations();
            Assert.assertEquals(emailActualError,
                    "Warning: No match for E-Mail Address and/or Password.");
            logger.info("Test Passed");
        } catch (Exception e) {
            logger.info("Test Failed : " + e.getMessage());
            Assert.fail("Test Failed : " + e.getMessage());
        } finally {
            logger.info("Browser closed");
            driver.quit();
        }
    }

    @Test(groups = { "Sanity", "Master" }, priority = 4)
    public void verify_login_inValid_pwd_Email() {
        logger.info("****** Starting TC_002_LoginTest_Method 4 *****");

        try {
            HomePage hm = new HomePage(driver);
            hm.clickMyAccount();
            hm.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail("hana@123");
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
        logger.info("****** Starting TC_002_LoginTest_Method 5 *****");

        try {
            HomePage hm = new HomePage(driver);
            hm.clickMyAccount();
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
        logger.info("****** Starting TC_002_LoginTest_Method 6 *****");

        try {
            HomePage hm = new HomePage(driver);
            hm.clickMyAccount();
            hm.clickLogin();

            LoginPage lp = new LoginPage(driver);
            boolean results = lp.ForgetPassword();

            Assert.assertTrue(results);
        } catch (Exception e) {
            logger.info("Test Failed : " + e.getMessage());
            Assert.fail();
        } finally {
            logger.info("Test Completed");
            driver.quit();
        }
    }

    @Test(groups = { "Sanity", "Master" }, priority = 7)
    public void verify_login_Login_navigate_To_Different_pages() {
        logger.info("****** Starting TC_002_LoginTest_Method 7 *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email1"));
            lp.setPassword(p.getProperty("password1"));
            lp.clickLogin();

            LoginSuccessfullPage lsp = new LoginSuccessfullPage(driver);

            String mord = lsp.myOrderTextvalidation();
            Assert.assertEquals(mord, "My Orders");

            String newsletter = lsp.newsLetterTextvalidation();
            Assert.assertEquals(newsletter, "Newsletter");
        } catch (Exception e) {
            logger.info("Test Failed : " + e.getMessage());
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test(groups = { "Sanity", "Master" }, priority = 8)
    public void verify_login_Login_navigate_To_Different_pages_windows() {
        logger.info("****** Starting TC_002_LoginTest_Method 8 *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email1"));
            lp.setPassword(p.getProperty("password1"));
            lp.clickLogin();

            LoginSuccessfullPage lsp = new LoginSuccessfullPage(driver);
            lsp.ClickModifyWishList();

            MyWishList myw = new MyWishList(driver);
            boolean results = myw.WishTextValidations();
            Assert.assertTrue(results);
        } catch (Exception e) {
            logger.info("Test Failed : " + e.getMessage());
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test(groups = { "Sanity", "Master" }, priority = 9)
    public void verify_login_Regisert_Account_Text_validation() {
        logger.info("****** Starting TC_002_LoginTest_Method 9 *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            boolean results = lp.Validate_RegisterAccount();

            Assert.assertTrue(results);
        } catch (Exception e) {
            logger.info("Test Failed : " + e.getMessage());
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test(groups = { "Sanity", "Master" }, priority = 10)
    public void verify_login_options_Available_on_LoginPage() {
        logger.info("****** Starting TC_002_LoginTest_Method 10 *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);

            List<String> optionsList = lp.ValidateOptions();

            for (String option : optionsList) {
                System.out.println(option);
            }
        } catch (Exception e) {
            logger.info("Test Failed : " + e.getMessage());
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    */
    @Test(groups = { "Sanity", "Master" }, priority = -1)
    public void verify_login_inValidLogin() {
        logger.info("****** Starting TC_002_LoginTest_Method 11 *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail("hanamantg@gmail.com");
            lp.setPassword("12456");
            lp.clickLogin();

            String results = lp.Email_pwd_ErrorValidations();
            Assert.assertEquals(results, "Warning: No match for E-Mail Address and/or Password.");

        } catch (Exception e) {
            logger.info("Test Failed : " + e.getMessage());
            Assert.fail();
        } finally {
            driver.quit();
        }
    }
}
