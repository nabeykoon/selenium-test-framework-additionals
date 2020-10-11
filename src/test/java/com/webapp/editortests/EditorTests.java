package com.webapp.editortests;

import com.webapp.base.TestUtilities;
import com.webapp.pages.EditorPage;
import com.webapp.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditorTests extends TestUtilities {

	@Test
	public void defaultEditorValueTest() {

		// open main page
		WelcomePage welcomePage = new WelcomePage(getDriver (), log);
		welcomePage.openPage();
		
		// Scroll to the bottom
		welcomePage.scrollToBottom();
		// Click on WYSIWYG Editor link
		EditorPage editorPage = welcomePage.clickWYSIWYGEditorLink();

		// Get default editor text
		String editorText = editorPage.getEditorText();

		// Verification that new page contains expected text in source
		Assert.assertTrue(editorText.equals("Your content goes here."),
				"Editor default text is not expected. It is: " + editorText);
	}
}
