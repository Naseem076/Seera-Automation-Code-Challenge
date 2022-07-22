package stepDefinations;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.SfPgObj;

public class SFServiceAppsFirstScenarioSD extends Base 
{
	
	WebDriver driver;
	Base baseobj = new Base();
	
	
	//**********************SALESFORCE APPLICATION LOGIN SCENARIO*********************************//
	
	@Given("^user is on the salesforce application login page")
    public void user_is_on_the_salesforce_application_login_page() throws IOException, InterruptedException 
	{
		driver = baseobj.setup("no");
		driver.get(base.Config.getInstance().getString("SalesforceLoginURL"));
		Thread.sleep(5000);  
    }

	 @When("user enters SFusername {string} and SFpassword {string} and clicks on Login button")
	    public void user_enters_sfusername_something_and_sfpassword_something_and_clicks_on_login_button(String userName, String password) throws InterruptedException
	    {
		 userName = base.Config.getInstance().getSFUserName();
		 password = base.Config.getInstance().getSFPassword();
	     Base.SFApplicationLogin(userName, password);
	    }

	 @Then("^validate user login successfully and salesforce home page is displayed")
	    public void validate_user_login_successfully_and_salesforce_home_page_is_displayed() throws InterruptedException 
	 {
	    String expectedHomepage="Home";
		 Base.SFHomePageValidation(expectedHomepage);  
		
	 }
	
	 
	//**********************ACCOUNT CREATION SCENARIO*********************************//
	 @Given("^user is on SF Home Page$")
	    public void user_is_on_sf_home_page() throws IOException, InterruptedException 
	    {
	        
	    }
	 
	 @When("User navigate to Service App via AppLaucher then Create new Account and Enters Account Name as {string} and Account Number as {string} and clicks on Save button")
	    public void user_navigate_to_service_app_via_applaucher_then_create_new_account_and_enters_account_name_as_something_and_account_number_as_something_and_clicks_on_save_button(String accountName, String accountNumber) throws IOException, InterruptedException 
	    {
		 
		 SfPgObj.sFAccountCreation(accountName, accountNumber);
	    }

	    @Then("validate new account has been created successfully and user is on newly created Account {string}")
	    public void validate_new_account_has_been_created_successfully_and_user_is_on_newly_created_account_something(String strArg1) throws IOException, InterruptedException 
	    {
	       
	    }
	 
	  //**********************CONTACT CREATION SCENARIO***********************************************************//
	    
	    @Given("^user is on Related List Tab of newly created Account Page$")
	    public void user_is_on_related_list_tab_of_newly_created_account_page() throws IOException, InterruptedException 
	    {
	        
	    }
	    
	    @When("^User navigate to Contact section then Create new Contact via New Button and Enters Contact (.+)  and Contact (.+) and clicks on Save button$")
	    public void user_navigate_to_contact_section_then_create_new_contact_via_new_button_and_enters_contact_and_contact_and_clicks_on_save_button(String firstName, String lastName) throws IOException, InterruptedException
	    {
	    	Base.SFContactCreation(firstName, lastName);
	    }

	    @Then("validate new contact has been created successfully and shown in Contact section of related list tab of Account {string}")
	    public void validate_new_contact_has_been_created_successfully_and_shown_in_contact_section_of_related_list_tab_of_account(String expectedContactName) throws IOException, InterruptedException 
	    {
	    	expectedContactName="Contact1stName2 Contact2ndName2";
	        Base.ValidatedCreatedContact(expectedContactName);
        }
	    
	    //**********************CASE CREATION SCENARIO*********************************//
	    
	    @Given("^user is on Related List Tab of newly created Account Page1$")
	    public void user_is_on_related_list_tab_of_newly_created_account_page1() throws IOException, InterruptedException 
	    {
	        
	    }

	    @When("^User navigate to Contact section then Create new Cases via New Button and Enters above Contact Name (.+) and Account Name (.+)  and clicks on Save button$")
	    public void user_navigate_to_contact_section_then_create_new_cases_via_new_button_and_enters_above_contact_name_and_account_name_and_clicks_on_save_button(String contactName, String accountName) throws IOException, InterruptedException 
	    {
	        Base.SFCaseCreation(contactName, accountName);
	    }

	    @Then("^validate new Cases has been created successfully and shown in Cases section of related list tab of Account \"([^\"]*)\"$")
	    public void validate_new_cases_has_been_created_successfully_and_shown_in_cases_section_of_related_list_tab_of_account_something(String strArg1) throws IOException, InterruptedException 
	    {
	        SfPgObj.sFCaseCreatedValidation();
	    }
}
