Feature: get single todo

  Background: the user wants to get todo
    Given the user navigate to home page
    When the user wants to get todo

  Scenario: user wants to get existent todo
    Given todo number 1 exist
    When the user gets todo number 1
    Then the user get the correct todo

  Scenario: todo doesn't exist
    Given todo number 500 doesn't exist
    When the user gets todo number 500
    Then the user get an error message telling that todo number 500 not found