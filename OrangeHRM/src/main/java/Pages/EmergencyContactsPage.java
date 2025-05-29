package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class EmergencyContactsPage extends BasePage {

    public EmergencyContactsPage(WebDriver driver) {
        super(driver);
    }

    // Navigation
    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoTab;

    @FindBy(xpath = "//a[text()='Emergency Contacts']")
    private WebElement emergencyContactsTab;

    // Add Contact Button
    @FindBy(xpath = "//button[contains(., 'Add')]")
    private WebElement addButton;

    // Form Fields
    @FindBy(xpath = "//label[text()='Name']/following::input[1]")
    private WebElement nameInput;

    @FindBy(xpath = "//label[text()='Relationship']/following::input[1]")
    private WebElement relationshipInput;

    @FindBy(xpath = "//label[text()='Home Telephone']/following::input[1]")
    private WebElement homePhoneInput;

    @FindBy(xpath = "//label[text()='Mobile']/following::input[1]")
    private WebElement mobileInput;

    @FindBy(xpath = "//label[text()='Work Telephone']/following::input[1]")
    private WebElement workPhoneInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Table Rows
    @FindBy(css = ".oxd-table-body .oxd-table-card")
    private List<WebElement> contactRows;

    // Delete Icons
    @FindBy(css = ".oxd-icon.bi-trash")
    private List<WebElement> deleteIcons;

    @FindBy(xpath = "//button[text()=' Yes, Delete ']")
    private WebElement confirmDeleteButton;

    // Actions
    public void navigateToEmergencyContacts() {
        myInfoTab.click();
        emergencyContactsTab.click();
    }

    public void clickAdd() {
        waitForClickability(addButton);
        addButton.click();
    }

    public void fillEmergencyContactForm(String name, String relationship, String homePhone,
                                         String mobile, String workPhone) {
        waitForVisibility(nameInput);
        nameInput.clear();
        nameInput.sendKeys(name);

        relationshipInput.clear();
        relationshipInput.sendKeys(relationship);

        homePhoneInput.clear();
        homePhoneInput.sendKeys(homePhone);

        mobileInput.clear();
        mobileInput.sendKeys(mobile);

        workPhoneInput.clear();
        workPhoneInput.sendKeys(workPhone);
    }

    public void clickSave() {
        saveButton.click();
    }

    public void addEmergencyContact(String name, String relationship, String homePhone,
                                    String mobile, String workPhone) {
        clickAdd();
        fillEmergencyContactForm(name, relationship, homePhone, mobile, workPhone);
        clickSave();
    }

    public int getContactCount() {
        return contactRows.size();
    }

    public void deleteFirstContact() {
        if (!deleteIcons.isEmpty()) {
            deleteIcons.get(0).click();
            waitForClickability(confirmDeleteButton);
            confirmDeleteButton.click();
        }
    }
}