Feature: Validating place API's

  @AddPlaceAPI
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<Address>"
    When user call "AddPlaceAPI" with "POST" Http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples:
      | name    | language | Address |
      | Dheeraj | English  | Gohana  |
#     | Ravi    | Hindi    | Delhi   |
#     | Rajiv   | Hindi    | Haryana |
#     | Rahul   | Hindi    | Mumbai  |

  @DeletePlaceAPI
  Scenario: Verify if Delete Place API is working properly
    Given DeletePlace Payload
    When user call "DeletePlaceAPI" with "DELETE" Http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"


