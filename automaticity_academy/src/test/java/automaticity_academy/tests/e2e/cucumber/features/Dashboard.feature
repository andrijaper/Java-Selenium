@DashboardFeature
Feature: Automaticity Academy

  Background:
    Given Im logged into the Automaticity Academy Application
    When I should be on the page with this url: "https://automaticityacademy.ngrok.app/dashboard"

  Scenario: Should be able to change pagination pages
    When I am on a dashboard page
    Then I click on each pagination button and check it

  Scenario: Should find expected number of products on page
    When I am on a dashboard page
    And I check number of products on this page should be 12
    And I click on page 2 in pagination bar
    And I check number of products on this page should be 12
    And I click on page 3 in pagination bar
    And I check number of products on this page should be 12
    And I click on page 4 in pagination bar
    And I check number of products on this page should be 12
    And I click on page 5 in pagination bar
    And I check number of products on this page should be 12
    And I click on page 6 in pagination bar
    And I check number of products on this page should be 12
    And I click on page 7 in pagination bar
    Then I check number of products on this page should be 12

  @HigherResolution
  Scenario: Should find all elements for each product
    When I am on a dashboard page
    And I check if each product has necessary elements
    Then I click on page 2 in pagination bar
    And I check if each product has necessary elements
    And I click on page 3 in pagination bar
    And I check if each product has necessary elements
    And I click on page 4 in pagination bar
    And I check if each product has necessary elements
    And I click on page 5 in pagination bar
    And I check if each product has necessary elements
    And I click on page 6 in pagination bar
    And I check if each product has necessary elements
    And I click on page 7 in pagination bar
    And I check if each product has necessary elements

  Scenario: Should discount match old price decreased by 33%
    When I check today deals are present
    Then I check dicount prices are calculated and shown correctly

  Scenario: Success message should be displayed after adding product into cart
    When I am on a dashboard page
    And I click on cart icon for product with order number 1
    Then I can see that product is successfully added

  Scenario: Should open modal dialog and product name and description should be displayed
    When I am on a dashboard page
    And I click on the image of product with order number 1
    Then I check modal dialog is displayed with product name and product description

  Scenario: Every Score rating should match star rating for each product
    When I am on a dashboard page
    Then I check is star rating match score rating for product with order number 2

  Scenario: Sliders shouldn't change when changing new page
    When I am on a dashboard page
    And I click "left slider" slider and move it by 75
    And I click "right slider" slider and move it by 50
    And I check prices in the filter bar after moving sliders
    And I click on page 2 in pagination bar
    Then I see prices haven't changed

