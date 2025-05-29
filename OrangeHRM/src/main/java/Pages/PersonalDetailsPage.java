package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalDetailsPage extends BasePage {

    public PersonalDetailsPage(WebDriver driver) {
        super(driver);
    }

    // Menu navigation
    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoTab;

    // Personal details fields
    @FindBy(name = "firstName")
    private WebElement firstNameInput;

    @FindBy(name = "middleName")
    private WebElement middleNameInput;

    @FindBy(name = "lastName")
    private WebElement lastNameInput;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement employeeIdInput;

    @FindBy(xpath = "//label[text()='Driver's License Number']/following::input[1]")
    private WebElement licenseNumberInput;

    @FindBy(xpath = "//label[text()='License Expiry Date']/following::input[1]")
    private WebElement licenseExpiryDateInput;

    @FindBy(xpath = "//label[text()='SSN Number']/following::input[1]")
    private WebElement ssnNumberInput;

    @FindBy(xpath = "//label[text()='SIN Number']/following::input[1]")
    private WebElement sinNumberInput;

    // Click My Info tab
    public void goToPersonalDetails() {
        myInfoTab.click();
    }

    // Check if fields are readonly
    public boolean isFieldReadOnly(WebElement element) {
        String readonly = element.getAttribute("readonly");
        return readonly != null && (readonly.equals("true") || readonly.equals("readonly"));
    }

    public boolean isFirstNameEditable() {
        return !isFieldReadOnly(firstNameInput);
    }

    public boolean isMiddleNameEditable() {
        return !isFieldReadOnly(middleNameInput);
    }

    public boolean isLastNameEditable() {
        return !isFieldReadOnly(lastNameInput);
    }

    public boolean isEmployeeIdReadOnly() {
        return isFieldReadOnly(employeeIdInput);
    }

    public boolean isLicenseNumberReadOnly() {
        return isFieldReadOnly(licenseNumberInput);
    }

    public boolean isLicenseExpiryReadOnly() {
        return isFieldReadOnly(licenseExpiryDateInput);
    }

    public boolean isSSNReadOnly() {
        return isFieldReadOnly(ssnNumberInput);
    }

    public boolean isSINReadOnly() {
        return isFieldReadOnly(sinNumberInput);
    }
}
