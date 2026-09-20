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
      | username    | password        |
      | correctTCID | correctPassword |


    @FailedLogin @ios @android
    Scenario Outline: Correct "<username>" Username &  Wrong "<password>" Password for login
      And write "<username>" for username field
      And write "<password>" for password field
      And Click login button
      Then Check "<error>" message about credentials not valid

      Examples:
        | username    | password        | error                                        |
        | correctTCID | 1234Abc         | Girdiğin şifre ve hesap bilgileri uyuşmuyor. |
        | abcde       | correctPassword | Kimlik bilgileri geçerli değil               |


    @AllEmptyFields @ios @android
    Scenario Outline: All fields are empty for login
      And write "<username>" for username field
      And write "<password>" for password field
      And Click login button
      Then Check unsuccessful login

        Examples:
          | username | password |
          | empty    | empty    |
          | abcdef   | empty    |
          | empty    | abc123.  |

