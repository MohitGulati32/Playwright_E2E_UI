package company.qa.openacart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page; 
	//Object repository in the form of string locators

	private String emailid = "//input[@id='input-email']";
	private String pass ="//input[@id='input-password']";
	private String loginButton ="//input[@value='Login']";
	private String fpassLink ="//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
	private String logoutLink="//a[@class='list-group-item'][normalize-space()='Logout']";


	public LoginPage(Page page)
	{
		this.page = page;
	}


	public boolean forgotpassIsVisible()
	{
		return page.isVisible(fpassLink);

	}


	public boolean doLogin(String appuname, String apppass)
	{
		page.fill(emailid, appuname);
		page.fill(pass, apppass);
		page.click(loginButton); 
		if (page.isVisible(logoutLink))
		{
			System.out.println("user has sucessfully logged in");
			return true;
		}
		return false;
	}



	public String getLoginPageTitle()
	{
		String title = page.title();
		System.out.println("the title is:" + title);
		return title;

	}

}
