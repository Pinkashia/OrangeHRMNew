package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BasePage {

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    // Locators (not using @FindBy since dropdown is dynamic)
    private By userDropdown = By.cssSelector("p.oxd-userdropdown-name");
    private By logoutOption = By.xpath("//a[text()='Logout']");

    private By loginHeader = By.xpath("//h5[text()='Login']");
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");

    public void clickUserDropdown() {
        waitForVisibility(driver.findElement(userDropdown));
        driver.findElement(userDropdown).click();
    }

    public void clickLogout() {
        waitForVisibility(driver.findElement(logoutOption));
        driver.findElement(logoutOption).click();
    }

    public void logout() {
        clickUserDropdown();
        clickLogout();
    }

    public boolean isAtLoginPage() {
        try {
            waitForVisibility(driver.findElement(loginHeader));
            return driver.findElement(usernameField).isDisplayed()
                    && driver.findElement(passwordField).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
