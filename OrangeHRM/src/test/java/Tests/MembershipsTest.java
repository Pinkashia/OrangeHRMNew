package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.MembershipsPage;

public class MembershipsTest extends BaseTest {

    @Test(priority = 1)
    public void testAddMembership() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));

        MembershipsPage membershipsPage = new MembershipsPage(driver);
        membershipsPage.navigateToMemberships();

        int countBefore = membershipsPage.getMembershipCount();

        membershipsPage.addMembership("ACME Club", "Company", "100", "United States Dollar",
                "2024-01-01", "2025-01-01");

        int countAfter = membershipsPage.getMembershipCount();
        Assert.assertEquals(countAfter, countBefore + 1, "Membership not added correctly.");
    }

    @Test(priority = 2)
    public void testAddMultipleMemberships() {
        MembershipsPage membershipsPage = new MembershipsPage(driver);
        int initialCount = membershipsPage.getMembershipCount();

        membershipsPage.addMembership("Gold Membership", "Individual", "50", "United States Dollar",
                "2024-02-01", "2025-02-01");

        membershipsPage.addMembership("Silver Membership", "Company", "70", "Euro",
                "2024-03-01", "2025-03-01");

        int finalCount = membershipsPage.getMembershipCount();
        Assert.assertEquals(finalCount, initialCount + 2, "Failed to add multiple memberships.");
    }

    @Test(priority = 3)
    public void testDeleteMembership() {
        MembershipsPage membershipsPage = new MembershipsPage(driver);
        int countBefore = membershipsPage.getMembershipCount();

        if (countBefore == 0) {
            membershipsPage.addMembership("Temp Membership", "Company", "10", "Euro",
                    "2024-04-01", "2025-04-01");
            countBefore++;
        }

        membershipsPage.deleteFirstMembership();

        int countAfter = membershipsPage.getMembershipCount();
        Assert.assertTrue(countAfter < countBefore, "Membership was not deleted.");
    }
}