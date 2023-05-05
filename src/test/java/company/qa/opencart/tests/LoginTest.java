package company.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTests.Base;
import constants.AppConstants;

public class LoginTest extends Base{



	@Test(priority =1)
	public void NavigatetoLoginPageTest()
	{
		//PAGE CHAINING--the object of the login page is created in the home page 
	    //instead of creating it here
		
		loginpage =homepage.navigatetoLoginPage();  
		String actualLoginPageTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actualLoginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
		

	}

	
	
	@Test (priority =2)
	public void forgotpassTest()
	{
		loginpage =homepage.navigatetoLoginPage();  
		System.out.println(loginpage.forgotpassIsVisible());
		Assert.assertTrue(loginpage.forgotpassIsVisible());
		
		

	}
	
	
	@Test (priority =3)
	public void loginUserTest()
	{
		loginpage =homepage.navigatetoLoginPage();  
	    
		Assert.assertTrue(loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password")))
		;
		
		

	}
	
}
