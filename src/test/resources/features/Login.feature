Feature: Login Check Test Cases

  Background:
    Given User at home page
    When  Click member login button

  @SuccessfulLogin @ios @android
  Scenario Outline: Correct "<username>" Username &  Correct "<password>" Password for login
    And write "<username>" for username field
    And write "<password>" for password field
    And Click login button
    Then Check Successful login

    Examples:
      | username  | password  |
      | 123456789 | Piyango1.
