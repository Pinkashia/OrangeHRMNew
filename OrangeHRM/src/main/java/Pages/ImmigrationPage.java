package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImmigrationPage extends BasePage {

    public ImmigrationPage(WebDriver driver) {
        super(driver);
    }

    // Navigation
    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoTab;

    @FindBy(xpath = "//a[text()='Immigration']")
    private WebElement immigrationTab;

    // Add button
    @FindBy(css = "button.oxd-button--secondary")
    private WebElement addButton;

    // Document type, number, issued date, etc.
    @FindBy(xpath = "//label[text()='Number']/following::input[1]")
    private WebElement documentNumberInput;

    @FindBy(xpath = "//label[text()='Issued Date']/following::input[1]")
    private WebElement issuedDateInput;

    @FindBy(xpath = "//label[text()='Expiry Date']/following::input[1]")
    private WebElement expiryDateInput;

    @FindBy(xpath = "//label[text()='Eligible Status']/following::input[1]")
    private WebElement eligibleStatusInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Document upload
    @FindBy(xpath = "//div[@class='oxd-file-button']/input")
    private WebElement uploadFileInput;

    // Delete buttons (list)
    @FindBy(css = "i.bi-trash")
    private List<WebElement> deleteIcons;

    @FindBy(xpath = "//button[text()=' Yes, Delete ']")
    private WebElement confirmDeleteButton;

    // Immigration record entries
    @FindBy(css = ".orangehrm-horizontal-padding")
    private List<WebElement> immigrationRecords;

    // Navigate
    public void navigateToImmigration() {
        myInfoTab.click();
        immigrationTab.click();
    }

    // Add new immigration record
    public void addImmigrationRecord(String number, String issued, String expiry, String eligibleStatus) {
        addButton.click();
        documentNumberInput.sendKeys(number);
        issuedDateInput.sendKeys(issued);
        expiryDateInput.sendKeys(expiry);
        eligibleStatusInput.sendKeys(eligibleStatus);
        saveButton.click();
    }

    // Upload file (must be triggered right after add/edit)
    public void uploadDocument(String filePath) {
        try {
            uploadFileInput.sendKeys(filePath);
        } catch (Exception e) {
            throw new RuntimeException("File upload failed", e);
        }
    }

    // Delete first immigration record
    public void deleteFirstRecord() {
        if (!deleteIcons.isEmpty()) {
            deleteIcons.get(0).click();
            confirmDeleteButton.click();
        }
    }

    // Get number of immigration records
    public int getRecordCount() {
        return immigrationRecords.size();
    }
}

