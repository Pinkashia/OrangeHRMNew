package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.LoginPage;

public class LoginTest extends BaseTest {
	

    @Test(priority = 4)
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should succeed with valid credentials.");
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"), "User should be redirected to dashboard.");
    }

    @Test(priority = 1)
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        //driver.get(config.getProperty("baseUrl"));
        loginPage.login("wronguser", "wrongpass");

        Assert.assertFalse(loginPage.isLoginSuccessful(), "Login should fail with invalid credentials.");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed.");
        Assert.assertEquals(loginPage.getErrorMessageText().toLowerCase().trim(), 
            "invalid credentials", "Expected error message: 'Invalid credentials'");
    }

    @Test(priority = 2)
    public void testLoginFieldsPresence() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.areFieldsPresent(), "Username and Password fields should be visible.");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be visible and enabled.");
    }

    @Test(priority = 3)
    public void testPageTitleAndUrl() {
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("orangehrm"), "Page title should contain 'OrangeHRM'.");
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "URL should contain 'auth/login'.");
    }
}