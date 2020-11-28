Feature: Validating palce API

Scenario Outline: verify if place is added Successfully
Given user is with the request payload "<name>" "<language>" " <address>"
When user calls "AddPlaceAIP" with "POST" http method
Then "AddPlaceAIP" call should pass with statuscode 200
And "status" in response must be "OK"
And "scope" in response must be "APP"
When user calls "GetPlaceAPI" with "GET" http method
Then "GetPlaceAPI" call should pass with statuscode 200
And "name" in response must be "<name>"

Examples:
|name|language|address|
|AA|french|Edgetown street|
#|BB|spanish|barcilona|

@DeletePlace
Scenario: verify delete place api
Given user is with DeletePlaceAPI
When user calls "DeletePlaceAPI" with "POST" http method
Then "DeletePlaceAPI" call should pass with statuscode 200
And "delete-status" in response must be "OK"
