package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataProviders.ExcelReader;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import keyword.Keywords;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.junit.Assert;

import java.io.FileInputStream;
import java.security.Key;
import java.util.HashMap;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ManageEmployeeSteps {

    public static Properties prop = new Properties();
   public static ExcelReader xl = new ExcelReader();
public static String testcaseName;
public static String empID;
public Response response;
public String empname;
public Scenario scenarioName = null;


@Before
    public void setup(Scenario scenario1) throws Exception{
        scenarioName = scenario1;

    }


    @Given("The Customer with Some Conditions as {string} from the Module {string}")
    public void the_Customer_with_Some_Conditions_as_from_the_Module(String tcname, String sheetName) throws Exception{
        testcaseName=tcname;
        String xlpath = "C:\\IdeaProjects\\EmployeeManagement\\src\\test\\TestData\\EmpManagement.xls";
         xl.ReadExcel(xlpath,sheetName);


         RestAssured.registerParser("text/html", Parser.JSON);
         prop.load(new FileInputStream("C:\\IdeaProjects\\EmployeeManagement\\src\\test\\java\\utils\\config.properties"));



    }

    @When("User create new Employee with mandatory fields")
    public void user_create_new_Employee_based_on_provided_test_data_from() {
        String url = prop.getProperty("url")+"/create";
        empname = ExcelReader.excelSheetHashMap.get(testcaseName).get("Name");
        String salary = ExcelReader.excelSheetHashMap.get(testcaseName).get("Salary");
        String age = ExcelReader.excelSheetHashMap.get(testcaseName).get("Age");
        String image = ExcelReader.excelSheetHashMap.get(testcaseName).get("Image");
        String requestBody= prop.getProperty("requestbodyWithMandetory")
                .replace("nameval",empname)
                .replace("salaryval",salary)
                .replace("ageval",age);
        scenarioName.write(requestBody);
       response =  Keywords.restFullPost(requestBody,url);
        scenarioName.write(response.prettyPrint());
    }
    @When("User create new Employee with optional fields")
    public void user_create_new_Employee_based_on_provided_test_data_from_excel() {
        String url = prop.getProperty("url")+"/create";
        empname = Keywords.generateRandomString();
        String salary = ExcelReader.excelSheetHashMap.get(testcaseName).get("Salary");
        String age = ExcelReader.excelSheetHashMap.get(testcaseName).get("Age");
        String image = ExcelReader.excelSheetHashMap.get(testcaseName).get("Image");
        String requestBody= prop.getProperty("requestbodyWithOptionalfields")
                .replace("nameval",empname)
                .replace("salaryval",salary)
                .replace("ageval",age)
                .replace("prifieimg",image);
        scenarioName.write(requestBody);
        response=Keywords.restFullPost(requestBody,url);
        scenarioName.write(response.prettyPrint());
    }
    @Then("Get the Created emp ID")
    public void Get_theCreatedempID() {
        String path = ExcelReader.excelSheetHashMap.get(testcaseName).get("JsonPath");
        empID= Keywords.getTheEmpID(path);

    }
    @Then("Verify status code")
    public void verify_status_code() {
        String expectedCode =     ExcelReader.excelSheetHashMap.get(testcaseName).get("StatusCode");
        Keywords.verifyStatusCode(Integer.parseInt(expectedCode));
    }

    @When("user retrieves the employee details based on Employee id")
    public void user_retrieves_the_employee_details_based_on_Employee_id() {
        String url = prop.getProperty("url")+"/employee/"+empID;
        System.out.println(url);
        response= Keywords.restFullGet(url);
        scenarioName.write(response.prettyPrint());
    }

     @Then("verify response contains Name, Salary and Age and those values matches with employee record which was created earlier and profileimage not present")
    public void verify_response_contains_Name_Salary_and_Age_and_those_values_matches_with_employee_record_which_was_created_earlier() {

        String expectedname = ExcelReader.excelSheetHashMap.get(testcaseName).get("Name");
        String expectedsalary = ExcelReader.excelSheetHashMap.get(testcaseName).get("Salary");
        String expectedage = ExcelReader.excelSheetHashMap.get(testcaseName).get("Age");

        String actualName = response.getBody().jsonPath().getString("employee_name");
        String actualSalary = response.getBody().jsonPath().getString("employee_salary");
        String actualAge = response.getBody().jsonPath().getString("employee_age");
         String actualimage = response.getBody().jsonPath().getString("profile_image");
        Assert.assertEquals(actualName,expectedname);
        Assert.assertEquals(actualSalary,expectedsalary);
        Assert.assertEquals(actualAge,expectedage);
        Assert.assertEquals(actualimage,"");

    }


    @Then("verify response contains Name, Salary and Age and those values matches with employee record which was created earlier and Profile image is present")
    public void verify_response_contains_Name_Salary_and_Age_and_those_values_matches_with_employee_record_which_was_created_andProfileimageispresent() {


        String expectedsalary = ExcelReader.excelSheetHashMap.get(testcaseName).get("Salary");
        String expectedage = ExcelReader.excelSheetHashMap.get(testcaseName).get("Age");
        String expecteimg = ExcelReader.excelSheetHashMap.get(testcaseName).get("Image");

        String actualName = response.getBody().jsonPath().getString("employee_name");
        String actualSalary = response.getBody().jsonPath().getString("employee_salary");
        String actualAge = response.getBody().jsonPath().getString("employee_age");
        String actualImage = response.getBody().jsonPath().getString("Profile_image");

        Assert.assertEquals(actualName,empname);
        Assert.assertEquals(actualSalary,expectedsalary);
        Assert.assertEquals(actualAge,expectedage);
        Assert.assertEquals(actualAge,expectedage);

    }


    @Then("The Response contains Name, Salary and Age and those values matches with provided test data")
    public void the_Response_contains_Name_Salary_and_Age_and_those_values_matches_with_provided_test_data() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("Update the employee salary")
    public void update_the_employee_salary() {
        String url = prop.getProperty("url")+"/update/"+empID;
        String salary = ExcelReader.excelSheetHashMap.get(testcaseName).get("UpdatedSalary");
        String age = ExcelReader.excelSheetHashMap.get(testcaseName).get("Age");
        String image = ExcelReader.excelSheetHashMap.get(testcaseName).get("Image");
        String requestBody= prop.getProperty("requestbodyWithOptionalfields")
                .replace("nameval",empname)
                .replace("salaryval",salary)
                .replace("ageval",age)
                .replace("prifieimg",image);
        scenarioName.write(requestBody);
        response= Keywords.restFullPut(requestBody,url);
        scenarioName.write(response.prettyPrint());
    }

    @When("The User delete employee")
    public void theUserdeleteemployee() {
        String url = prop.getProperty("url")+"/delete/"+empID;
        scenarioName.write(url);
        response= Keywords.restFullDelete(url);
        scenarioName.write(response.prettyPrint());
    }

    @Then("Verify employee salary is updated successfully")
    public void verify_employee_salary_is_updated_successfully() {
        String salary = ExcelReader.excelSheetHashMap.get(testcaseName).get("UpdatedSalary");
        String actualSalary = response.getBody().jsonPath().getString("employee_salary");
        Assert.assertEquals(salary,actualSalary);
    }
    @Then("Verify employee record deleted succesfully")
    public void verifyemployeerecorddeletedsuccesfully() {

        String actualtext = response.getBody().jsonPath().getString("success.text");
        Assert.assertEquals(actualtext,"successfully! deleted Records");
    }


    @Then("Verify the record not displayed")
    public void verifytherecordnotdisplayed() {
        Assert.assertEquals(response.prettyPrint(),"false");
    }

}


