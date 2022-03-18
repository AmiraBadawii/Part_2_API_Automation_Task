
@Run
Feature: Best Buy API Get Product Info
  This feature is to validate all the Get Requests for categories

  @get
  Scenario: Get category by name
    Given Best Buy API is up and running
    When I hit url with query parameter as "TV & Home Theater"
    Then API returns the response with status code as 200
    And  ApI returns the response boday contains total number of category equal to 2


  @get
  Scenario: Get category by name
    Given Best Buy API is up and running
    When I hit url with query another parameter as "TV & Internet Service Providers"
    Then API returns the response with status code as 200
    And  ApI returns the second response body contains total number of category equal to 1

  @get
  Scenario: Get category by name
    Given Best Buy API is up and running
    When I hit url with invalid query another parameter as "TV & Int"
    Then API returns the response with status code as 200
    And  ApI returns the third response body contains total number of category equal to 0


    @post
    Scenario: create new category with unique id
      Given Best Buy API is up and running
      When I hit url with unique query parameter id equal to "pcmcxt51339" and name "Computer"
      Then API returns the post response with status code as 201


    @post
    Scenario: create new category with unique id
      Given Best Buy API is up and running
      When I hit url with duplicate query parameter id equal to "pcmcxt51339" and name "Computer"
      Then API returns the post error response with status code as 400







