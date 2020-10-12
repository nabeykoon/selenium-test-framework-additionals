package com.webapp.uploadtests;

import com.applitools.eyes.MatchLevel;
import com.webapp.base.TestUtilities;
import com.webapp.pages.FileUploaderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadTests extends TestUtilities {

	@Test(dataProvider = "files")
	public void fileUploadTest(int no, String fileName) {
		log.info("Starting fileUploadTest #" + no + " for " + fileName);

		log.info ("Thread id of fileUploadTest " + Thread.currentThread().getId());
		log.info ("Driver Hash code of fileUploadTest " + getDriver ().hashCode ());
		// open File Uploader Page
		FileUploaderPage fileUploaderPage = new FileUploaderPage(getDriver (), log);
		fileUploaderPage.openPage();

		// Select file
		fileUploaderPage.selectFile(fileName);
		validateWindow (MatchLevel.LAYOUT,"afterSelectFile");
		// Push upload button
		fileUploaderPage.pushUploadButton();
		sleep(2000);

		// Get uploaded files
		String fileNames = fileUploaderPage.getUploadedFilesNames();

		// Verify selected file uploaded
		Assert.assertTrue(fileNames.contains(fileName),
				"Our file (" + fileName + ") is not one of the uploaded (" + fileNames + ")");
	}
}
