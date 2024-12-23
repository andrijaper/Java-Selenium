@RegistrationFeature
Feature: Academy Application registration tests

  Scenario: Register with missing username field
    Given I enter random "email" in the field
    And I enter random "password" in the field
    When I click on the register button
    Then I should see invalid "username" message "The username field is required."

  Scenario:  Register with missing email field
    Given I enter random "username" in the field
    And I enter random "password" in the field
    When I click on the register button
    Then I should see invalid "email" message "The email field is required."

  Scenario:  Register with missing password field
    Given I enter random "username" in the field
    And I enter random "email" in the field
    When I click on the register button
    Then I should see invalid "password" message "The password field is required."

  Scenario Outline: Shouldn't be able to register with invalid email format
    Given I enter random "username" in the field
    And I enter the invalid "<email>" in the field
    And I enter random "password" in the field
    When I click on the register button
    Then I should see invalid "email" message "The email field format is invalid."
    Examples:
      | email           |
      | @test.com       |
      | johndoetest.com |
      | johndoe@test    |
      | johndoe@.com    |

  Scenario: Shouldn't be able to register with five characters as password
    Given I enter random "username" in the field
    And I enter random "email" in the field
    And I enter five characters in password field
    When I click on the register button
    Then I should see invalid "password" message "The password field must be at least 6 characters."

  Scenario: Shouldn't be able to register with six blank spaces as password
    Given I enter random "username" in the field
    And I enter random "email" in the field
    And I enter six blank spaces in password field
    When I click on the register button
    Then I should see invalid "password" message "The password field is required."

  Scenario: Shouldn't be able to register with more then 256 characters as username
    Given I enter the username with 256 characters in the field
    And I enter random "email" in the field
    And I enter random "password" in the field
    When I click on the register button
    Then I should see invalid "username" message "The username field must not be greater than 255 characters."

  Scenario Outline: Shouldn't be able to register already existing user
    Given I check if John Doe user is registered
    And  I enter the John Doe "username" in the field
    And I enter the John Doe "email" in the field
    And I enter the John Doe "password" in the field
    When I click on the register button
    Then I should see invalid "username" message "<username_message>"
    And I should see invalid "email" message "<email_message>"
    Examples:
      | username_message                     | email_message                     |
      | The username has already been taken. | The email has already been taken. |

  Scenario: Successfull Registration
    Given I enter random "username" in the field
    And I enter random "email" in the field
    And I enter random "password" in the field
    When I click on the register button
    Then I should see success registration message
    And I should be on Dashboard page

  Scenario Outline: Registration with blank space at the beginning and end of valid username
    Given I enter the "username" with blank space at the "<position>" in the field
    And I enter random "email" in the field
    And I enter random "password" in the field
    When I click on the register button
    Then I should see success registration message
    And I should be on Dashboard page
    Examples:
      | position  |
      | beginning |
      | end       |

  Scenario Outline: Registration with blank space at the beginning and end of valid password
    Given I enter random "username" in the field
    And I enter random "email" in the field
    And I enter the "password" with blank space at the "<position>" in the field
    When I click on the register button
    Then I should see success registration message
    And I should be on Dashboard page
    Examples:
      | position  |
      | beginning |
      | end       |

  Scenario Outline: Registration with blank space at the beginning and end of valid email
    Given I enter random "username" in the field
    And I enter the "email" with blank space at the "<position>" in the field
    And I enter random "password" in the field
    When I click on the register button
    Then I should see success registration message
    And I should be on Dashboard page
    Examples:
      | position  |
      | beginning |
      | end       |


