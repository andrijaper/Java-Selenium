@NavbarFeature
Feature: Academy Application navbar functionality tests

  Scenario: Should be redirected to register page on register button click
    Given I should be on the page with this url: "https://automaticityacademy.ngrok.app/"
    When I click on the registration button in navigation bar
    Then I should be on the page with this url: "https://automaticityacademy.ngrok.app/register"

  Scenario: Should be redirected to login page on login button click
    Given I should be on the page with this url: "https://automaticityacademy.ngrok.app/"
    When I click on the login button in navigation bar
    Then I should be on the page with this url: "https://automaticityacademy.ngrok.app/login"

  Scenario: Should be redirected to dashboard page on dashboard link click
    Given Im logged into the Automaticity Academy Application
    When I click on the dashboard button in navigation bar
    Then I should be on the page with this url: "https://automaticityacademy.ngrok.app/dashboard"

  Scenario: Should be redirected to home page on logout link click
    Given Im logged into the Automaticity Academy Application
    When I click on navbar dropdown menu
    And I click on log out
    Then I should be on the page with this url: "https://automaticityacademy.ngrok.app/"
