package compareclub.orangehrms.pages;

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
    
    public BasePage(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
		PageFactory.initElements(driver, this);
	}
    
    protected void waitUntilElementIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    protected void waitUntilElementIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    protected void waitUntilElementIsInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));

	}

    protected void type(WebElement element, String value) {
        waitUntilElementIsVisible(element);
        element.sendKeys(value);
    }

    protected void click(WebElement element) {
        waitUntilElementIsClickable(element);
        element.click();
    }

    protected String getText(WebElement element) {
        waitUntilElementIsVisible(element);
        return element.getText();
    }
    
    protected void selectDropdownValue(String value) {
        WebElement option = driver.findElement(By.xpath("//div[@role='option']//span[text()='" + value + "']"));
        waitUntilElementIsClickable(option);
        option.click();
    }
    
    public void waitUntilLoaderDisappears() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'oxd-form-loader')]")
            ));
        } catch (Exception e) {
            System.out.println("Loader not visible or already disappeared");
        }
    }


    
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true); window.scrollBy(0, -150);", element);
    }




}
