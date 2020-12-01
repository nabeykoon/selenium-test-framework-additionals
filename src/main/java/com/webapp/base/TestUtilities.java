package com.webapp.base;

import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.webapp.utils.TimeUtils.getSystemTime;
import static com.webapp.utils.TimeUtils.getTodaysDate;

public class TestUtilities extends BaseTest {

    protected String basePath;

    //STATIC SLEEP IF REQUIRED FOR DEBUGGING. NOT RECOMMENDED TO USE IN TESTS
    protected void sleep(long millis) {
        try {
            Thread.sleep (millis);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

    @DataProvider(name = "files")
    protected static Object[][] files() {
        return new Object[][]{
                {1, "Intel-logo.jpg"},
                {2, "download.png"},
                {3, "text.txt"}
        };
    }


    /**
     * Take screenshot from any point of test
     *
     * @return
     */
    protected void takeScreenshotInTest(String fileName, WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs (OutputType.FILE);
        String path = System.getProperty ("user.dir")
                + File.separator + File.separator + "test-output"
                + File.separator + File.separator + "screenshots"
                + File.separator + File.separator + getTodaysDate ()
                + File.separator + File.separator + testSuiteName
                + File.separator + File.separator + testName
                + File.separator + File.separator + testMethodName
                + File.separator + File.separator +
                getSystemTime () + " " + fileName + ".png";
        try {
            FileUtils.copyFile (scrFile, new File (path));
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    /**
     * Get logs from browser console
     */
    protected List<LogEntry> getBrowserLogs() {
        LogEntries log = getDriver ().manage ().logs ().get ("browser");
        List<LogEntry> logList = log.getAll ();
        return logList;
    }
}
