package com.webapp.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject {

    private By usernameLocator = By.id("username");
    private By passwordLocator = By.name("password");
    private By logInButtonLocator = By.tagName("button");
    private By loginErrorMessage = By.id("flash");

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Execute log in
     */
    public SecureAreaPage logIn(String username, String password) {
        genericLogIn(username, password);
        return new SecureAreaPage(driver, log);
    }

    public LoginPage negativeLogIn(String username, String password) {
        genericLogIn(username, password);
        return this;
    }

    public String getLoginErrorMessage() {
        waitForVisibilityOf(loginErrorMessage, 5);
        return find(loginErrorMessage).getText();
    }

    // Private methods
    private void genericLogIn(String username, String password) {
        log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(logInButtonLocator);
    }

}
