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

  @RetrievalWebFormMissingData
  Scenario Outline: User verifies Password Retrieval form is not submittable when with "<tcId>" TC ID, "<birthDate>" Birth Date, "<email>" email missing data
    And User navigates to the Password Retrieval Page
    Then User verifies Password Retrieval form is not submittable when with "<tcId>" TC ID, "<birthDate>" Birth Date, "<email>" email missing data

    @missingBirthDate
    Examples:
      | tcId        | birthDate | email        |
      | correctTcID | empty     | correctEmail |

    @missingTcId
    Examples:
      | tcId  | birthDate | email        |
      | empty | 01122001  | correctEmail |

    @missingEmail
    Examples:
      | tcId        | birthDate | email     |
      | correctTcID | 01122001  | empty     |

  @PasswordUpdateSuccess
  Scenario Outline: User verifies that can update the password to the "<password>" on Password Retrieval Screen
    And User navigates to the Password Retrieval Page
    And User fills the Password Retrieval form with "<tcId>" "<birthDate>" "<email>"
    And User enters and submits OTP for password update
    And User changes the password with "<password>" on Password Retrieval Screen and submits
    And User navigates to the Login Page from Password Update Screen
    And User logs into the app with "<password>" and "tcId" credentials
    Then User verifies the login with "<password>" is successful

    @password1
    Examples:
      | password   | tcId        | birthDate | email        |
      | Piyango.59 | correctTcId | 10122001  | correctEmail |

    @password2
    Examples:
      | password  | tcId        | birthDate | email        |
      | Piyango1. | correctTcId | 10122001  | correctEmail |

