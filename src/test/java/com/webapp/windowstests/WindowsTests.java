package com.webapp.windowstests;

import com.webapp.base.TestUtilities;
import com.webapp.pages.NewWindowPage;
import com.webapp.pages.WelcomePage;
import com.webapp.pages.WindowsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WindowsTests extends TestUtilities {

	@Test
	public void newWindowTest() {
		log.info("Starting newWindowTest");

		// open main page
		WelcomePage welcomePage = new WelcomePage(getDriver (), log);
		welcomePage.openPage();

		// Click on MultipleWindows link
		WindowsPage windowsPage = welcomePage.clickMultipleWindowsLink();

		// Click 'Click Here' link to open new window
		windowsPage.openNewWindow();
		sleep(1000);
		
		// Find and switch to new window page
		NewWindowPage newWindowPage = windowsPage.switchToNewWindowPage();

		String pageSource = newWindowPage.getCurrentPageSource();

		// Verification that new page contains expected text in source
		Assert.assertTrue(pageSource.contains("New Window"), "New page source doesn't contain expected text");
	}
}
