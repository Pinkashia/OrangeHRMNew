package Pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DependentsPage extends BasePage {

    public DependentsPage(WebDriver driver) {
        super(driver);
    }

    // Navigation
    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoTab;

    @FindBy(xpath = "//a[text()='Dependents']")
    private WebElement dependentsTab;

    // Add Button
    @FindBy(xpath = "//button[contains(., 'Add')]")
    private WebElement addButton;

    // Form Inputs
    @FindBy(xpath = "//label[text()='Name']/following::input[1]")
    private WebElement nameInput;

    @FindBy(xpath = "//label[text()='Relationship']/following::div[contains(@class,'oxd-select-text-input')]")
    private WebElement relationshipDropdown;

    @FindBy(xpath = "//div[@role='option']//span[text()='Child']")
    private WebElement relationshipChildOption;

    @FindBy(xpath = "//label[text()='Date of Birth']/following::input[1]")
    private WebElement dobInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Delete elements
    @FindBy(css = "i.bi-trash")
    private List<WebElement> deleteIcons;

    @FindBy(xpath = "//button[text()=' Yes, Delete ']")
    private WebElement confirmDeleteButton;

    // Record rows
    @FindBy(css = ".orangehrm-horizontal-padding")
    private List<WebElement> dependentRows;

    public void navigateToDependents() {
        myInfoTab.click();
        dependentsTab.click();
    }

    public void clickAdd() {
        addButton.click();
    }

    public void addDependent(String name, String dob) {
        clickAdd();
        nameInput.sendKeys(name);

        relationshipDropdown.click();
        relationshipChildOption.click();

        dobInput.sendKeys(Keys.CONTROL + "a");
        dobInput.sendKeys(dob);
        dobInput.sendKeys(Keys.ENTER);

        saveButton.click();
    }

    public int getDependentCount() {
        return dependentRows.size();
    }

    public void deleteFirstDependent() {
        if (!deleteIcons.isEmpty()) {
            deleteIcons.get(0).click();
            confirmDeleteButton.click();
        }
    }
}