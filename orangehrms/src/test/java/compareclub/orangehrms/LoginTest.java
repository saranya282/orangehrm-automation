package compareclub.orangehrms;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import compareclub.orangehrms.base.BaseTest;
import compareclub.orangehrms.pages.DashboardPage;
import compareclub.orangehrms.pages.LoginPage;
@Listeners({io.qameta.allure.testng.AllureTestNg.class})


public class LoginTest extends BaseTest{
	
	@Test
	public void verifyLoginTest() {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginApplication("Admin", "admin123");
		DashboardPage dashboardPage = new DashboardPage(driver);		
		Assert.assertEquals(dashboardPage.getDashboardHeaderText(), "Dashboard");
		}
	
	

}
