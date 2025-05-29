package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.JobPage;
import Pages.LoginPage;

public class JobTest extends BaseTest {

    @Test
    public void testJobDetailsAreReadonly() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));

        JobPage jobPage = new JobPage(driver);
        jobPage.navigateToJobPage();

        Assert.assertTrue(jobPage.validateJobFieldsReadonly(), "Some date fields are editable - should be readonly.");
        Assert.assertTrue(jobPage.validateDropdownFieldsReadonly(), "Some dropdowns appear editable - should not be.");

        Assert.assertFalse(jobPage.isSaveButtonVisible(), "Save button should not be available for readonly fields.");
    }
}