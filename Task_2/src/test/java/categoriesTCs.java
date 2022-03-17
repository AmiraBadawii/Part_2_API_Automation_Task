import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class categoriesTCs {

    static String baseURI     = "http://localhost:3030"  ;
    static String endPoint    = "/categories"            ;
    static int    statusCode  =    200                   ;  // used to assert status code of the returned response
    static int    statusCode_2  =  201                   ;  // used to assert status code of the created category response


// Test Get Categories Request when Name contains TV
    @DataProvider
    public  static Object[] getData()
    {
        return new Object[][] {
                {"TV & Home Theater",2},
                {"TV & Internet Service Providers",1}
        };
    }

    @Test(dataProvider ="getData",priority=1)
    public void testGetCategoriesRequest(String name , int totalNumber) {

            given().log().all().
                baseUri(baseURI).and().queryParam("name",name)
                .when().get(endPoint).then().log().all()
                .assertThat().contentType(ContentType.JSON)
                .statusCode(statusCode)
                .body("total",equalTo(totalNumber));



    }

    //Test Create Category Request
    @DataProvider
    public  static Object[] postData()
    {
        return new Object[][] {
                {"pcmcat12349","New Category"}
        };
    }
    @Test(dataProvider ="postData",priority=2)
    public void testcreateCategoriesRequest(String id,String name)
    {
        given().log().all()
                .baseUri(baseURI)
                .body("{\n" +
                "    \"id\": \""+id+"\", \n" +
                "    \"name\": \""+name+"\"\n" +
                "}").
                and().header("Content-Type", ContentType.JSON).
                when().post(endPoint).then().log().all()
                .assertThat()
                .statusCode(statusCode_2)
                .body("id",equalTo(id))
                .body("name",equalTo(name));
    }



}
