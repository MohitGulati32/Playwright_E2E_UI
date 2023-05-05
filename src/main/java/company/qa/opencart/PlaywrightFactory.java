package company.qa.opencart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;

	private static  ThreadLocal<Browser> tlbrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlbrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlpage = new ThreadLocal<>();
	private  static ThreadLocal<Playwright> tlplaywright = new ThreadLocal<>();



	public static Playwright getPlaywright()
	{
		return tlplaywright.get();

	}


	public static Page getPage()
	{
		return tlpage.get();

	}


	public static BrowserContext getBrowserContext()
	{
		return tlbrowserContext.get();

	}


	public static Browser getBrowser()
	{
		return tlbrowser.get();

	}




	public Page initBrowser(Properties prop)
	{
		String browserName= prop.getProperty("browser").trim();
		System.out.println("the browser name is:" + browserName );
		//playwright =Playwright.create();
		tlplaywright.set(Playwright.create());

		switch (browserName.toLowerCase()) {
		case "chromium":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlbrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "firefox":
			//browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlbrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "safari":
			//browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlbrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));

			break;	

		case "chrome":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			tlbrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;		

		default:
			System.out.println("please pass the browser name...");
			break;
		}



		//browserContext = browser.newContext();

		//page = browserContext.newPage();


		tlbrowserContext.set(getBrowser().newContext());
		tlpage.set(getBrowserContext().newPage());

		getPage().navigate(prop.getProperty("url").trim());


		return getPage() ;


	}

	//method to initialize the properties  from config file
	public Properties init_prop()  {


		try {

			FileInputStream ip = new FileInputStream("./resources/config/config.properties");
			prop =new Properties();
			prop.load(ip);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} 



		return prop;
	}

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		//getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);

		return base64Path;
	}


}	
