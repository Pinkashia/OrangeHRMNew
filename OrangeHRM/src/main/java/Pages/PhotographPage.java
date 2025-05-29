package Pages;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhotographPage extends BasePage {

	public PhotographPage(WebDriver driver) {
		super(driver);
	}

	// Navigation
	@FindBy(xpath = "//span[text()='My Info']")
	private WebElement myInfoTab;
	
	@FindBy(xpath= "//img[@class=\"employee-image\"]")
	private WebElement profilepicture;

	@FindBy(xpath = "//div[@class='employee-image-wrapper']")
	private WebElement profilePhotoArea;

	// Upload elements
	@FindBy(xpath = "//input[@type='file']")
	private WebElement fileInput;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement uploadButton;

	@FindBy(css = ".oxd-toast.oxd-toast--success")
	private WebElement successToast;

	// Actions
	public void goToPhotographUpload() {
		myInfoTab.click();
		profilepicture.click();
		
	}

	public void uploadPhotograph(String absoluteFilePath) throws AWTException {
		waitForVisibility(profilePhotoArea);
		profilePhotoArea.click();  // Click on photo avatar
		sleep(1000);
		uploadFileWithRobot(absoluteFilePath);
		waitForClickability(uploadButton);
		uploadButton.click();
		
	}
	
	public boolean isphotoPageOpen()
	{
		waitForVisibility(uploadButton);
		return true;
	}

	public boolean isUploadSuccessful() {
		try {
		    waitForVisibility(successToast);
		    return successToast.isDisplayed();
		} catch (Exception e) {
		    return false;
		}
	}
}