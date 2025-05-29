package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.ContactDetailsPage;
import Pages.LoginPage;

public class ContactDetailsTest extends BaseTest {

    @Test
    public void testAddOrEditContactDetails() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed.");

        // Navigate to Contact Details
        ContactDetailsPage contactPage = new ContactDetailsPage(driver);
        contactPage.navigateToContactDetails();

        // Fill contact info
        contactPage.fillContactDetails(
                "221B Baker Street",
                "Apt 2",
                "London",
                "Greater London",
                "NW1 6XE",
                "0123456789",
                "9876543210",
                "01123456789",
                "test@orangehrm.com",
                "alternate@orangehrm.com"
        );

        contactPage.clickSave();
        Assert.assertTrue(contactPage.isSuccessToastDisplayed(), "Contact details were not saved successfully.");
    }
}
