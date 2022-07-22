Feature: FIRST SCENARIO - To create Accounts Contacts and Cases on Service App in Salesforce Application

@SFLoginPage
Scenario: To login into Salesforce Application  
Given user is on the salesforce application login page
When user enters SFusername "harsh.chaturvedi@wise-raccoon-vgnfqt.com" and SFpassword "Seer@123" and clicks on Login button
Then validate user login successfully and salesforce home page is displayed


@SFAccountCreation
Scenario: To create new Account in Service App
Given user is on SF Home Page
When User navigate to Service App via AppLaucher then Create new Account and Enters Account Name as "AccountName1" and Account Number as "Account12345" and clicks on Save button
Then validate new account has been created successfully and user is on newly created Account "AccountName"



@SFContactCreation
Scenario: To create new Contact using the same above newly created Account
Given user is on Related List Tab of newly created Account Page
When User navigate to Contact section then Create new Contact via New Button and Enters Contact <firstName>  and Contact <lastName> and clicks on Save button
Then validate new contact has been created successfully and shown in Contact section of related list tab of Account "ContactName"

Examples:
|firstName		|lastName		|
|ContactFN1		|ContactLN1		|


@SFCaseCreation
Scenario: To create new Case using the same above newly created Account and Contact
Given user is on Related List Tab of newly created Account Page
When User navigate to Contact section then Create new Cases via New Button and Enters above Contact Name <contactName> and Account Name <accountName>  and clicks on Save button
Then validate new Cases has been created successfully and shown in Cases section of related list tab of Account "CaseNumber"

Examples:
|contactName			|accountName			|
|ContactFN1	ContactLN1	|Account Name2			|

