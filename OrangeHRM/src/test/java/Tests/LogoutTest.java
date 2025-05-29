package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.LogoutPage;


public class LogoutTest extends BaseTest {

    @Test
    public void testLogoutFunctionality() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed, cannot proceed with logout.");

        // Step 2: Logout
        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.logout();

        // Step 3: Validation
        Assert.assertTrue(logoutPage.isAtLoginPage(), "User should be redirected to login page after logout.");
        Assert.assertTrue(logoutPage.getCurrentUrl().contains("/auth/login"),
                "URL should point to login after logout.");
    }
}