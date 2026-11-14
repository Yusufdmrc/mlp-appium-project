Feature: Lottery Games Test Cases

  @e2e @LoginRequired
  Scenario Outline: "<Game>" - User is able to buy ticket "<Type>" SuperStar on tab : "<Tab>"
    When User navigates to the "<Game>" game page from homepage
    And User navigates to "<Tab>" tab on game
    And User enters <drawNumber> row by random button
    Then User verifies the ticket was bought successfully
    And User verifies the receipt elements for <drawNumber> draw <columnNumber> column <standartNumber> standart number <superstarNumber> superstar number
    And User verifies the details of the ticket for "<Game>"

    @sansTopuSimple
    Examples:
      | Game         | Tab    | Type | drawNumber | columnNumber | standartNumber | superstarNumber |
      | Sayisal Loto | simple | with | 1          | 1            | 6              | 1               |
