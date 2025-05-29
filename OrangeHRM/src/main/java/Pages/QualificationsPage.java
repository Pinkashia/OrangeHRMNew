package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QualificationsPage extends BasePage {

    public QualificationsPage(WebDriver driver) {
        super(driver);
    }

    // Navigation
    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoTab;

    @FindBy(xpath = "//a[text()='Qualifications']")
    private WebElement qualificationsTab;

    // Work Experience
    @FindBy(xpath = "//h6[text()='Work Experience']/following::button[contains(., 'Add')][1]")
    private WebElement addWorkExpButton;

    @FindBy(name = "experience[employer]")
    private WebElement companyInput;

    @FindBy(name = "experience[jobtitle]")
    private WebElement jobTitleInput;

    @FindBy(xpath = "//label[text()='From']/following::input[1]")
    private WebElement fromDateInput;

    @FindBy(xpath = "//label[text()='To']/following::input[1]")
    private WebElement toDateInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveWorkExpBtn;

    @FindBy(xpath = "//h6[text()='Work Experience']/following::i[@class='oxd-icon bi-trash']")
    private List<WebElement> deleteWorkExpIcons;

    // Education
    @FindBy(xpath = "//h6[text()='Education']/following::button[contains(., 'Add')][1]")
    private WebElement addEducationBtn;

    @FindBy(xpath = "//label[text()='Level']/following::div[contains(@class,'oxd-select-text-input')]")
    private WebElement educationLevelDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span[text()='Bachelor�s Degree']")
    private WebElement bachelorOption;

    @FindBy(name = "education[institute]")
    private WebElement instituteInput;

    @FindBy(name = "education[major]")
    private WebElement majorInput;

    @FindBy(xpath = "//label[text()='Year']/following::input[1]")
    private WebElement yearInput;

    @FindBy(xpath = "//h6[text()='Education']/following::i[@class='oxd-icon bi-trash']")
    private List<WebElement> deleteEducationIcons;

    // Save button (common for many forms)
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Actions
    public void navigateToQualifications() {
        myInfoTab.click();
        qualificationsTab.click();
    }

    public void addWorkExperience(String company, String jobTitle, String from, String to) {
        addWorkExpButton.click();
        waitForVisibility(companyInput);
        companyInput.sendKeys(company);
        jobTitleInput.sendKeys(jobTitle);
        fromDateInput.sendKeys(from);
        toDateInput.sendKeys(to);
        saveWorkExpBtn.click();
    }

    public int getWorkExperienceCount() {
        return deleteWorkExpIcons.size();
    }

    public void deleteFirstWorkExperience() {
        if (!deleteWorkExpIcons.isEmpty()) {
            deleteWorkExpIcons.get(0).click();
        }
    }

    public void addEducation(String level, String institute, String major, String year) {
        addEducationBtn.click();
        waitForVisibility(educationLevelDropdown);
        educationLevelDropdown.click();
        bachelorOption.click();
        instituteInput.sendKeys(institute);
        majorInput.sendKeys(major);
        yearInput.sendKeys(year);
        saveButton.click();
    }

    public int getEducationCount() {
        return deleteEducationIcons.size();
    }

    public void deleteFirstEducationEntry() {
        if (!deleteEducationIcons.isEmpty()) {
            deleteEducationIcons.get(0).click();
        }
    }

    // Add more methods for Skills, Languages, Licenses, and Attachments similarly
}