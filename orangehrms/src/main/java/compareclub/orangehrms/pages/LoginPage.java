package compareclub.orangehrms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage{
	
	
	@FindBy(xpath = "//input[@name='username']")
	private WebElement usernameField;
	
	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//button[@type='submit' and contains(@class,'login')]")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String username) {
		type(usernameField, username);
	}
	
	public void enterPassword(String password) {
		type(passwordField,password);
		
	}
	
	 public void clickLogin() {
		 click(loginButton);
	  }
	 
	 public void loginApplication(String username, String password) {
		 enterUserName(username);
		 enterPassword(password);
		 clickLogin();
	 }


}
