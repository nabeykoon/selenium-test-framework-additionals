package com.webapp.base;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EyesManager {

    private Eyes eyes;
    private EyesRunner runner;
    private String appName;
    private WebDriver driver;

    public EyesManager(WebDriver driver, String appName) {
        this.driver = driver;
        this.appName = appName;
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setApiKey(System.getProperty("applitools.api.key"));
    }

    public void setBatchName(String batchName) {
        eyes.setBatch(new BatchInfo(batchName));
    }

    public void abort() {
        eyes.abortIfNotClosed();
    }

    public void generateResults() {
        TestResultsSummary allTestResults = runner.getAllTestResults();
    }

    public void validateWindow(Enum Match, boolean requireFullPage) {
        eyes.open(driver, "herokuapp", Thread.currentThread().getStackTrace()[2].getMethodName(), new RectangleSize(1920, 1080));
        eyes.setMatchLevel(MatchLevel.STRICT);
        //define Full page need to be considered
        eyes.setForceFullPageScreenshot(requireFullPage);
        eyes.checkWindow();
        eyes.close();
    }

    public void validateElement(By locator) {
        eyes.open(driver, "herokuapp", Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkElement(locator);
        eyes.close();
    }

    public void validateFrame(String locator) {
        eyes.open(driver, "herokuapp", Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkFrame(locator);
        eyes.close();
    }
}
