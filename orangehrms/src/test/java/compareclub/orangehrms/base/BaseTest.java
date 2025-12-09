package compareclub.orangehrms.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.compareclub.orangehrms.utils.ConfigReader;

public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeClass
	public void driverSetup() throws IOException {
		ConfigReader.loadTheProperties();
		String browser = ConfigReader.get("browser");
		String url = ConfigReader.get("url");
		if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("edge")) {
        	driver = new EdgeDriver();
        }
		driver.get(url);
		driver.manage().window().maximize();

	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
