package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MembershipsPage extends BasePage {

    public MembershipsPage(WebDriver driver) {
        super(driver);
    }

    // Navigation
    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoTab;

    @FindBy(xpath = "//a[text()='Memberships']")
    private WebElement membershipsTab;

    // Add Button
    @FindBy(xpath = "//h6[text()='Assigned Memberships']/following::button[contains(., 'Add')][1]")
    private WebElement addMembershipBtn;

    // Membership Dropdown
    @FindBy(xpath = "//label[text()='Membership']/following::div[contains(@class,'oxd-select-text')][1]")
    private WebElement membershipDropdown;

    // Subscription Paid By
    @FindBy(xpath = "//label[text()='Subscription Paid By']/following::div[contains(@class,'oxd-select-text')][1]")
    private WebElement paidByDropdown;

    // Subscription Amount
    @FindBy(xpath = "//label[text()='Subscription Amount']/following::input[1]")
    private WebElement amountInput;

    @FindBy(xpath = "//label[text()='Currency']/following::div[contains(@class,'oxd-select-text')][1]")
    private WebElement currencyDropdown;

    @FindBy(xpath = "//label[text()='Commence Date']/following::input[1]")
    private WebElement commenceDateInput;

    @FindBy(xpath = "//label[text()='Renewal Date']/following::input[1]")
    private WebElement renewalDateInput;

    // Save Button
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Delete Membership
    @FindBy(css = ".oxd-icon.bi-trash")
    private List<WebElement> deleteIcons;

    @FindBy(xpath = "//button[text()=' Yes, Delete ']")
    private WebElement confirmDeleteBtn;

    // Membership List
    @FindBy(css = ".oxd-table-body .oxd-table-card")
    private List<WebElement> membershipRows;

    // Actions
    public void navigateToMemberships() {
        myInfoTab.click();
        waitForClickability(membershipsTab);
        membershipsTab.click();
    }

    public void clickAddMembership() {
        waitForClickability(addMembershipBtn);
        addMembershipBtn.click();
    }

    public void selectDropdownOption(WebElement dropdown, String optionText) {
        dropdown.click();
        WebElement option = driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + optionText + "']"));
        option.click();
    }

    public void fillMembershipForm(String membership, String paidBy, String amount,
                                   String currency, String commenceDate, String renewalDate) {
        waitForVisibility(membershipDropdown);
        selectDropdownOption(membershipDropdown, membership);
        selectDropdownOption(paidByDropdown, paidBy);
        amountInput.clear();
        amountInput.sendKeys(amount);
        selectDropdownOption(currencyDropdown, currency);
        commenceDateInput.sendKeys(commenceDate);
        renewalDateInput.sendKeys(renewalDate);
    }

    public void clickSave() {
        saveButton.click();
    }

    public void addMembership(String membership, String paidBy, String amount,
                              String currency, String commenceDate, String renewalDate) {
        clickAddMembership();
        fillMembershipForm(membership, paidBy, amount, currency, commenceDate, renewalDate);
        clickSave();
    }

    public int getMembershipCount() {
        return membershipRows.size();
    }

    public void deleteFirstMembership() {
        if (!deleteIcons.isEmpty()) {
            deleteIcons.get(0).click();
            waitForClickability(confirmDeleteBtn);
            confirmDeleteBtn.click();
        }
    }
}
