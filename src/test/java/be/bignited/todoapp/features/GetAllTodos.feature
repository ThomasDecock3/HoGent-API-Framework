Feature: get all todo's

  Background: the user wants to get todo
    Given the user navigate to home page
    When the user wants to get all todo's

  Scenario: the user wants all todo's
    When the user gets todo's
    Then the user sees all todo's

  Scenario: the user wants only incomplete todo's
    When the user gets incomplete todo's
    Then the user sees a list of todo's
    And all todo's are incomplete
