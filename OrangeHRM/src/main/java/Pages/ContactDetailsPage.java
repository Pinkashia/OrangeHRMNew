package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactDetailsPage extends BasePage {

    public ContactDetailsPage(WebDriver driver) {
        super(driver);
    }

    // Navigation
    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoTab;

    @FindBy(xpath = "//a[text()='Contact Details']")
    private WebElement contactDetailsTab;

    // Input fields
    @FindBy(name = "street1")
    private WebElement addressStreet1;

    @FindBy(name = "street2")
    private WebElement addressStreet2;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "state")
    private WebElement state;

    @FindBy(name = "zip")
    private WebElement zipCode;

    @FindBy(xpath = "//label[text()='Country']/following::div[@class='oxd-select-text-input'][1]")
    private WebElement countryDropdown;

    @FindBy(xpath = "//div[@role='option']//span[text()='India']")
    private WebElement countryIndiaOption;

    @FindBy(name = "home")
    private WebElement homePhone;

    @FindBy(name = "mobile")
    private WebElement mobilePhone;

    @FindBy(name = "work")
    private WebElement workPhone;

    @FindBy(name = "workEmail")
    private WebElement workEmail;

    @FindBy(name = "otherEmail")
    private WebElement otherEmail;

    // Save button
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Success toast
    @FindBy(css = ".oxd-toast.oxd-toast--success")
    private WebElement successToast;

    public void navigateToContactDetails() {
        myInfoTab.click();
        contactDetailsTab.click();
    }

    public void fillContactDetails(String street1, String street2, String cityName,
                                   String stateName, String zip, String home, String mobile,
                                   String work, String workEmailAddr, String otherEmailAddr) {
        addressStreet1.clear();
        addressStreet1.sendKeys(street1);

        addressStreet2.clear();
        addressStreet2.sendKeys(street2);

        city.clear();
        city.sendKeys(cityName);

        state.clear();
        state.sendKeys(stateName);

        zipCode.clear();
        zipCode.sendKeys(zip);

        countryDropdown.click();
        countryIndiaOption.click();

        homePhone.clear();
        homePhone.sendKeys(home);

        mobilePhone.clear();
        mobilePhone.sendKeys(mobile);

        workPhone.clear();
        workPhone.sendKeys(work);

        workEmail.clear();
        workEmail.sendKeys(workEmailAddr);

        otherEmail.clear();
        otherEmail.sendKeys(otherEmailAddr);
    }

    public void clickSave() {
        saveButton.click();
    }

    public boolean isSuccessToastDisplayed() {
        waitForVisibility(successToast);
        return successToast.isDisplayed();
    }
}