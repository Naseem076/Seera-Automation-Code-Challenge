Feature: SECOND SCENARIO - Open Cases to Change Case Owner field to Queue Name - Dummy Queue


@SFLoginPage
Scenario: To login into Salesforce Application  
Given user is on the salesforce application login page for Cases
When user enters SFusername2 "harsh.chaturvedi@wise-raccoon-vgnfqt.com" and SFpassword2 "Seer@123" and clicks on Login button
Then validate user login successfully and salesforce home page is displayed for Cases

@SFCaseUpate
Scenario: Open Cases to Change Case Owner field to Queue Name - Dummy Queue  
Given user is on Service Home page on login
When user navigate to Cases via AppLauncher and open newly created case from 1st scenario to change Case Owner to QueueName as <queue> and clicks on Save button
Then validate Case Owner field value is updated to Queue and case saved successfully "Case updated"

Examples:
|queue		|
|Dummy Queue|