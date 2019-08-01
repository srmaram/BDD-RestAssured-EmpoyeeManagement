import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/IdeaProjects/EmployeeManagement/src/test/features/ManageEmployee.feature"},
        plugin = {"json:C:/IdeaProjects/EmployeeManagement/target/cucumber-json/1.json"},
        monochrome = true,
        tags = {"@all"},
        glue = {"stepdefs"})
public class Manageemployee01IT {
}
