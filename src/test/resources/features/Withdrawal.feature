@MilliPiyangoWithdrawal
Feature: Milli Piyango Withdrawal Check Test Cases

  @SuccessfulWithdrawals @LoginRequired
  Scenario Outline: Correct Username & Password for login and Successful Withdrawal
    Given User navigates to the Para Çekme page
    When User adds a new IBAN with "<ibanNo>" and "<shortName>"
    And User withdraws "<price>"
    Then Check Successful withdrawal

    Examples:
      | ibanNo | shortName  | price  |
      | ibanNo | shortName1 | price1 |