package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


import io.github.bonigarcia.wdm.WebDriverManager;

import pageObjects.SfPgObj;

public class Base 
{
public static WebDriver driver;
	
	public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
	
	 public static Config settings;
	  
	 public static String testName;
	
	  @Parameters({"headless"})
	  @BeforeMethod
	  public WebDriver setup(String headless) throws IOException
	  {
		  
	        WebDriverManager.chromedriver().setup();
	        
	        ChromeOptions options = new ChromeOptions();
	        
	        if(headless.equalsIgnoreCase("yes"))
            {
              System.out.println("Browser will run in headless mode.");
              options.addArguments("--headless");
            }
            else
            {
              System.out.println("Browser will run in headed mode.");
            }
	        
	        
	        driver = new ChromeDriver(options);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
	    	driver.manage().window().maximize();

	    	//set driver to threadDriver so that we can reference the instance of same driver where ever required via getDriver method

	    	threadDriver.set(driver);
	    	return getDriver();
	    }
	  
	  public static WebDriver getDriver() 
	  {
			return threadDriver.get();
	  }
	  
	  //Method to get the name of the test in execution
	  
	  @BeforeMethod
	  public void getTestDetails(ITestContext testNameFetcher, Method method)
	  {
			final String xmlTestName = testNameFetcher.getName();
			final String methodName = method.getName();
		    testName = xmlTestName + "." + methodName;
		    
		    System.out.println("Test to run is: "+testName);
		    
	  }

	  	//Method to close the browser after test execution
	  
		@AfterMethod
		public void closeBrowser()
		{
			if(driver!=null)
			{
				 System.out.println("Test browser before closing");
				 driver.quit();
				 System.out.println("Test browser");
			}
		 
		}
		
		//Method to take UI screenshot when a test fails.
		public void takeScreenshot()
		{
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss");  
			 LocalDateTime now = LocalDateTime.now();  
			
			String filepath;
			
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			
		       if(System.getProperty("os.name").contains("Windows"))
		    	   filepath = System.getProperty("user.dir")+"\\Screenshots//";
	              else if(System.getProperty("os.name").contains("Mac"))
	            	  filepath = System.getProperty("user.dir")+"/Screenshots/";
	              else
	            	  filepath = "./Screenshots/";
			
			
			try 
			{
				FileUtils.copyFile(srcFile, new File(filepath+dtf.format(now).replace(":", "")+dtf.format(now).replaceAll("[^a-zA-Z0-9]", "")+testName+"_"+".jpg"));
				System.out.println("Screenshot taken.");
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		//************** Salesforce Application Method*********************//
		
		protected static SfPgObj SfPg;
		
	 	//Salesforce Application login with username and password
	    
	    public synchronized static void SFApplicationLogin(String userName, String password) throws InterruptedException 
	    {
	    	SfPg = PageFactory.initElements(driver, SfPgObj.class);
	    	SfPgObj.sFLogin(userName, password);
	    }
	    
	    public synchronized static void SFHomePageValidation(String expectedHomepage) throws InterruptedException 
	    {
	    	SfPg = PageFactory.initElements(driver, SfPgObj.class);
			SfPgObj.sFHomePageValidation(expectedHomepage);
	    } 

	    
	    public synchronized static void SFContactCreation(String firstName, String lastName) throws InterruptedException 
	    {
	    	SfPg = PageFactory.initElements(driver, SfPgObj.class);
			SfPgObj.sFContactCreation(firstName, lastName);
	    } 
	    
	    public synchronized static void ValidatedCreatedContact(String expectedContactName) throws InterruptedException 
	    {
	    	SfPg = PageFactory.initElements(driver, SfPgObj.class);
			SfPgObj.validateCreatedContact(expectedContactName);
	    }

		public synchronized static void SFCaseCreation(String contactName, String accountName) throws InterruptedException
		{
			SfPg = PageFactory.initElements(driver, SfPgObj.class);
			SfPgObj.sFCaseCreation(contactName, accountName);
			
		} 
	    
}


