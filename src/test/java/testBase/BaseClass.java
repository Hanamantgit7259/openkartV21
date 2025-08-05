package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
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

    @BeforeClass(groups= {"Sanity","Regression","Master"})
    @Parameters({"os","browser"})
    public void setup(String os, String br) throws IOException
    {
        // Load config.properties
        FileReader file = new FileReader("./src//test//resources//config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        String env = p.getProperty("execution_env"); // local or remote
        String appUrl = p.getProperty("appURL2");

        if(env.equalsIgnoreCase("remote"))
        {
            // ✅ Inside Docker (Jenkins) we use selenium-hub, outside Docker use localhost
            String hubHost = System.getenv("HUB_HOST") != null ? System.getenv("HUB_HOST") : "selenium-hub";
            String hubUrl = "http://" + hubHost + ":4444";

            if(br.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
                driver = new RemoteWebDriver(URI.create(hubUrl).toURL(), options);

            } else if(br.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver = new RemoteWebDriver(URI.create(hubUrl).toURL(), options);

            } else if(br.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
                driver = new RemoteWebDriver(URI.create(hubUrl).toURL(), options);

            } else {
                System.out.println("No matching browser found for remote execution.");
                return;
            }
        }
        else if(env.equalsIgnoreCase("local"))
        {
            // 🔹 Local execution (on laptop)
            if(br.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
                driver = new ChromeDriver(options);

            } else if(br.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver = new FirefoxDriver(options);

            } else if(br.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
                driver = new EdgeDriver(options);

            } else {
                System.out.println("Invalid browser name for local execution.");
                return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(appUrl); // URL from config.properties
        driver.manage().window().maximize();
    }

    @AfterClass(groups= {"Sanity","Regression","Master"})
    public void tearDown()
    {
        if(driver != null) {
            driver.quit();
        }
    }

    // ✅ Random generators
    public String randomeString() {
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'
        int length = 5;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                     .limit(length)
                     .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                     .toString();
    }

    public String randomeNumber() {
        Random random = new Random();
        long number = (long)(1000000000L + random.nextDouble() * 9000000000L); // 10 digits
        return String.valueOf(number);
    }

    public String randomeAlphaNumberic() {
        return randomeString().substring(0, 3) + "@" + randomeNumber().substring(0, 3);
    }

    // ✅ Screenshot utility
    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
