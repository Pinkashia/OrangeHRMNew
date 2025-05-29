package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.EmergencyContactsPage;
import Pages.LoginPage;

public class EmergencyContactsTest extends BaseTest {

    @Test(priority = 1)
    public void testAddEmergencyContact() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));

        EmergencyContactsPage ecPage = new EmergencyContactsPage(driver);
        ecPage.navigateToEmergencyContacts();

        int countBefore = ecPage.getContactCount();

        ecPage.addEmergencyContact("Jane Doe", "Wife", "0123456789", "9876543210", "0112345678");

        int countAfter = ecPage.getContactCount();
        Assert.assertEquals(countAfter, countBefore + 1, "Emergency contact was not added.");
    }

    @Test(priority = 2)
    public void testAddMultipleEmergencyContacts() {
        EmergencyContactsPage ecPage = new EmergencyContactsPage(driver);
        int initialCount = ecPage.getContactCount();

        ecPage.addEmergencyContact("John Smith", "Brother", "0203456789", "9988776655", "0101010101");
        ecPage.addEmergencyContact("Emily Clark", "Friend", "0303456789", "9123456789", "0223344556");

        int finalCount = ecPage.getContactCount();
        Assert.assertEquals(finalCount, initialCount + 2, "Failed to add multiple emergency contacts.");
    }

    @Test(priority = 3)
    public void testDeleteEmergencyContact() {
        EmergencyContactsPage ecPage = new EmergencyContactsPage(driver);
        int countBefore = ecPage.getContactCount();

        if (countBefore == 0) {
            ecPage.addEmergencyContact("Temp Contact", "Test", "0000000000", "0000000000", "0000000000");
        }

        ecPage.deleteFirstContact();

        int countAfter = ecPage.getContactCount();
        Assert.assertTrue(countAfter < countBefore || countAfter == 0, "Emergency contact was not deleted.");
    }
}
