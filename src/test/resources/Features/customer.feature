# Generate Feature Testcases for CustomerController
Feature: Customer Controller API

  Scenario: Get customer by ID
    Given a customer with ID 1 exists
    When I request the customer with ID 1
    Then the response status code should be 200
    And the response body should contain the customer details

  Scenario: Add a new customer
    Given a new customer with name "John Doe"
    When I add the customer
    Then the response status code should be 200
    And the response body should contain the added customer details

  Scenario: Update an existing customer
    Given an existing customer with ID 1
    When I update the customer with ID 1
    Then the response status code should be 200
    And the response body should contain the updated customer details

  Scenario: Delete a customer
    Given an existing customer with ID 1
    When I delete the customer with ID 1
    Then the response status code should be 200