package com.selenium.project;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MultiSelectTests extends BaseTest {

    @Test(description = "Verify the skills list supports multiple selections")
    public void verifyMultiSelectList() {
        Select skills = new Select(driver.findElement(By.id("skills")));
        Assert.assertTrue(skills.isMultiple(), "Skills control should support multiple selections");
        Assert.assertEquals(skills.getOptions().size(), 4);
        Assert.assertTrue(skills.getAllSelectedOptions().isEmpty(),
                "No skill should be selected initially");
    }

    @Test(description = "Select and verify several skills")
    public void selectMultipleSkills() {
        Select skills = new Select(driver.findElement(By.id("skills")));
        skills.selectByVisibleText("Selenium");
        skills.selectByVisibleText("SQL");
        skills.selectByVisibleText("Software Testing");

        List<String> selected = skills.getAllSelectedOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertEquals(selected.size(), 3);
        Assert.assertTrue(selected.contains("Selenium"));
        Assert.assertTrue(selected.contains("SQL"));
        Assert.assertTrue(selected.contains("Software Testing"));
    }

    @Test(description = "Deselect one option while keeping another selected")
    public void deselectOneSkill() {
        Select skills = new Select(driver.findElement(By.id("skills")));
        skills.selectByVisibleText("Java");
        skills.selectByVisibleText("SQL");
        skills.deselectByVisibleText("Java");

        List<String> selected = skills.getAllSelectedOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertEquals(selected.size(), 1);
        Assert.assertEquals(selected.get(0), "SQL");
    }

    @Test(description = "Clear all selections in a multi-select list")
    public void deselectAllSkills() {
        Select skills = new Select(driver.findElement(By.id("skills")));
        skills.selectByVisibleText("Selenium");
        skills.selectByVisibleText("Java");
        skills.deselectAll();

        Assert.assertTrue(skills.getAllSelectedOptions().isEmpty(),
                "All skill selections should be cleared");
    }
}
