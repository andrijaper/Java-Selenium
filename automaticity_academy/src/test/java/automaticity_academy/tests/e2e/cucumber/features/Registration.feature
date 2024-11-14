@RegistrationFeature
Feature: Academy Application test

  Scenario: Register with missing username field
    When I enter random "email" in the field
    And I enter random "password" in the field
    And I click on the register button
    Then I should see invalid "username" message "The username field is required."

  Scenario:  Register with missing email field
    When I enter random "username" in the field
    And I enter random "password" in the field
    And I click on the register button
    Then I should see invalid "email" message "The email field is required."

  Scenario:  Register with missing password field
    When I enter random "username" in the field
    And I enter random "email" in the field
    And I click on the register button
    Then I should see invalid "password" message "The password field is required."

  Scenario Outline: Shouldn't be able to register with invalid email format
    When I enter random "username" in the field
    And I enter the invalid "<email>" in the field
    And I enter random "password" in the field
    And I click on the register button
    Then I should see invalid "email" message "The email field format is invalid."
    Examples:
      | email           |
      | @test.com       |
      | johndoetest.com |
      | johndoe@test    |
      | johndoe@.com    |

  Scenario: Shouldn't be able to register with five characters as password
    When I enter random "username" in the field
    And I enter random "email" in the field
    And I enter five characters in password field
    And I click on the register button
    Then I should see invalid "password" message "The password field must be at least 6 characters."

  Scenario: Shouldn't be able to register with six blank spaces as password
    When I enter random "username" in the field
    And I enter random "email" in the field
    And I enter six blank spaces in password field
    And I click on the register button
    Then I should see invalid "password" message "The password field is required."

  Scenario: Shouldn't be able to register with more then 255 characters as username
    When I enter the username with 256 characters in the field
    And I enter random "email" in the field
    And I enter random "password" in the field
    And I click on the register button
    Then I should see invalid "username" message "The username field must not be greater than 255 characters."

  Scenario Outline: Shouldn't be able to register already existing user
    When I check if John Doe user is registered
    And  I enter the John Doe "username" in the field
    And I enter the John Doe "email" in the field
    And I enter the John Doe "password" in the field
    And I click on the register button
    Then I should see invalid "username" message "<username_message>"
    Then I should see invalid "email" message "<email_message>"
    Examples:
      | username_message                     | email_message                     |
      | The username has already been taken. | The email has already been taken. |

  Scenario: Successfull Registration
    When I enter random "username" in the field
    And I enter random "email" in the field
    And I enter random "password" in the field
    And I click on the register button
    Then I should see success registration message
    Then I should be on Dashboard page

  Scenario Outline: Registration with blank space at the beginning and end of valid username
    When I enter the "username" with blank space at the "<position>" in the field
    And I enter random "email" in the field
    And I enter random "password" in the field
    And I click on the register button
    Then I should see success registration message
    Then I should be on Dashboard page
    Examples:
      | position  |
      | beginning |
      | end       |

  Scenario Outline: Registration with blank space at the beginning and end of valid password
    When I enter random "username" in the field
    And I enter random "email" in the field
    And I enter the "password" with blank space at the "<position>" in the field
    And I click on the register button
    Then I should see success registration message
    Then I should be on Dashboard page
    Examples:
      | position  |
      | beginning |
      | end       |

  Scenario Outline: Registration with blank space at the beginning and end of valid email
    When I enter random "username" in the field
    And I enter the "email" with blank space at the "<position>" in the field
    And I enter random "password" in the field
    And I click on the register button
    Then I should see success registration message
    Then I should be on Dashboard page
    Examples:
      | position  |
      | beginning |
      | end       |


