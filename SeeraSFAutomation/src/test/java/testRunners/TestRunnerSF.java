package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/main/resources/testDataFeature",
        glue = "stepDefinations",
      
        plugin = {"pretty",
        		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				
                }     
        
        )

public class TestRunnerSF extends AbstractTestNGCucumberTests
{

 }
   



