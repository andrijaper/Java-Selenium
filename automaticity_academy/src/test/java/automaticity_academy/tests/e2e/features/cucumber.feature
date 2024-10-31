Feature: Academy Application test

  Scenario: Login should be success
    Given User navigate to the Academy Application
    And User clicks on the login button
    And User enter the email
    And User enter the password
    When User click on the login button
    Then Login should be success
