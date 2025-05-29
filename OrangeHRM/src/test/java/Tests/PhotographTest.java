package Tests;

import java.awt.AWTException;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.PhotographPage;

public class PhotographTest extends BaseTest {

    @Test
    public void testUploadValidPhotograph() throws AWTException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed - cannot upload photo.");

        PhotographPage photoPage = new PhotographPage(driver);
        photoPage.goToPhotographUpload();
        Assert.assertTrue(photoPage.isphotoPageOpen(), "Login failed - cannot upload photo.");

        // Provide an actual image path (replace with your test image)
        String photoPath = Paths.get("src/test/resources/sample1.png").toAbsolutePath().toString();
        photoPage.uploadPhotograph(photoPath);
        
        Thread.sleep(5000);

        Assert.assertTrue(photoPage.isUploadSuccessful(), "Photograph upload was not successful.");
    }
}
