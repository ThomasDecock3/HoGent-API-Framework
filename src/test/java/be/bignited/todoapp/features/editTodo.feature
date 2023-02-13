Feature: user edit todo

  Background: the user wants to edit todo
    Given the user navigate to home page
    When the user wants to edit todo

  Scenario: todo exist
    Given the user change todo to "clean my garage"
    When the user edit todo number "2"
    Then the todo will be changed
    And the description will be "clean my garage"

  Scenario: todo don't exist
    Given the user change todo to "make my sandwich"
    When the user edit todo number "300"
    Then the user get an error message telling that todo nr "300" not found

  Scenario: todoId is not numeric
    Given the user change todo to "play football"
    When the user edit todo number "a"
    Then the user get a bedRequest response