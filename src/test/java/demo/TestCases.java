package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.time.Duration;
import java.util.logging.Level;
import demo.wrappers.Wrappers;
import dev.failsafe.internal.util.Durations;

public class TestCases {
    ChromeDriver driver;
    String url = "https://www.scrapethissite.com/";
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test (priority = 1,description = "Web Scraping Sandbox", enabled = true)
    public void testCase01() throws Exception{
     System.out.println("Started : TestCase01");
     //navigating to the website 
     Wrappers.navigateToWebsite(driver,url);
     //selecting the page 
     Wrappers.selectPage(driver,"Sandbox");
     //Clicking on the desired heading
     Wrappers.headingSelection(driver,"Hockey");
     //printing the Teamname ,year, win %
     Wrappers.teamData(driver);
     System.out.println("End : TestCase01");
    }

    @Test (priority = 2,description = "click on \"Oscar Winning Films", enabled = true)
    public void testCase02() throws Exception{
        System.out.println("Started : TestCase02");
     //navigating to the website 
     Wrappers.navigateToWebsite(driver,url);
     //selecting the page 
     Wrappers.selectPage(driver,"Sandbox");
     //Clicking on the desired heading
     Wrappers.headingSelection(driver,"Oscar");
     Wrappers.scrapeOscarData(driver);
     Wrappers.verifyFile();
     System.out.println("End : TestCase02");

    }


    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}