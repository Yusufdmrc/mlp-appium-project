@MilliPiyangoWithdrawal
Feature: Milli Piyango Withdrawal Check Test Cases

  Background:
    Given User navigates to the Para Çekme page
  @SuccessfulWithdrawals @LoginRequired
  Scenario Outline:Successful Withdrawal
    When User adds a new IBAN with "<ibanNo>" and "<shortName>"
    And User withdraws "<price>"
    Then Check Successful withdrawal

    Examples:
      | ibanNo | shortName  | price  |
      | ibanNo | shortName1 | price1 |

    @removeIban @LoginRequired
    Scenario: Remove IBAN
      When User removes the IBAN
      Then Check IBAN is removed successfully
