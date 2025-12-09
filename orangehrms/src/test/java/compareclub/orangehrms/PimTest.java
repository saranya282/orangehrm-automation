package compareclub.orangehrms;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.compareclub.orangehrms.utils.JsonDataReader;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import compareclub.orangehrms.api.EmployeeApiValidator;
import compareclub.orangehrms.base.BaseTest;
import compareclub.orangehrms.pages.DashboardPage;
import compareclub.orangehrms.pages.LoginPage;
import compareclub.orangehrms.pages.PimPage;
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class PimTest extends BaseTest {
	 String employeeid = "E" + (int)(Math.random() * 9000 + 1000);
	 LoginPage loginPage;
	 DashboardPage dashboardPage;
	 PimPage pimPage;
	 EmployeeApiValidator apiValidator;
	 protected String sessionCookie;


	 @BeforeClass
	 public void setupObjects() {
	        loginPage = new LoginPage(driver);
	        dashboardPage = new DashboardPage(driver);
	        pimPage = new PimPage(driver);
	        apiValidator = new EmployeeApiValidator();
	 }
	@Test(dataProvider = "employeeData")
	public void verifyAddEmployee(HashMap<String, String> data) {
		
		String imagePath = System.getProperty("user.dir") + "\\src\\test\\resources\\images\\profiles.jpg";
		loginPage.loginApplication("Admin", "admin123");
	    sessionCookie = driver.manage().getCookieNamed("orangehrm").getValue();
		dashboardPage.navigateToPimPage();
		pimPage.addEmployee(data.get("firstName"),
	            data.get("lastName"), employeeid,imagePath);
		Assert.assertTrue(pimPage.isEmployeeAdded(),"Employee have not added");
		
	}
	

    @Test(dataProvider = "employeeData",dependsOnMethods = {"verifyAddEmployee"})
    public void verifyEditEmployee(HashMap<String, String> data) throws InterruptedException {
    	pimPage.searchEmployee(employeeid);
        Assert.assertTrue(pimPage.isEmployeeDisplayed(employeeid));
        pimPage.openEmployee();
        String updatedJobTitle = data.get("jobTitle");
        String updatedEmploymentStatus = data.get("employmentStatus");
        pimPage.updateEmployeeJobDetails(updatedJobTitle, updatedEmploymentStatus);
        Assert.assertTrue(pimPage.isJobUpdated(updatedJobTitle, updatedEmploymentStatus,employeeid),"Job didn't updated");
    }
	
    @Test(dependsOnMethods = "verifyEditEmployee")
    public void verifyEmployeeUsingApi() {

        String apiJobTitle = apiValidator.getJobTitle(employeeid,sessionCookie);
        String apiStatus = apiValidator.getEmploymentStatus(employeeid,sessionCookie);
        Assert.assertEquals(apiJobTitle, "Automaton Tester", "Job Title mismatch");
        Assert.assertEquals(apiStatus, "Full-Time Permanent", "Employment Status mismatch");
    }
    
    @Test(priority = 4)
    public void verifyDeleteEmployee() {

        pimPage.deleteEmployee(employeeid);
        boolean uiDeleted = pimPage.isEmployeeDeleted(employeeid);
        Assert.assertTrue(uiDeleted, "Employee record is not deleted in ui");
        boolean apiDeleted = apiValidator.isEmployeeDeletedInApi(employeeid, sessionCookie);
        Assert.assertTrue(apiDeleted, "Employee record is not deleted in api");
    }
    
    @Test(priority = 5)
    public void verifyLogoutTest() {
        dashboardPage.logout();
        Assert.assertTrue(dashboardPage.isLoggedOut(), "User is not logged out successfully");
    }


    @DataProvider(name = "employeeData")
    public Object[][] employeeData() throws StreamReadException, DatabindException, IOException {
    	String file = System.getProperty("user.dir")
                + "\\src\\test\\resources\\employeeData.json";


        List<HashMap<String,String>> data =
                JsonDataReader.getJsonDataToMap(file);

        Object[][] dp = new Object[data.size()][1];

        for(int i = 0; i < data.size(); i++) {
            dp[i][0] = data.get(i);
        }
        return dp;
    }


    

}
