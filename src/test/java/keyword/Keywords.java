package keyword;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class Keywords {
    public static Response response;
    private static ValidatableResponse json;
private  static RequestSpecification request;


    public static Response restFullGet(String url){

        request = given()
                .contentType(ContentType.JSON);
        response = request
                .when()
                .get(url);
        System.out.println(response.prettyPeek());
        return response;

    }

    public static Response restFullPost(String requestBody, String url){

        request = given()
                .body(requestBody)
                .contentType(ContentType.JSON);
        response = request
                .when()
                .post(url);
        System.out.println(response.prettyPeek());
        return response;

    }
    public static Response restFullPut(String requestBody, String url){

        request = given()
                .body(requestBody)
                .contentType(ContentType.JSON);
        response = request
                .when()
                .put(url);
        System.out.println(response.prettyPeek());
        return response;

    }
    public static Response restFullDelete(String url){

        request = given()
                .contentType(ContentType.JSON);
        response = request
                .when()
                .delete(url);
        System.out.println(response.prettyPeek());
        return response;

    }

    public static void verifyStatusCode(int expectedCode){

        response.then().statusCode(expectedCode);
    }

    public static void verifyResponseStringValue(String jsonPath, String expectedValue){

       String actualvalue= response.getBody().jsonPath().getString(jsonPath);
        Assert.assertEquals(expectedValue,actualvalue);
    }
    public static void verifyResponseIntegerValue(String jsonPath, int expectedValue){

        int actualvalue= response.getBody().jsonPath().getInt(jsonPath);
        Assert.assertEquals(expectedValue,actualvalue);
    }

    public static String getTheEmpID(String jsonPath){

        return response.getBody().jsonPath().getString(jsonPath);

    }

    public static String  generateRandomString() {
       return RandomStringUtils.randomAlphabetic(6);

    }

}
