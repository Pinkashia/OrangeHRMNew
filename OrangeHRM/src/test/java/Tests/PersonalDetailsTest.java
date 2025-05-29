package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.PersonalDetailsPage;

public class PersonalDetailsTest extends BaseTest {

    @Test
    public void verifyPersonalDetailsFieldAccess() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed!");

        // Step 2: Navigate to Personal Details
        PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage(driver);
        personalDetailsPage.goToPersonalDetails();

        // Step 3: Validate read-only fields
        Assert.assertFalse(personalDetailsPage.isFirstNameEditable(), "First name should not be editable.");
        Assert.assertFalse(personalDetailsPage.isMiddleNameEditable(), "Middle name should not be editable.");
        Assert.assertFalse(personalDetailsPage.isLastNameEditable(), "Last name should not be editable.");

        Assert.assertTrue(personalDetailsPage.isEmployeeIdReadOnly(), "Employee ID should be read-only.");
        Assert.assertTrue(personalDetailsPage.isLicenseNumberReadOnly(), "License Number should be read-only.");
        Assert.assertTrue(personalDetailsPage.isLicenseExpiryReadOnly(), "License Expiry Date should be read-only.");
        Assert.assertTrue(personalDetailsPage.isSSNReadOnly(), "SSN Number should be read-only.");
        Assert.assertTrue(personalDetailsPage.isSINReadOnly(), "SIN Number should be read-only.");
    }
    
}