package BaseTests;




import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;

import company.qa.openacart.pages.HomePage;
import company.qa.openacart.pages.LoginPage;
import company.qa.opencart.PlaywrightFactory;

public class Base  {
	
	
	PlaywrightFactory pf;
	Page page;
	protected HomePage homepage;
	protected LoginPage loginpage;
	protected Properties prop;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) throws IOException

	{
		pf = new PlaywrightFactory();
		prop = pf.init_prop();
		
		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		
		page = pf.initBrowser(prop);
		homepage =new HomePage(page);


	}


	
	@AfterTest
	public void tearDown()
	{
		page.context().browser().close();
	}
		
	

}
