Feature: Finance Handler functionalities

  Scenario: Get all available stocks as a list

    Given the valid stocks collection available for query
    When calling the get stocks handler
    Then list of stocks are returned as a list