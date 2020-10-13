package com.webapp.horizontalslidertests;

import com.applitools.eyes.MatchLevel;
import com.webapp.base.TestUtilities;
import com.webapp.pages.HorizontalSliderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HorizontalSliderTests extends TestUtilities {

	@Test
	public void sliderTest() {
		log.info("Starting sliderTest");

		// Open HorizontalSliderPage
		HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(getDriver (), log);
		horizontalSliderPage.openPage();

		String value = "3.5";
		eyesManager.validateWindow (MatchLevel.STRICT, false);
		// Set slider value
		sleep(2000);
		horizontalSliderPage.setSliderTo(value);
		sleep(2000);

		// Verify slider value
		String sliderValue = horizontalSliderPage.getSliderValue();
		Assert.assertTrue(sliderValue.equals(value), "Range is not correct. It is: " + sliderValue);
	}
}
