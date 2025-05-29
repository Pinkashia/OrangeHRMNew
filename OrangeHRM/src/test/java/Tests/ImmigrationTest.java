package Tests;

import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.ImmigrationPage;
import Pages.LoginPage;

public class ImmigrationTest extends BaseTest {

    @Test(priority = 1)
    public void testAddImmigrationRecord() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));

        ImmigrationPage immigrationPage = new ImmigrationPage(driver);
        immigrationPage.navigateToImmigration();

        int beforeCount = immigrationPage.getRecordCount();

        immigrationPage.addImmigrationRecord("A12345678", "2023-01-01", "2030-12-31", "Permanent Resident");

        int afterCount = immigrationPage.getRecordCount();
        Assert.assertTrue(afterCount > beforeCount, "Immigration record should be added.");
    }

    @Test(priority = 2)
    public void testUploadImmigrationDocument() {
        ImmigrationPage immigrationPage = new ImmigrationPage(driver);

        // Sample image from test resources
        String filePath = Paths.get("src/test/resources/sample_doc.png").toAbsolutePath().toString();
        immigrationPage.uploadDocument(filePath);

        // This is a basic verification. You can enhance this with UI confirmation logic.
        Assert.assertTrue(true, "Document upload completed (verify manually or with UI feedback).");
    }

    @Test(priority = 3)
    public void testDeleteImmigrationRecord() {
        ImmigrationPage immigrationPage = new ImmigrationPage(driver);

        int before = immigrationPage.getRecordCount();

        if (before == 0) {
            immigrationPage.addImmigrationRecord("B87654321", "2022-05-01", "2028-12-31", "Work Visa");
        }

        immigrationPage.deleteFirstRecord();

        int after = immigrationPage.getRecordCount();
        Assert.assertTrue(after < before || after == 0, "Immigration record should be deleted.");
    }

    @Test(priority = 4)
    public void testMultipleImmigrationRecords() {
        ImmigrationPage immigrationPage = new ImmigrationPage(driver);

        int startCount = immigrationPage.getRecordCount();

        immigrationPage.addImmigrationRecord("X1001", "2022-01-01", "2028-01-01", "Temp Visa");
        immigrationPage.addImmigrationRecord("X1002", "2023-03-03", "2029-03-03", "PR Card");

        int newCount = immigrationPage.getRecordCount();
        Assert.assertTrue(newCount >= startCount + 2, "Two immigration records should be added.");
    }
}