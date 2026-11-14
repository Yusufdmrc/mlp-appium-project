Feature: Milli Piyango Ticket Detail Test Cases

  @TicketInquiry @LoginRequired
  Scenario Outline: Ticket inquiry by serial number
    Given User goes to the Lotteries page
    When  User queries ticket by "<serialNumber>"
    Then  The user checks if the ticket "<statusText>" has been successfully viewed
    Examples:
      | serialNumber                   | statusText      |
      | 260041213986PC7000010002652    | Kazandın!       |
      | 26002F213986BC7000010008650    | Kazanamadın!    |
      | 26002F2232986BC7000010008650   | Geçersiz bilet! |
