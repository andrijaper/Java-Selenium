Feature: Academy Application test

  Background:
    Given I am on the Academy Application Login window

  Scenario Outline: Successfull login
    When I enter the email <email> in the field
    And I enter the password <password> in the field
    And I click on the sign in button
    Then I should be on Dashboard page
    Examples:
      | email            | password      |
      | "jane@test.com"  | "Janedoe@123" |
      | "JANE@TEST.COM"  | "Janedoe@123" |
      | " jane@test.com" | "Janedoe@123" |
      | "jane@test.com " | "Janedoe@123" |

  Scenario Outline: Shouldn't login without full email address provided
    When I enter the email <email> in the field
    And I enter the password "Janedoe@123" in the field
    And I click on the sign in button
    Then I should see invalid email <message>
    Examples:
      | email          | message                                          |
      | ""             | "The email field is required."                   |
      | ".com"         | "The email field must be a valid email address." |
      | "jane@test."   | "The email field must be a valid email address." |
      | "janetest.com" | "The email field must be a valid email address." |

  Scenario Outline: Shouldn't login with invalid email address provided
    When I enter the email <email> in the field
    And I enter the password "Janedoe@123" in the field
    And I click on the sign in button
    Then I should see invalid fields <message>
    Examples:
      | email                       | message                                                |
      | "jane@test"                 | "The email address or password you entered is invalid" |
      | "unregisteredUser@test.com" | "The email address or password you entered is invalid" |


  Scenario: Shouldn't login without password provided
    When I enter the email "jane@test.com" in the field
    And I enter the password "" in the field
    And I click on the sign in button
    Then I should see invalid password "The password field is required."

  Scenario Outline:  Shouldn't login with invalid password provided
    When I enter the email "jane@test.com" in the field
    And I enter the password <password> in the field
    And I click on the sign in button
    Then I should see invalid fields <message>
    Examples:
      | password          | message                                                |
      | "invalidPassword" | "The email address or password you entered is invalid" |
      | "JANEDOE@123"     | "The email address or password you entered is invalid" |
