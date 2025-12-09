package compareclub.orangehrms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PimPage extends BasePage {
	

	@FindBy(xpath = "//a[text()='Add Employee']")
	private WebElement addEmployee;
	
	@FindBy(name = "firstName")
	private WebElement firstNameInput;

	@FindBy(name = "lastName")
	private WebElement lastNameInput;
	 
	@FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
	private WebElement employeeIdInput;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveButton;

	@FindBy(xpath = "//h6[text()='Personal Details']")
	private WebElement personalDetails;
	
	@FindBy(xpath = "//input[@type='file']")
	private WebElement uploadProfilePicture;

	@FindBy(xpath = "//h6[text()='Add Employee']")
	private WebElement addEmployeeHeader;
	
	@FindBy(xpath = "//div[contains(@class,'oxd-form-loader')]")
	private WebElement loader;
	
	@FindBy(xpath = "//a[text()='Employee List']")
	private WebElement employeeList;

	@FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div//input")
	private WebElement employeeIdSearch;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchButton;

	@FindBy(xpath = "//div[@class='oxd-table-card']")
	private WebElement searchRow;

	@FindBy(xpath = "//a[contains(@href,'Job')]")
	private WebElement jobTab;

	@FindBy(xpath = "//label[text()='Job Title']/ancestor::div[1]/following-sibling::div//div[contains(@class,'oxd-select-text')]")
	private WebElement jobTitleDropdown;

	@FindBy(xpath = "//label[text()='Employment Status']/ancestor::div[1]/following-sibling::div//div[contains(@class,'oxd-select-text')]")
	private WebElement employmentStatusDropdown;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement editSaveButton;
	
	@FindBy(xpath = "//i[@class='oxd-icon bi-trash']")
	private WebElement deleteIcon;

	@FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
	private WebElement confirmDeleteButton;
	

	public PimPage(WebDriver driver) {
		super(driver);
	}
	
	public void uploadImage(String filePath) {
	    uploadProfilePicture.sendKeys(filePath);
	}
	
	 public void addEmployee(String firstName, String lastName, String employeeId,String filePath) {
	        click(addEmployee);
	        waitUntilElementIsVisible(addEmployeeHeader);
	        uploadImage(filePath);
	        waitUntilElementIsInvisible(loader);
	        type(firstNameInput, firstName);
	        type(lastNameInput, lastName);
	        employeeIdInput.click();
	        employeeIdInput.sendKeys(Keys.CONTROL + "a");
	        employeeIdInput.sendKeys(Keys.BACK_SPACE);
	        type(employeeIdInput, employeeId);
	        waitUntilElementIsInvisible(loader);
	        waitUntilElementIsClickable(saveButton); 
	        click(saveButton);;
	  }
	 
	 public boolean isEmployeeAdded() {
		 waitUntilElementIsVisible(personalDetails);
	     return personalDetails.isDisplayed();	
	}
	 
	 public void searchEmployee(String employeeId) {
		    click(employeeList);
		    waitUntilElementIsClickable(employeeIdSearch);
		    type(employeeIdSearch, employeeId);
		    click(searchButton);
		    waitUntilElementIsInvisible(loader);
		}

	 public boolean isEmployeeDisplayed(String employeeId) {
			    waitUntilElementIsInvisible(loader);

			    String rowLocator = "//div[@class='oxd-table-card']//div[contains(text(),'" + employeeId + "')]";

			    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(rowLocator)));

			    WebElement employeeCell = driver.findElement(By.xpath(rowLocator));

			    scrollToElement(employeeCell);
			    return employeeCell.isDisplayed();
 
	 }
		
	 public void openEmployee() {
		    waitUntilElementIsInvisible(loader);
	        waitUntilElementIsClickable(searchRow);
	        click(searchRow);
	    }
		
	 public void updateEmployeeJobDetails(String jobTitle, String employmentStatus) throws InterruptedException {

		    waitUntilElementIsClickable(jobTab);
		    click(jobTab);

		    waitUntilLoaderDisappears(); 
		    waitUntilElementIsVisible(jobTitleDropdown);
		    scrollToElement(jobTitleDropdown);
		    waitUntilLoaderDisappears();
		    waitUntilElementIsClickable(jobTitleDropdown);
		    click(jobTitleDropdown);
		    selectDropdownValue(jobTitle);
		    waitUntilLoaderDisappears();
		    scrollToElement(employmentStatusDropdown);
		    waitUntilLoaderDisappears();
		    waitUntilElementIsClickable(employmentStatusDropdown);
		    click(employmentStatusDropdown);
		    selectDropdownValue(employmentStatus);
		    waitUntilLoaderDisappears();

		    scrollToElement(editSaveButton);
		    waitUntilElementIsClickable(editSaveButton);
		    click(editSaveButton);

		    waitUntilLoaderDisappears(); 
		}



	 public boolean isJobUpdated(String expectedJobTitle, String expectedEmployeeStatus,String employeeId) {

		    click(employeeList);
		    waitUntilElementIsClickable(employeeIdSearch);
		    type(employeeIdSearch, employeeId);
		    click(searchButton);
		    waitUntilElementIsInvisible(loader);

		    String rowXpath = "//div[@class='oxd-table-card']//div[text()='" + employeeId + "']/ancestor::div[@class='oxd-table-card']";

		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rowXpath)));

		    String rowText = driver.findElement(By.xpath(rowXpath)).getText();
		    System.out.println("Row Text ---> " + rowText);

		    return rowText.contains(expectedJobTitle) &&
		           rowText.contains(expectedEmployeeStatus);
		}

	 public void deleteEmployee(String employeeId) {

		    searchEmployee(employeeId);

		    String rowXpath = "//div[@class='oxd-table-card']//div[contains(text(),'" + employeeId + "')]";
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rowXpath)));

		    scrollToElement(driver.findElement(By.xpath(rowXpath)));

		    String deleteIconXpath = rowXpath + "/ancestor::div[@class='oxd-table-card']//i[contains(@class,'bi-trash')]";

		    WebElement deleteBtn = wait.until(
		            ExpectedConditions.elementToBeClickable(By.xpath(deleteIconXpath)));

		    deleteBtn.click();

		    WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//button[normalize-space()='Yes, Delete']")));

		    confirmDelete.click();

		    waitUntilLoaderDisappears();
		}


	 
	 public boolean isEmployeeDeleted(String employeeId) {

		    searchEmployee(employeeId);
		    String rowLocator = "//div[@class='oxd-table-card']//div[text()='" + employeeId + "']";
		    waitUntilLoaderDisappears();
		    return driver.findElements(By.xpath(rowLocator)).size() == 0;
		}
	 
	 
	

}
