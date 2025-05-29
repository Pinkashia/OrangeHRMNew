package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
    	
        super(driver);// call the base page constructor
    }

    // UI Elements
    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = "p.oxd-userdropdown-name")
    private WebElement userProfileDropdown;

    @FindBy(xpath = "//p[text()='Invalid credentials']")
    private WebElement errorMessage;

    // Actions
    public void enterUsername(String username) {
        waitForVisibility(usernameInput);  //wait.until(ExpectedConditions.visibilityOf(usernameInput)).clear();
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForVisibility(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        waitForClickability(loginButton);
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginSuccessful() {
        try {
            waitForVisibility(userProfileDropdown);
            return userProfileDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            waitForVisibility(errorMessage);
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed() && loginButton.isEnabled();
    }

    public boolean areFieldsPresent() {
        return usernameInput.isDisplayed() && passwordInput.isDisplayed();
    }
}