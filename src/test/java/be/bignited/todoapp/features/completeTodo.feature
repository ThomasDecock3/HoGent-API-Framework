Feature: complete todo

  Background: the user wants to set todo as complete
    Given the user navigate to home page
    When the user wants to set todo as complete

  Scenario: make todo complete
    Given todo is not complete
    When the user makes todo complete
    Then todo will set as complete

  Scenario: make a complete todo complete
    Given todo is complete
    When the user makes todo complete
    Then the user get an error message telling that todo is complete

  Scenario: make a not existent todo complete
    Given todo not exist
    When the user makes todo complete
    Then the user get an error message telling that todo not found