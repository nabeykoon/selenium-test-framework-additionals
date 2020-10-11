package com.webapp.jserrortests;

import java.util.List;

import com.webapp.base.TestUtilities;
import com.webapp.pages.JSErrorPage;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class JSErrorTests extends TestUtilities {

	@Test
	public void jsErrorTest() {
		log.info("Starting jsErrorTest");
		SoftAssert softAssert = new SoftAssert();

		// Open JSErrorPage
		JSErrorPage jSErrorPage = new JSErrorPage(getDriver (), log);
		jSErrorPage.openPage();

		// Get logs
		List<LogEntry> logs = getBrowserLogs();

		// Verifying there are no JavaScript errors in console
		for (LogEntry logEntry : logs) {
			if (logEntry.getLevel().toString().equals("SEVERE")) {
				softAssert.fail("Severe error: " + logEntry.getMessage());
			}
		}
		softAssert.assertAll();
	}
}
