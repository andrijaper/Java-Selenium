Feature: Academy Application test

Scenario Outline: Login aggain
  Given User navigate to the Academy Application
  When User clicks on the login button
  And User enter the email "<email>" in the field
  And User enter the password "<password>" in the field
  And User click on the login button
  Then Login should be success
  Examples:
    | email         | password    |
    | jane@test.com | Janedoe@123 |
