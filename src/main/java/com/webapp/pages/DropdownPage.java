package com.webapp.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePageObject {

	private By dropdown = By.id("dropdown");

	public DropdownPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	private WebElement dropdownElement = find(dropdown);
	private Select selectDropdown = new Select(dropdownElement);

	/** This method selects given option from dropdown */
	public void selectOption(int i) {
		log.info("Selecting option " + i + " from dropdown");
		// There are three ways to use Select class
		// #1
		// dropdown.selectByIndex(i);
		// #2
		selectDropdown.selectByValue("" + i);
		// #3
		// dropdown.selectByVisibleText("Option " + i);
	}

	/** This method returns selected option in dropdown as a string */
	public String getSelectedOption() {
		String selectedOption = selectDropdown.getFirstSelectedOption().getText();
		log.info(selectedOption + " is selected in dropdown");
		return selectedOption;
	}

}
