package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException, URISyntaxException {
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            URI hubUri = new URI("http://selenium-hub:4444/wd/hub");

            switch (br.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    driver = new RemoteWebDriver(hubUri.toURL(), chromeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--width=1920");
                    firefoxOptions.addArguments("--height=1080");
                    driver = new RemoteWebDriver(hubUri.toURL(), firefoxOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--window-size=1920,1080");
                    driver = new RemoteWebDriver(hubUri.toURL(), edgeOptions);
                    break;

                default:
                    throw new IllegalArgumentException("❌ No matching browser for remote execution: " + br);
            }
        }

        else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new RemoteWebDriver(new ChromeOptions());
                    break;
                case "firefox":
                    driver = new RemoteWebDriver(new FirefoxOptions());
                    break;
                case "edge":
                    driver = new RemoteWebDriver(new EdgeOptions());
                    break;
                default:
                    throw new IllegalArgumentException("❌ Invalid local browser name: " + br);
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appURL2"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String randomeString() {
        return new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build()
                .generate(5);
    }

    public String randomeNumber() {
        return new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .build()
                .generate(10);
    }

    public String randomeAlphaNumberic() {
        RandomStringGenerator letters = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build();
        RandomStringGenerator numbers = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .build();
        return letters.generate(3) + "@" + numbers.generate(3);
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        // Ensure screenshots folder exists
        String screenshotDirPath = System.getProperty("user.dir") + File.separator + "screenshots";
        File screenshotDir = new File(screenshotDirPath);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        String targetFilePath = screenshotDirPath + File.separator + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        // Copy screenshot using safe method
        FileUtils.copyFile(sourceFile, targetFile);

        return targetFilePath;
    }
}