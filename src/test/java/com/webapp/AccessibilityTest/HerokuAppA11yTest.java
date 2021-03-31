package com.webapp.AccessibilityTest;

import com.deque.axe.AXE;
import com.webapp.base.TestUtilities;
import com.webapp.pages.WelcomePage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.net.URL;

//Automated accessibility test written using axe-selenium library

public class HerokuAppA11yTest extends TestUtilities {

    private static final URL scriptUrl =  HerokuAppA11yTest.class.getResource("/axe.min.js");


    @BeforeMethod
    public void getAmazon(){
        super.getDriver().get("https://www.amazon.com/");
    }

    @Test
    public void herokuAppA11yTest(){

        JSONObject resultJson = new AXE.Builder(getDriver(), scriptUrl).analyze();
        JSONArray violations = resultJson.getJSONArray("violations");

        if (violations.length() == 0){
            System.out.println("No accessibility issues");
        }
        else {
            AXE.writeResults("HerokuAppA11yTest", resultJson);
            Assert.assertTrue( false, AXE.report(violations));
        }
    }
    //Test a specific selector
    @Test
    public void TestAccessibilityWithSelector(){

        JSONObject resultJsonTitle = new AXE.Builder(getDriver(), scriptUrl).include("title").analyze();
        JSONArray titleViolations = resultJsonTitle.getJSONArray("violations");

        if (titleViolations.length() == 0){
            System.out.println("No accessibility issues");
        }
        else {
            AXE.writeResults("HerokuAppA11yTest", resultJsonTitle);
            Assert.assertTrue( false, AXE.report(titleViolations));
        }
    }

    //Test a specific element

    @Test
    public void TestAccessibilityWithWebElement() {

        sleep(2000);
        JSONObject resultJsonElement = new AXE.Builder(getDriver(), scriptUrl).analyze(getDriver().findElement(By.linkText("Customer Service")));
        JSONArray elementViolations = resultJsonElement.getJSONArray("violations");

        if (elementViolations.length() == 0){
            System.out.println("No accessibility issues");
        }
        else {
            AXE.writeResults("HerokuAppA11yTest", resultJsonElement);
            Assert.assertTrue( false, AXE.report(elementViolations));
        }
    }
}
