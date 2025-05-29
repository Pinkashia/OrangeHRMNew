package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.QualificationsPage;

public class QualificationsTest extends BaseTest {

    @Test(priority = 1)
    public void testAddWorkExperience() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));

        QualificationsPage qualPage = new QualificationsPage(driver);
        qualPage.navigateToQualifications();

        int countBefore = qualPage.getWorkExperienceCount();
        qualPage.addWorkExperience("ACME Corp", "QA Engineer", "2020-01-01", "2023-12-31");

        int countAfter = qualPage.getWorkExperienceCount();
        Assert.assertEquals(countAfter, countBefore + 1, "Work experience not added.");
    }

    @Test(priority = 2)
    public void testDeleteWorkExperience() {
        QualificationsPage qualPage = new QualificationsPage(driver);
        int before = qualPage.getWorkExperienceCount();

        if (before == 0) {
            qualPage.addWorkExperience("Test Co", "Temp Role", "2021-01-01", "2022-01-01");
            before++;
        }

        qualPage.deleteFirstWorkExperience();

        int after = qualPage.getWorkExperienceCount();
        Assert.assertTrue(after < before, "Work experience was not deleted.");
    }

    @Test(priority = 3)
    public void testAddEducation() {
        QualificationsPage qualPage = new QualificationsPage(driver);
        int countBefore = qualPage.getEducationCount();

        qualPage.addEducation("Bachelor�s Degree", "MIT", "Computer Science", "2018");

        int countAfter = qualPage.getEducationCount();
        Assert.assertEquals(countAfter, countBefore + 1, "Education entry not added.");
    }

    @Test(priority = 4)
    public void testDeleteEducationEntry() {
        QualificationsPage qualPage = new QualificationsPage(driver);
        int before = qualPage.getEducationCount();

        if (before == 0) {
            qualPage.addEducation("Bachelor�s Degree", "Temp University", "Testing", "2017");
            before++;
        }

        qualPage.deleteFirstEducationEntry();

        int after = qualPage.getEducationCount();
        Assert.assertTrue(after < before, "Education entry was not deleted.");
    }
}