package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import base.Base;

public class Utitilities extends Base
{
	public static final Logger LOGGER = LogManager.getLogger(Utitilities.class);

	protected static WebDriver driver;

	public Utitilities (WebDriver driver) 
	{
		this.driver = driver;
	}

	public void pause(long time) 
	{
		try 
		{
			Thread.sleep(time);
		} 
		catch (Exception e) 
		{
		}
	}   

	
	//**********Wait for element to be clickable. Parameter - Pagefactory element*******//
	
	public static WebDriver waitForClickableElement(WebElement element) throws InterruptedException
	{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(90));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			LOGGER.info("Element clickable.");
		
		return (driver);
	}
	
	
	//************Method to perform sendkeys - parameter is page factory element**********//
	
			public static void sendKeys(WebElement element, String key) throws InterruptedException
			{
				waitForClickableElement(element);	
				element.clear();
				element.sendKeys(key);
				element.sendKeys(Keys.TAB);
				
			}
		
			//************Method to perform sendkeys - parameter is page factory element**********//
			
			public static void sendKeysEnter(WebElement element, String key) throws InterruptedException
			{
				waitForClickableElement(element);	
				element.clear();
				element.sendKeys(key);
				element.sendKeys(Keys.TAB);
				element.sendKeys(Keys.ENTER);
				
			}
			
		//*****************Method to perform sendkeys - parameter is xpath of String type************//
			
			public void xpathSendKeys(String xpath, String key) throws InterruptedException
			{
				waitForClickableDynamicElement(By.xpath(xpath));	
				driver.findElement(By.xpath(xpath)).clear();
				driver.findElement(By.xpath(xpath)).sendKeys(key);
				LOGGER.info("Input added: "+key);
			}
			
		//************Method to perform sendkeys - parameter is page factory element*****************//
			
			public void sendKeysWithoutClicking(WebElement element, String key) throws InterruptedException
			{
				waitForElementVisbility(element)	;
				element.clear();
				element.sendKeys(key);
			}
		
			
			//*************Wait for element to be visible. Parameter - Pagefactory element*************//
			
			public static WebDriver waitForElementVisbility(WebElement element) throws InterruptedException
			{
					WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(90));
					wait.until(ExpectedConditions.visibilityOf(element));
					LOGGER.info("Element visible.");
				return (driver);
			}
			
			//************Wait for element to be visible. Parameter -  element locator***********//
			
			public static WebDriver waitForDynamicElementVisbility(By element) throws InterruptedException
			{	
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
					wait.until(ExpectedConditions.visibilityOfElementLocated(element));
					LOGGER.info("Element found.");
				
				return (driver);
			}
			
			//***********Wait for element to be present. Parameter - element locator*************//
			
			public static WebDriver waitForElementToBePresent(By element) throws InterruptedException
			{
					WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(90));
					wait.until(ExpectedConditions.presenceOfElementLocated(element));
					LOGGER.info("Element visible.");
				return (driver);
			}
			
				
			
			//***********Wait for element to be clickable. Parameter - element locator************//
			
			public static WebDriver waitForClickableDynamicElement(By element) throws InterruptedException
			{
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
					wait.until(ExpectedConditions.elementToBeClickable(element));
					LOGGER.info("Element found.");
				
				return (driver);
			}
			
			
			//*****************Method to perform a click after element is loaded - parameter is page factory element********//
			
			public static void click(WebElement element) throws InterruptedException
			{
				waitForClickableElement(element);
				element.click();
				LOGGER.info("Button clicked.");
			}
			
		//Method to perform a click after element is loaded - parameter is xpath of String type
			public void xpathClick(String xpath) throws InterruptedException
			{
				waitForClickableDynamicElement(By.xpath(xpath));
				driver.findElement(By.xpath(xpath)).click();
				LOGGER.info("Button clicked.");
				pause(2000);	
			}
		
			
		//Method to perform javascript click
			public static void javaClick(WebElement element) throws InterruptedException
			{
				waitForClickableElement(element);
				JavascriptExecutor executor1 = (JavascriptExecutor)driver;
				executor1.executeScript("arguments[0].click();", element);
				LOGGER.info("Performed java click.");
			}
			
		//Method to perform javascript click - parameter is xpath of String type
			public static void javaXpathClick(String xpath) throws InterruptedException
			{
				waitForClickableDynamicElement(By.xpath(xpath));
				JavascriptExecutor executor1 = (JavascriptExecutor)driver;
				executor1.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
				LOGGER.info("Performed java click.");
			}
			
		//Method to display button name after clicking it.
			public void clickedOn(String buttonName) throws InterruptedException
			{
				LOGGER.info("Clicked on "+buttonName);
			}
			
			
		//Method to scroll to a particular element on page
			public static void scrollToElement(WebElement element)
			{
				JavascriptExecutor executor1 = (JavascriptExecutor)driver;
				executor1.executeScript("arguments[0].scrollIntoView(true);",element);
				LOGGER.info("Scrolled to the element.");
				
			}	
				
		//Method to refresh the page using navigate()
			public static void refreshPageNavigate() throws InterruptedException
			{
				driver.navigate().refresh();
			}
			
			
			//Generate Random String and append with Prefix words
			
			public static String genrateRandomString()
			{
				String preWord="AccountName_";
				String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

			    // create random string builder
			    StringBuilder sb = new StringBuilder();

			    // create an object of Random class
			    Random random = new Random();

			    // specify length of random string
			    int length = 10;

			    for(int i = 0; i < length; i++) {

			      // generate random index number
			      int index = random.nextInt(alphabet.length());

			      // get character specified by index
			      // from the string
			      char randomChar = alphabet.charAt(index);

			      // append the character to string builder
			      
			      sb.append(randomChar);
			    }

			    String randomString = (preWord).concat(sb.toString());
			    return randomString;
			}
          
			//Generate Random Integers 
			
			public static String randomNumber()
			{
				String prefixWord="AccountNumber-";
				int min = 9;  
				int max = 99;  
				StringBuilder sb = new StringBuilder();
				//Generate random int value from 200 to 400   
				System.out.println("Random value of type int between "+min+" to "+max+ ":");  
				int randNumber = (int)(Math.random()*(max-min+1)+min);  
				sb.append(randNumber);
				String alphaNumeric=(prefixWord)+(sb.append(randNumber));
				
				return alphaNumeric;
			}
				
			
			// Method to get Attribute of web element
			
			public static String getAttributeMethod(String xpath, String attributeValue)
			{
				String getAttributeValue=driver.findElement(By.xpath(xpath)).getAttribute(attributeValue);
				return getAttributeValue;
			}
			
			//set current date ( change the format based on requirement here, will be using MM/dd/yyyy format)
			
			public static Date currentDate(String format)
			{
				final DateFormat dateFormat = new SimpleDateFormat(format);
				Date currentDate = new Date();
		        System.out.println(dateFormat.format(currentDate));
		        return currentDate;

			}
			
			//adding number of days, number of years and Month in current date ( format can be changed based on requirement)
			
			public static Date currentDatePlusOne(String format)
			{
				final DateFormat dateFormat = new SimpleDateFormat(format);
				Date currentDate = new Date();
				
				Calendar c = Calendar.getInstance();
		        c.setTime(currentDate);
		        c.add(Calendar.YEAR, 1);
		        c.add(Calendar.MONTH, 1);
		        c.add(Calendar.DATE, 1);
		        
		        Date currentDatePlusOne = c.getTime();

		        System.out.println(dateFormat.format(currentDatePlusOne));
		        
		        return currentDatePlusOne;
			}
//***************************************************************************************************************************//
			
			
}
