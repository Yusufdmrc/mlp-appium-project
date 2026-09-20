Feature: Draw Results Test Cases

  Background:
    Given User navigates to the Draw Results page

    @FilterDrawResults
    Scenario Outline: Verify that games are listed according to the filter
      When User filters results by "<game>"
      Then User verifies that the results belong to "<game>"

      Examples:
        | game            |
        | Super Loto      |
        | Sayisal Loto    |
        | Sans Topu       |
        | On Numara       |
        | Milli Piyango   |
        | Hızlı On Numara |