package com.webapp.base;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Properties;

@Listeners({com.webapp.base.TestListener.class})


public class BaseTest {
    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();
    protected Logger log;
    protected EyesManager eyesManager;

    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;

    public WebDriver getDriver() {
        return drivers.get();
    }

    @Parameters({"browser", "os", "node"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional("chrome") String browser, @Optional String os, @Optional String node, ITestContext context) {

        String testName = context.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        //context.setAttribute ("WebDriver", driver);
        context.setAttribute("browser", browser);
        this.testSuiteName = context.getSuite().getName();
        this.testName = testName;
        this.testMethodName = method.getName();

        if (node != null) {
            try {
                RemoteWebDriver driver = BrowserDriverFactory.createRemoteDriver(browser, log, os, node);
                driver.manage().window().maximize();
                drivers.set(driver);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            WebDriver driver = BrowserDriverFactory.createDriver(browser, log);
            driver.manage().window().maximize();
            drivers.set(driver);
        }

        Properties props = System.getProperties();
        try {
            props.load(new FileInputStream(new File("src/main/resources/test.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        eyesManager = new EyesManager(getDriver(), "heroApp");

    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Close driver");
        getDriver().quit();
        drivers.remove();
        eyesManager.abort();
        eyesManager.generateResults ();
    }
}
