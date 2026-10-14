package com.selenium.project;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownTests extends BaseTest {

    @Test(description = "Verify the language dropdown contains the expected options")
    public void verifyDropdownOptions() {
        Select dropdown = new Select(driver.findElement(By.id("language")));
        Assert.assertFalse(dropdown.isMultiple(), "Language control should be single-select");
        Assert.assertEquals(dropdown.getOptions().size(), 5, "Expected placeholder plus four languages");
        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Select a language");
    }

    @Test(description = "Select a language using visible text")
    public void selectLanguageByVisibleText() {
        Select dropdown = new Select(driver.findElement(By.id("language")));
        dropdown.selectByVisibleText("Python");
        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Python");
    }

    @Test(description = "Select a language using the HTML value")
    public void selectLanguageByValue() {
        Select dropdown = new Select(driver.findElement(By.id("language")));
        dropdown.selectByValue("java");
        Assert.assertEquals(dropdown.getFirstSelectedOption().getAttribute("value"), "java");
    }

    @Test(description = "Select a language using its index")
    public void selectLanguageByIndex() {
        Select dropdown = new Select(driver.findElement(By.id("language")));
        dropdown.selectByIndex(3);
        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "JavaScript");
    }
}
