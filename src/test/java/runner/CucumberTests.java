package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"json:target/cucumber-json/cucumber.json"},
		glue = {"stepdefs"},
		features = {"src/test/features"} )
public class CucumberTests {}
