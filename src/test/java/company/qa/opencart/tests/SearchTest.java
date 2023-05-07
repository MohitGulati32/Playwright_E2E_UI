package company.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import org.testng.annotations.DataProvider;


import BaseTests.Base;
import constants.AppConstants;

public class SearchTest extends Base{


	
	@Test
	
	public void TitleTest()
	{
		String actualTitle=  homepage.getHomePageTitle();
		AssertJUnit.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
	}

	@Test(dataProvider="getProductData")
	public void searchTest(String productName )
	{
		homepage.getHomePageTitle();
		homepage.getHomePageUrl();
		homepage.doSearch(productName);
	}

	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][]
				{
			      {"Macbook"}, 
			      {"Imac"},
			      {"Samsung"}
				};
	}


}
