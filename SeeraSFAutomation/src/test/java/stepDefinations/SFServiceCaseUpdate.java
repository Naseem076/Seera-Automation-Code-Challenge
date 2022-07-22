package stepDefinations;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.SfPgObj;

public class SFServiceCaseUpdate extends Base
{

	WebDriver driver;
	Base baseobj = new Base();
	
	
	//**********************SALESFORCE APPLICATION LOGIN SCENARIO*********************************//
	
		@Given("^user is on the salesforce application login page for Cases")
	    public void user_is_on_the_salesforce_application_login_page_for_Cases() throws IOException, InterruptedException 
		{
			driver = baseobj.setup("no");
			driver.get(base.Config.getInstance().getString("SalesforceLoginURL"));
			Thread.sleep(5000);  
	    }

		 @When("user enters SFusername2 {string} and SFpassword2 {string} and clicks on Login button")
		    public void user_enters_sfusername2_something_and_sfpassword2_something_and_clicks_on_login_button(String userName, String password) throws InterruptedException
		    {
			 userName = base.Config.getInstance().getSFUserName();
			 password = base.Config.getInstance().getSFPassword();
		     Base.SFApplicationLogin(userName, password);
		    }

		 @Then("^validate user login successfully and salesforce home page is displayed for Cases")
		    public void validate_user_login_successfully_and_salesforce_home_page_is_displayed_for_Cases() throws InterruptedException 
		 {
		    String expectedHomepage="Home";
			 Base.SFHomePageValidation(expectedHomepage);  
			
		 }
		 
	//*************OPEN NEWLY CREATED CASE FROM 1ST SCENARIO TO CHANGE CASE OWNER TO QUEUE**********************//
		 
		 @Given("^user is on Service Home page on login$")
		    public void user_is_on_service_home_page_on_login() throws IOException, InterruptedException 
		 	{
			 SfPgObj.sFServiceHomePageValidation();
		    }

		    @When("^user navigate to Cases via AppLauncher and open newly created case from 1st scenario to change Case Owner to QueueName as (.+) and clicks on Save button$")
		    public void user_navigate_to_cases_via_applauncher_and_open_newly_created_case_from_1st_scenario_to_change_case_owner_to_queuename_as_and_clicks_on_save_button(String queue) throws IOException, InterruptedException 
		    {
		        SfPgObj.sFCaseUpdate(queue);
		    }

		    @Then("validate Case Owner field value is updated to Queue and case saved successfully {string}")
		    public void validate_case_owner_field_value_is_updated_to_queue_and_case_saved_successfully_something(String expectedCaseUpdateMessage) throws IOException, InterruptedException 
		    {
		       String expectedCaseUpdatedMessage="Case updated";
		    	SfPgObj.sFCaseUpdateValidation(expectedCaseUpdatedMessage);
		    }
}
