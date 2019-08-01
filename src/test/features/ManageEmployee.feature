Feature: Validation of Employee management system

@e2e @all
Scenario Outline: "<TestCase>"
As a user I want to create update and delete employee record

  Given The Customer with Some Conditions as "<TestCase>" from the Module "<XLSheetName>"
  When User create new Employee with mandatory fields
  Then Verify status code
  Then Get the Created emp ID
  When user retrieves the employee details based on Employee id
  Then Verify status code
  And verify response contains Name, Salary and Age and those values matches with employee record which was created earlier and profileimage not present
  When Update the employee salary
  Then user retrieves the employee details based on Employee id
  And Verify employee salary is updated successfully
  When The User delete employee
  Then Verify employee record deleted succesfully
    Examples:
    |TestCase                                                  |XLSheetName|
    |Verify User is able to create update and delete employee record |EmployeeDetails|

@all @create
Scenario Outline: "<TestCase>"
  As a user I want to create and return the employee details from system based on Employee id

  Given The Customer with Some Conditions as "<TestCase>" from the Module "<XLSheetName>"
  When User create new Employee with optional fields
  Then Verify status code
  Then Get the Created emp ID
  When user retrieves the employee details based on Employee id
  Then Verify status code
  And verify response contains Name, Salary and Age and those values matches with employee record which was created earlier and Profile image is present
  Examples:
    |TestCase                                                  |XLSheetName|
  |Verify employee record is successfully created and returned when record is created with optional fields|EmployeeDetails|



  @all @update
  Scenario Outline: "<TestCase>"
  As a user I want to create and return the employee details from system based on Employee id

    Given The Customer with Some Conditions as "<TestCase>" from the Module "<XLSheetName>"
    When User create new Employee with optional fields
    Then Verify status code
    Then Get the Created emp ID
    When Update the employee salary
    Then user retrieves the employee details based on Employee id
    And Verify employee salary is updated successfully
    Examples:
      |TestCase                                                  |XLSheetName|
      |Verify employee salary was successfully updated and returned|EmployeeDetails|

  @all @delete
  Scenario Outline: "<TestCase>"
  As a user I want to create and return the employee details from system based on Employee id

    Given The Customer with Some Conditions as "<TestCase>" from the Module "<XLSheetName>"
    When User create new Employee with optional fields
    Then Verify status code
    Then Get the Created emp ID
    When The User delete employee
    Then Verify employee record deleted succesfully
    Then user retrieves the employee details based on Employee id
    Then Verify the record not displayed
    Examples:
      |TestCase                                                  |XLSheetName|
      |Verify employee record is successfully deleted|EmployeeDetails|


