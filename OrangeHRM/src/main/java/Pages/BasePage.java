package Pages;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        // Set wait time to 15 seconds
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
      
    
    

    // Utility method to wait for visibility of an element
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Utility method to wait for clickability of an element
    public void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Utility method to wait for presence using By locator
    public WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Scroll to view if needed
    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public void uploadFileWithRobot(String filePath) throws AWTException {
    	
    	//Robot Class for tasks where Selenium cannot interact with the OS-level UI (like file upload dialogs).
        Robot robot = new Robot();
        
        //Sets a small delay (in milliseconds) after each simulated keypress/mouse action.
        robot.setAutoDelay(100);

        // Copy file path to clipboard
        StringSelection stringSelection = new StringSelection(filePath); //StringSelection object which holds your file path string 
       
        
       // This copies the filePath string into the system clipboard
        //just like pressing Ctrl+C manually.
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        //System.out.println("starting upload");
        
        // CTRL+V to paste
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        //System.out.println(" upload successful");
 
        
        
        /*
         * Since Selenium can�t handle the native file dialog, this approach mimics what a user would do:
			Click a button or image to open the file dialog.
			Copy the file path to clipboard.
			Press Ctrl + V to paste it.
			Press Enter to confirm.
         */
    }
    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}