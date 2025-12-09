package compareclub.orangehrms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage{
	
	@FindBy(xpath = "//h6[text()='Dashboard']")
	private WebElement dashBoardElement;
	
	@FindBy(xpath = "//span[text()='PIM']")
	private WebElement pim;
	

	@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
	private WebElement profileDropdown;

	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement logoutLink;

	@FindBy(xpath = "//h5[text()='Login']")
	private WebElement loginPageHeader;

	public DashboardPage(WebDriver driver) {
		super(driver);
	}
	
	 public String getDashboardHeaderText() {
	        waitUntilElementIsVisible(dashBoardElement);
	        return dashBoardElement.getText();
	   }
	 
	 public void navigateToPimPage() {
		 click(pim);
	}
	 public void logout() {
		    waitUntilElementIsVisible(profileDropdown);
		    click(profileDropdown);

		    wait.until(ExpectedConditions.visibilityOf(logoutLink));
		    click(logoutLink);
		}

		public boolean isLoggedOut() {
		    waitUntilElementIsVisible(loginPageHeader);
		    return loginPageHeader.isDisplayed();
		}




}
