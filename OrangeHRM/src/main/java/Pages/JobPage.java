package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobPage extends BasePage {

    public JobPage(WebDriver driver) {
        super(driver);
    }

    // Navigation
    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoTab;

    @FindBy(xpath = "//a[text()='Job']")
    private WebElement jobTab;

    // Job Info Fields (readonly validation)
    @FindBy(xpath = "//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text')][1]")
    private WebElement jobTitleField;

    @FindBy(xpath = "//label[text()='Employment Status']/following::div[contains(@class,'oxd-select-text')][1]")
    private WebElement employmentStatusField;

    @FindBy(xpath = "//label[text()='Job Category']/following::div[contains(@class,'oxd-select-text')][1]")
    private WebElement jobCategoryField;

    @FindBy(xpath = "//label[text()='Joined Date']/following::input[1]")
    private WebElement joinedDateField;

    @FindBy(xpath = "//label[text()='Sub Unit']/following::div[contains(@class,'oxd-select-text')][1]")
    private WebElement subUnitField;

    @FindBy(xpath = "//label[text()='Location']/following::div[contains(@class,'oxd-select-text')][1]")
    private WebElement locationField;

    @FindBy(xpath = "//label[text()='Employment Contract Start Date']/following::input[1]")
    private WebElement contractStartDateField;

    @FindBy(xpath = "//label[text()='Employment Contract End Date']/following::input[1]")
    private WebElement contractEndDateField;

    @FindBy(xpath = "//button[contains(., 'Save')]")
    private WebElement saveButton;

    public void navigateToJobPage() {
        myInfoTab.click();
        waitForClickability(jobTab);
        jobTab.click();
    }

    public boolean isFieldDisabled(WebElement field) {
        try {
            return !field.isEnabled();
        } catch (Exception e) {
            return true; // If exception, assume inaccessible
        }
    }

    public boolean isSaveButtonVisible() {
        try {
            return saveButton.isDisplayed() && saveButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateJobFieldsReadonly() {
        // These fields should be readonly (i.e. not editable for employees)
        return isFieldDisabled(joinedDateField)
                && isFieldDisabled(contractStartDateField)
                && isFieldDisabled(contractEndDateField);
    }

    public boolean validateDropdownFieldsReadonly() {
        return !jobTitleField.getAttribute("class").contains("editable")
                && !employmentStatusField.getAttribute("class").contains("editable")
                && !jobCategoryField.getAttribute("class").contains("editable")
                && !subUnitField.getAttribute("class").contains("editable")
                && !locationField.getAttribute("class").contains("editable");
    }
}
