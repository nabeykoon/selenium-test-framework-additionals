package com.webapp.loginpagetests;

import com.applitools.eyes.MatchLevel;
import com.webapp.base.TestUtilities;
import com.webapp.pages.LoginPage;
import com.webapp.pages.SecureAreaPage;
import com.webapp.pages.WelcomePage;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveLogInTests extends TestUtilities {
    @Test
    public void logInTest() {

        // open main page
        WelcomePage welcomePage = new WelcomePage(getDriver (), log);
        log.info ("Thread id of logInTest " + Thread.currentThread().getId());
        log.info ("Driver Hash code of logInTest " + getDriver ().hashCode ());
        welcomePage.openPage();
        takeScreenshotInTest("Welcome page opened", getDriver ());
        log.info("Welcome page is opened.");

        // Click on Form Authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
        takeScreenshotInTest("Login page opened", getDriver ());

        Cookie ck = new Cookie("username", "tomsmith", "the-internet.herokuapp.com", "/", null);
        loginPage.setCookie(ck);

        // Log in
        SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "SuperSecretPassword!");
        takeScreenshotInTest("Secure page opened", getDriver ());
        validateWindow (MatchLevel.LAYOUT, false);

        // Get cookies
        String username = secureAreaPage.getCookie("username");
        log.info("Username cookie: " + username);
        String session = secureAreaPage.getCookie("rack.session");
        log.info("Session cookie: " + session);

        // verifications
        // Correct url is appeared
        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

        // log out button is visible
        Assert.assertTrue(secureAreaPage.isLogOutButtonVisible(),
                "logOutButton is not visible.");

        // Successful log in message
        String expectedSuccessMessage = "You logged into a secure area!";
        String actualSuccessMessage = secureAreaPage.getSuccessMessageText();
        Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
                "actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
                        + expectedSuccessMessage + "\nactualSuccessMessage: " + actualSuccessMessage);
    }
}
