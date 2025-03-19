package com.saucedemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.saucedemo.utils.ExtentManager;

public class LoginTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        extent = ExtentManager.getInstance();
        test = extent.createTest("Login Test", "Verifying Login Functionality");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        test.info("Navigated to SauceDemo site");
    }

    @Test
    public void loginTest() {
        try {
            test.info("Entering login credentials");
            WebElement username = driver.findElement(By.id("user-name"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            username.sendKeys("standard_user");
            password.sendKeys("secret_sauce");
            loginButton.click();
            test.pass("Login button clicked");

            // Verify login success
            boolean isLoggedIn = driver.getCurrentUrl().contains("inventory.html");
            Assert.assertTrue(isLoggedIn, "Login failed!");
            test.pass("Login successful, redirected to inventory page");
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
