package com.webapp.loginpagetests;

import com.webapp.base.CsvDataProviders;
import com.webapp.base.TestUtilities;
import com.webapp.pages.LoginPage;
import com.webapp.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Map;

public class NegativeLogInTests extends TestUtilities {

    @Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void negativeLogInTest(Map<String, String> testData) {
        log.info ("Thread id " + testData.get("no") + " - " + Thread.currentThread().getId());
        log.info ("Driver Hash code " + testData.get("no") + " - " + getDriver ().hashCode ());
        //Data
        String no = testData.get("no");
        String username = testData.get("username");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");
        String description = testData.get("description");
        log.info("Starting negativeLogInTest #" + no + " for " + description);
        // open main page
        WelcomePage welcomePage = new WelcomePage(getDriver (), log);
        welcomePage.openPage();
        log.info("Welcome page is opened.");

        // Click on Form Authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();

        // negative login
        loginPage.negativeLogIn(username, password);
        // Verification
        String actualErrorMessage = loginPage.getLoginErrorMessage();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "actualErrorMessage does not contain expectedErrorMessage\nexpectedErrorMessage: "
                        + expectedErrorMessage + "\nactualErrorMessage: " + actualErrorMessage);
    }
}
