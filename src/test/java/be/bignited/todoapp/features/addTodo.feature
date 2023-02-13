@ignore
Feature: User add todo


  Background: the user wants to add todo
    Given the user navigate to home page
    When the user wants to add todo

  Scenario Outline: the user add todo with a description
    Given the user gives <description> as description
    When the user add todo
    Then todo will be added
    Examples:
      | description         |
      | "make my home work" |
      | "clean my desk"     |

  Scenario: the user add todo without description
    Given the user gives no description
    When the user add todo
    Then todo will not be added
    And the user get the error message "description is required"

  Scenario: de user add empty todo
    Given the user gives no todo
    When the user add todo
    Then todo will not be added
    And the user get the error message "Required requestbody is missing"

