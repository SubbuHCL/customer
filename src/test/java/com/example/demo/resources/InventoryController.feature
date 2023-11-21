Feature: Inventory Management

  Scenario: Get all inventory items
    Given the inventory has items
    When the client calls /inventory
    Then the client receives status code of 200
    And the response contains all inventory items

  Scenario: Get all inventory items when inventory is empty
    Given the inventory is empty
    When the client calls /inventory
    Then the client receives status code of 200
    And the response is empty

  Scenario: Get an inventory item by id
    Given the inventory has an item with id 1
    When the client calls /inventory/1
    Then the client receives status code of 200
    And the response contains the inventory item with id 1

  Scenario: Try to get an inventory item with a non-existent id
    Given the inventory does not have an item with id 999
    When the client calls /inventory/999
    Then the client receives status code of 404

  Scenario: Add a new inventory item
    Given the client has a new inventory item to add
    When the client posts to /inventory with the new item
    Then the client receives status code of 201
    And the response contains the added inventory item

  Scenario: Try to add a new inventory item without necessary details
    Given the client has a new inventory item to add without necessary details
    When the client posts to /inventory with the new item
    Then the client receives status code of 400

  Scenario: Update an existing inventory item
    Given the inventory has an item with id 1
    When the client puts to /inventory/1 with updated details
    Then the client receives status code of 200
    And the response contains the updated inventory item

  Scenario: Try to update an inventory item with a non-existent id
    Given the inventory does not have an item with id 999
    When the client puts to /inventory/999 with updated details
    Then the client receives status code of 404

  Scenario: Delete an existing inventory item
    Given the inventory has an item with id 1
    When the client deletes /inventory/1
    Then the client receives status code of 200
    And the response confirms deletion

  Scenario: Try to delete an inventory item with a non-existent id
    Given the inventory does not have an item with id 999
    When the client deletes /inventory/999
    Then the client receives status code of 404