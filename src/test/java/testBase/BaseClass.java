package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println("=== Before Suite ===");
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        System.out.println("=== Before Test ===");
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.out.println("=== Before Class ===");
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {
        System.out.println(">>> Before Method - Browser Setup");

        // Load properties file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            // OS Platform
            switch (os.toLowerCase()) {
                case "windows": capabilities.setPlatform(Platform.WIN11); break;
                case "linux": capabilities.setPlatform(Platform.LINUX); break;
                case "mac": capabilities.setPlatform(Platform.MAC); break;
                default: System.out.println("No matching OS"); return;
            }

            // Browser
            switch (br.toLowerCase()) {
                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
                case "firefox": capabilities.setBrowserName("firefox"); break;
                default: System.out.println("No matching browser"); return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } else {
            // Local Execution
            switch (br.toLowerCase()) {
                case "chrome": driver = new ChromeDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                case "firefox": driver = new FirefoxDriver(); break;
                default: System.out.println("Invalid browser name"); return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appURL2"));
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        System.out.println("<<< After Method - Closing Browser");
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("=== After Class ===");
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        System.out.println("=== After Test ===");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("=== After Suite ===");
    }

    // Utility methods
    public String randomeString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomeNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomeAlphaNumberic() {
        return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(3);
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        String targetPath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetPath);
        sourceFile.renameTo(targetFile);
        return targetPath;
    }
}
