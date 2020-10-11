package com.webapp.hoverstests;

import com.webapp.base.TestUtilities;
import com.webapp.pages.HoversPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HoversTests extends TestUtilities {

	@Test
	public void user2ProfileTest() {
		log.info("Starting user2ProfileTest");

		// Open HoversPage
		HoversPage hoversPage = new HoversPage(getDriver (), log);
		hoversPage.openPage();

		// Open User 2 profile
		hoversPage.openUserProfile(2);

		// Verify correct user profile opened
		Assert.assertTrue(hoversPage.getCurrentUrl().contains("/users/2"),
				"Url of opened page is not expected User 1 page url");
	}
}
