package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.DependentsPage;
import Pages.LoginPage;

public class DependentsTest extends BaseTest {

    @Test(priority = 1)
    public void testAddDependent() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));

        DependentsPage dependentsPage = new DependentsPage(driver);
        dependentsPage.navigateToDependents();

        int beforeCount = dependentsPage.getDependentCount();

        dependentsPage.addDependent("John Junior", "2015-06-15");

        int afterCount = dependentsPage.getDependentCount();
        Assert.assertEquals(afterCount, beforeCount + 1, "Dependent count should increase by 1.");
    }

    @Test(priority = 2)
    public void testAddMultipleDependents() {
        DependentsPage dependentsPage = new DependentsPage(driver);
        int initialCount = dependentsPage.getDependentCount();

        dependentsPage.addDependent("Jane Doe", "2012-04-20");
        dependentsPage.addDependent("Tim Doe", "2017-11-01");

        int finalCount = dependentsPage.getDependentCount();
        Assert.assertEquals(finalCount, initialCount + 2, "Should be able to add multiple dependents.");
    }

    @Test(priority = 3)
    public void testDeleteDependent() {
        DependentsPage dependentsPage = new DependentsPage(driver);
        int countBefore = dependentsPage.getDependentCount();

        if (countBefore == 0) {
            dependentsPage.addDependent("Test Dependent", "2010-01-01");
        }

        dependentsPage.deleteFirstDependent();

        int countAfter = dependentsPage.getDependentCount();
        Assert.assertTrue(countAfter < countBefore || countAfter == 0, "Dependent should be deleted.");
    }
}