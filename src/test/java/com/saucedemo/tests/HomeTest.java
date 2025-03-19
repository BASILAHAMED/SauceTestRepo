package com.saucedemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.saucedemo.utils.ExtentManager;

public class HomeTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        extent = ExtentManager.getInstance();
        test = extent.createTest("Home Test", "Verifying Login Functionality");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        test.info("Navigated to SauceDemo site");
    }

    @Test
    public void homeTest() {
        try {
            test.info("Verify the header");
            WebElement swag_labs_header = driver.findElement(By.className("login_logo"));

            // Verify header displayed
            boolean isLoggedIn = swag_labs_header.isDisplayed();
            Assert.assertTrue(isLoggedIn, "Not present!");
            test.pass("Swag Labs present in Home Page");
        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
            test.info("Browser closed");
        }
        extent.flush(); // Generate the report
    }
}
