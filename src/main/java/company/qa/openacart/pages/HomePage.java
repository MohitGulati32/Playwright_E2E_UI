package company.qa.openacart.pages;

import com.microsoft.playwright.Page;

public class HomePage {

	private Page page; 
	//Object repository in the form of string locators

	private String search = "//input[@name='search']";
	private String searchIcon ="//i[@class='fa fa-search']";
	private String MyaccountLink ="//span[normalize-space()='My Account']";
	private String login ="a:text('login')";
	

// page constructor
	
	public HomePage(Page page)
	{
		this.page=page ;
	}


    public String getHomePageTitle()
    {
    	String title = page.title();
    	System.out.println("the title is:" + title);
    	return title;
    	
    }
    
    
    public String getHomePageUrl()
    {
    	String url  = page.url();
    	System.out.println("the url is:" + url);
    	return url;
    	
    }
    
    public void doSearch(String productName)
    {
    	page.fill(search, productName);
    	page.click(searchIcon);
    }
    
    
    public LoginPage navigatetoLoginPage()
    {
    	page.click(MyaccountLink);
    	page.click(login);
    	return new LoginPage(page);
    	
    }
    
}
