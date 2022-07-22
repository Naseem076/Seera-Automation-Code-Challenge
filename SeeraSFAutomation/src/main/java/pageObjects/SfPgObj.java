package pageObjects;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.Config;

import utilities.Utitilities;


public class SfPgObj extends Utitilities
{

	
	public SfPgObj(WebDriver driver) 
	{
		super(driver);
		
	}
	
	static String randomAccountName=genrateRandomString();
	static String randomAccountNumber=randomNumber();
	

	
	
 //****************Xpath for Salesforce Application Login Page ***********************//
	
	@FindBy(xpath="//img[@alt='Salesforce']")
	static WebElement salesforceLoginPage;
	
	@FindBy(xpath="//input[@type='email']")
	static WebElement userName;
	
	@FindBy(xpath="//input[@type='password']")
	static WebElement UserPassowrd;
	
	@FindBy(xpath="//input[@type='submit']")
	static WebElement loginButton;

	
	//***************Salesforce Application Login Method*******************//
	 
		public static void sFLogin(String loginUser,String loginPassword) throws InterruptedException
		{
			
			
			waitForElementVisbility(salesforceLoginPage);
			LOGGER.info("Waiting for Salesforce Login page to load");
			
			String actualTitleLoginPage=driver.getTitle();
			Assert.assertEquals(actualTitleLoginPage, "Login | Salesforce", "User is not on the salesforce Login Page");
			System.out.println("Salesforce Login page validation is completed successfully");
			
			sendKeys(userName,loginUser);
			LOGGER.info("Entered SF User Name");
				
			sendKeys(UserPassowrd,loginPassword);
			LOGGER.info("Entered SF password");
				
			javaClick(loginButton);
		    LOGGER.info("Clicked on Log In button.");
		    
			
		}
	
//****************Xpath for Salesforce Application Home Page**********************************************************//	
	
	
	
	@FindBy(xpath="(//a[@title='Home'])[1]")
	static WebElement sfHomeTab;
	
	@FindBy(xpath="//div[@class='slds-icon-waffle']")
	static WebElement appLauncherButton;
	
	@FindBy(xpath="//p[text()='Service']")
    static WebElement serviceApp;
	

	//***************Salesforce Application Home Page Validation Method*******************//
	 
		public static void sFHomePageValidation(String expectedHomepage) throws InterruptedException
		{

			
			
			waitForElementVisbility(sfHomeTab);
			LOGGER.info("Wait for Home page to load");
			
			
			String actualHomepage=sfHomeTab.getText();
			System.out.println(actualHomepage);
			
			if(sfHomeTab.isDisplayed()!=false)
			{
				
				Assert.assertEquals(actualHomepage, expectedHomepage, "User is not on the Salesforce Home Page");
				LOGGER.info("Validation of Home page tab");
				System.out.println("User logged in SF Application successfully and on SF Home page");
				
			}
			
			waitForClickableElement(appLauncherButton);
			javaClick(appLauncherButton);
			LOGGER.info("Clicked on App Launcher on SF Home Page");
			
			waitForClickableElement(serviceApp);
			javaClick(serviceApp);
		    LOGGER.info("Clicked on Service App on Applauncher flyout to navigate to Service Home page");
		    
		    
		}
	
	//*****************Xpath for Serivice Apps page*********************************//
		
		@FindBy(xpath="//span[@title='Service']")
	    static WebElement serviceAppPage;
		
		
		@FindBy(xpath="//a[@title='Accounts']")
		static WebElement accountsTab;
		
		
		@FindBy(xpath="//div[@title='New']")
		static WebElement accountNewButton;
		
		@FindBy(xpath="//span[text()='Accounts']/parent::div")
		static WebElement accountsPage;
		
		
		@FindBy(xpath="//h2[text()='New Account']")
		static WebElement newAccountPage;
		
		@FindBy(xpath="//input[@name='Name']")
		static WebElement accountNameField;
		
		@FindBy(xpath="//input[@name='AccountNumber']")
		static WebElement accountNumberField;
		
		@FindBy(xpath="//button[@name='SaveEdit']")
		static WebElement accountSaveButton;
		
		
		
		@FindBy(xpath="//lightning-formatted-text[@class='custom-truncate']")
		static WebElement newAccountName;
		
	//*****************Account creation in Service Apps****************************//
		
		public static void sFAccountCreation(String accountName, String accountNumber) throws InterruptedException
		{
             
			waitForElementVisbility(serviceAppPage);
			
			Assert.assertTrue(serviceAppPage.isDisplayed(), "User is not navigated to Service App Page");
			System.out.println("User is on Service App Home Page");
			
			javaClick(accountsTab);
			LOGGER.info("Clicked on Account Tab");
			
            waitForElementVisbility(accountsPage);
			
			Assert.assertTrue(accountsPage.isDisplayed(), "User is not navigated to Accounts home Page");
			System.out.println("User is on Accounts Home Page");
			
			click(accountNewButton);
			LOGGER.info("Clicked on New button to create new account");
			waitForElementVisbility(newAccountPage);
			
			Assert.assertTrue(newAccountPage.isDisplayed(), "User is not navigated to New Account Page");
			System.out.println("User is on New Account Page");
			
			Thread.sleep(1000);
			randomAccountName=genrateRandomString();
			sendKeys(accountNameField,randomAccountName);
			LOGGER.info("Entered Account Name");
			
			Thread.sleep(1000);
			randomAccountNumber=randomNumber();
			sendKeys(accountNumberField,randomAccountNumber);
			LOGGER.info("Entered Account Number");
			
			click(accountSaveButton);
			LOGGER.info("Clicked Save button to create new account");
			
			waitForElementVisbility(newAccountName);
			String actualnewAccountName=newAccountName.getText();
			System.out.println(actualnewAccountName);
			
			Assert.assertEquals(actualnewAccountName, randomAccountName, "New account is not created successfully");
			LOGGER.info("Validation of New Account creation");
			System.out.println("User created new account successfully and is on Related List tab of Account");
			
			
		}
		
		
		//*****************Xpath for Contact page*********************************//
		
		
		@FindBy(xpath="//a[@data-label='Related']")
	    static WebElement relatedListTab;
				
		@FindBy(xpath="(//a[@title='New'])[2]")
	    static WebElement contactNewButton;
		
		//h2[text()='New Contact']
		@FindBy(xpath="//h2[text()='New Contact']")
	    static WebElement newContactPage;
		
		@FindBy(xpath="//input[@name='lastName']")
	    static WebElement contactLastNameField;
				
		@FindBy(xpath="//input[@name='firstName']")
		static WebElement contactLFirstNameField;
		
		
		@FindBy(xpath="//button[@name='SaveEdit']")
		static WebElement contactSaveButton;
		
		
		
		@FindBy(xpath="//span[@title='Contacts']")
		static WebElement contactTabSection;
		
		//*[@id="window"]
		@FindBy(xpath="//*[@id='window' or @id='tab-6']")
		static WebElement contactFullName;
	
		//*****************Contact creation in Service Apps****************************//
		
				public static void sFContactCreation(String firstName, String lastName) throws InterruptedException
				{
					
					waitForElementVisbility(relatedListTab);
					
					Assert.assertTrue(relatedListTab.isDisplayed(), "User is not navigated to Related list Tab of new Account Page");
					System.out.println("User is on Related list tab of new account page");
					
					javaClick(contactNewButton);
					
					LOGGER.info("Clicked on New button to create new Contact");
					waitForElementVisbility(newContactPage);
					
					Assert.assertTrue(newContactPage.isDisplayed(), "User is not navigated to New Contact Page");
					System.out.println("User is on New Contact Page");
					
					sendKeys(contactLFirstNameField,firstName);
					LOGGER.info("Entered Contact First Name");
					
					sendKeys(contactLastNameField,lastName);
					LOGGER.info("Entered Contact Last Name");
					
					click(contactSaveButton);
					LOGGER.info("Clicked Save button to create new Contact");
					
					
				}

				public static void validateCreatedContact(String expectedContactName) throws InterruptedException
				{
					waitForElementVisbility(contactTabSection);
					LOGGER.info("Wait for Contact Tab Section to load");
					System.out.println("User is on New Contact Tab Section");
					
					boolean fullNameflag=contactFullName.isDisplayed();
					System.out.println(fullNameflag);
					String full=contactFullName.getText();
					//System.out.println(full);
					
				}
	
				
		//*****************Xpath for Case page*********************************//
				
				@FindBy(xpath="(//a[@title='New'])[4]")
			    static WebElement caseNewButton;
				
				@FindBy(xpath="//h2[text()='New Case']")
			    static WebElement newCasePage;
				
				//input[@title='Search Contacts']
				@FindBy(xpath="//input[@title='Search Contacts']")
			    static WebElement contactNamefield;
				
				
				@FindBy(xpath="(//div[@title='ContactFN1 ContactLN1'])[1]")
				static WebElement contactNameValue;
				
				//input[@title='Search Accounts']
				@FindBy(xpath="//input[@title='Search Accounts']")
			    static WebElement accountNamefield;
				
				
				
				
				@FindBy(xpath="(//a[@role='button'][normalize-space()='--None--'])[1]")
				static WebElement caseOriginfield;
				
				//a[text()='Phone']
				@FindBy(xpath="//a[text()='Phone']")
				static WebElement caseOriginValue;
				
				//button[@title='Save']
				@FindBy(xpath="//button[@title='Save']")
				static WebElement caseSaveButton;
				
		//*****************Case creation in Service Apps****************************//
				
		public static void sFCaseCreation(String contactName, String accountName) throws InterruptedException
				{
					
			        waitForClickableElement(caseNewButton);
					javaClick(caseNewButton);
		            LOGGER.info("Clicked on New button to create new Case");
		            Thread.sleep(2000);
		            
		            
		//			waitForElementVisbility(newCasePage);
		//			driver.navigate().refresh();
					
		 		    javaClick(caseOriginfield);
					LOGGER.info("Clicked to open the picklist values of Case Origin field");
					
					Thread.sleep(2000);
					javaClick(caseOriginValue);
					LOGGER.info("Select picklist value of case Origin Field");
					
					Thread.sleep(2000);
		//			Assert.assertTrue(newCasePage.isDisplayed(), "User is not navigated to New Case Page");
		//			System.out.println("User is on New Case Page");
					
					sendKeys(contactNamefield,contactName);
					LOGGER.info("Entered Contact Name");
					Thread.sleep(2000);
					javaClick(contactNameValue);
					LOGGER.info("Select Contact from dropdown");
					Thread.sleep(2000);
					
					//sendKeys(accountNamefield,randomAccountName);
					//LOGGER.info("Entered Account Name");
					
					
					//refreshPageNavigate();
					//LOGGER.info("refresh page to load Account name");
					
					
					

					click(caseSaveButton);
					LOGGER.info("Clicked Save button to create new Case");
					
					
				}
		
		
		//******************NEWLY CREATED CASE VALIDATION***********************//
		public static void sFCaseCreatedValidation() throws InterruptedException
		{
			waitForElementVisbility(casesTabSection);
			
			Assert.assertTrue(casesTabSection.isDisplayed(), "Cases Tab Section is not shown after case creation");
			System.out.println("Cases Tab Section is shown after case creation");
		}
	
		//*****************Xpath for Cases  page*********************************//
		
		//span[@title='Cases']	
		        @FindBy(xpath="//span[@title='Cases']")
		        static WebElement casesTabSection;
		
				@FindBy(xpath="//a[@title='Cases']")
				static WebElement casesTab;
				
				//span[normalize-space()='00001042']
				@FindBy(xpath="//span[normalize-space()='00001042']")
				static WebElement cases;
				
				//a[normalize-space()='00001043']
				@FindBy(xpath="//a[normalize-space()='00001043']")
				static WebElement caseValue;
				
				
				@FindBy(xpath="//*[name()='svg' and @data-key='change_owner']//*[local-name()='g']")
			    static WebElement changeOwnerButton;
				
				
				//h2[text()='Change Case Owner']
				@FindBy(xpath="//h2[text()='Change Case Owner']")
			    static WebElement changeCaseOwnerPage;
				
				//input[@title='Search Users']
				@FindBy(xpath="//input[@title='Search Users' or @title='Search Queues']")
			    static WebElement changeOwnerSearch;
				
				
				@FindBy(xpath="//a[@aria-label='Enter new owner name—Current Selection: Users, Pick an object']")
			    static WebElement changeCaseOwnerTypeButton;
				
				//span[@title='Queues']
				@FindBy(xpath="//span[@title='Queues']")
			    static WebElement queuesSelect;
				
				//a[text()='Dummy Queue']
				@FindBy(xpath="//a[text()='Dummy Queue']")
			    static WebElement queuesSelectValues;
				
				//button[@name='change owner']
				@FindBy(xpath="//button[@name='change owner']")
			    static WebElement changeCaseOwnerButton;
				
				
				//span[@class='preamble_custom-preamble']
				@FindBy(xpath="//span[@class='preamble_custom-preamble']")
			    static WebElement caseUpdatedMessage;
				
		//****************OPEN NEWLY CREATED CASE AND CHANGE CASE OWNER TO QUEUE***************//
		
				public static void sFServiceHomePageValidation() throws InterruptedException
				{
					
					waitForElementVisbility(serviceAppPage);
					
					Assert.assertTrue(serviceAppPage.isDisplayed(), "User is not navigated to Service App Page");
					System.out.println("User is on Service App Home Page");
				}
		
	//****************OPEN NEWLY CREATED CASE AND CHANGE CASE OWNER TO QUEUE***************//
		
		public static void sFCaseUpdate(String queue) throws InterruptedException
		{
			
			
			waitForClickableElement(casesTab);
			javaClick(casesTab);
		    LOGGER.info("Clicked on Cases Tab in Service Page");
		    
		    waitForClickableElement(caseValue);
			javaClick(caseValue);
		    LOGGER.info("Clicked on existing cases");
		    
		    //waitForClickableElement(changeOwnerButton);
		    waitForElementVisbility(changeOwnerButton);
		   
		    Boolean change=changeOwnerButton.isDisplayed();
		    System.out.println(change);
			click(changeOwnerButton);
		    LOGGER.info("Clicked on Change Owner Button to change case owner");
		    
		    waitForElementVisbility(changeCaseOwnerPage);
		    Assert.assertTrue(changeCaseOwnerPage.isDisplayed(), "User is not navigated to Change Case Owner Page");
		    System.out.println("User is navigated to Change Case Owner Page");
		    
		    
		    waitForClickableElement(changeCaseOwnerTypeButton);
			javaClick(changeCaseOwnerTypeButton);
		    LOGGER.info("Clicked on Change Case owner Type Button");
		    
		    Thread.sleep(2000);
		    click(queuesSelect);
		    
		   
		    waitForClickableElement(changeOwnerSearch);
		    sendKeysEnter(changeOwnerSearch,queue);
			LOGGER.info("Entered queue as Dummy Queue value");
			
			waitForClickableElement(queuesSelectValues);
			click(queuesSelectValues);
			LOGGER.info("Select Dummy Queue value from Search Result page");
		    
			waitForClickableElement(changeCaseOwnerButton);
			click(changeCaseOwnerButton);
			LOGGER.info("Select Dummy Queue value from Search Result page");
			
		}
			
		public static void sFCaseUpdateValidation(String expectedCaseUpdatedMessage) throws InterruptedException
		{
			
			waitForClickableElement(caseUpdatedMessage);
			String actualCaseUpdateMessage= caseUpdatedMessage.getText();
			LOGGER.info("Get updated message of Case from Feed Related List");
			
			Assert.assertEquals(actualCaseUpdateMessage, expectedCaseUpdatedMessage, "Case from 1st scenario case owner field value is not updated to Dummy Queue");
			System.out.println(" Case from 1st scenario case owner field value is not updated to Dummy Queue");
			
		    
		}
		
}
