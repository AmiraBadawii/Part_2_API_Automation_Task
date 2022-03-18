import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MyStepdefs {
    static String baseURI     = "http://localhost:3030"  ;
    static String endPoint    = "/categories"            ;
    RequestSpecification _REQUEST_SPEC;
    Response _RESP ,_RESPb;

    //hitting API with baseURI c
    @Given("Best Buy API is up and running")
    public void bestBuyAPIIsUpAndRunning() {
        _REQUEST_SPEC = given().baseUri(baseURI);
    }
   //----------------------------------------------//

    //get response with valid category name TV & Home Theater #Scenario_1
    @When("I hit url with query parameter as {string}")
    public void iHitUrlWithQueryParameterAs(String categoryName) {

        _RESP = _REQUEST_SPEC.and().queryParam("name",categoryName).when().get(endPoint);
    }

    //get response with valid category name TV & Home Theater V & Internet Service Providers  #Scenario_2
    @When("I hit url with query another parameter as {string}")
    public void iHitUrlWithQueryAnotherParameterAs(String categoryName) {
        _RESP = _REQUEST_SPEC.and().queryParam("name",categoryName).when().get(endPoint);
    }
    //get response with Invalid category name TV & Int   #Scenario_3
    @When("I hit url with invalid query another parameter as {string}")
    public void iHitUrlWithInvalidQueryAnotherParameterAs(String categoryName) {
        _RESP = _REQUEST_SPEC.and().queryParam("name",categoryName).when().get(endPoint);
    }

    //Post API create new category with unique id   #scenario#4
    @When("I hit url with unique query parameter id equal to {string} and name {string}")
    public void iHitUrlWithUniqueQueryParameterIdEqualToAndName(String id, String name) {
        _RESP = _REQUEST_SPEC.body("{\n" +
                "    \"id\": \""+id+"\", \n" +
                "    \"name\": \""+name+"\"\n" +
                "}").
                and().header("Content-Type", ContentType.JSON).
                when().post(endPoint);
    }

    //Post API create new category with duplicate id   #scenario#5
    @When("I hit url with duplicate query parameter id equal to {string} and name {string}")
    public void iHitUrlWithDuplicateQueryParameterIdEqualToAndName(String id, String name) {
        _RESP = _REQUEST_SPEC.body("{\n" +
                "    \"id\": \""+id+"\", \n" +
                "    \"name\": \""+name+"\"\n" +
                "}").
                and().header("Content-Type", ContentType.JSON).
                when().post(endPoint);
    }

    //return status code 200 for Get API
    @Then("API returns the response with status code as {int}")
    public void apiReturnsTheResponseWithStatusCodeAs(int statuscode) {
        _RESP.then().log().all()
                .assertThat().contentType(ContentType.JSON)
                .statusCode(statuscode);
    }

    //return status code 201 for Post API
    @Then("API returns the post response with status code as {int}")
    public void apiReturnsThePostResponseWithStatusCodeAs(int statuscode2) {
        _RESP.then().log().all()
                .assertThat().contentType(ContentType.JSON)
                .statusCode(statuscode2);
    }

    //return status code 400 for Post API in case of failure response
    @Then("API returns the post error response with status code as {int}")
    public void apiReturnsThePostErrorResponseWithStatusCodeAs(int statuscode3) {
        _RESP.then().log().all()
                .assertThat().contentType(ContentType.JSON)
                .statusCode(statuscode3);
    }

   // assert to  total number in case Sucess response from Get API #scenario_1
    @And("ApI returns the response boday contains total number of category equal to {int}")
    public void apiReturnsTheResponseBodayContainsTotalNumberOfCategoryEqualTo(int totalnumber) {
        _RESP.then().log().all()
                .assertThat().contentType(ContentType.JSON).body("total",equalTo(totalnumber));
    }
    // assert to  total number in case Sucess response from Get API #scenario_2
    @And("ApI returns the second response body contains total number of category equal to {int}")
    public void apiReturnsTheSecondResponseBodyContainsTotalNumberOfCategoryEqualTo(int totalnumber) {
        _RESP.then().log().all()
                .assertThat().contentType(ContentType.JSON).body("total",equalTo(totalnumber));
    }

    // assert to  total number in case failure response from Get API #scenario_3
    @And("ApI returns the third response body contains total number of category equal to {int}")
    public void apiReturnsTheThirdResponseBodyContainsTotalNumberOfCategoryEqualTo(int totalnumber) {
        _RESP.then().log().all()
                .assertThat().contentType(ContentType.JSON).body("total",equalTo(totalnumber));
    }


}
