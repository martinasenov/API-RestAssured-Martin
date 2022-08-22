package com.cybertek.Day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured .*;
import static org.junit.jupiter.api.Assertions .*;

public class SpartanTestsWithParameters {




    //BeforeAll is an annotation equals to @BeforeClass int testNG, we use it with static method name

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.160.162.92:8000";
    }


/* Given accept type is json
   And ID parameter value is 5
   When user sends GET request to /api/spartans/{id}
   Then response status code should be 200
   And response content-type:applicant/json
   And "Blythe" should be in response payload
 */


    @DisplayName("Get requst to /api/spartans/{id} with ID 5")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("/api/spartans/{id}");

        //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json", response.getContentType());

        //verify Blythe in the json payload/body
        assertTrue(response.body().asString().contains("Blythe"));

    }


    /* Given accept type is json
       And Id parameter value is 500
       When user sends GET request to /api/spartans{id}
       Then response status code should be 404
       And response content-type: application/json
       And "Not Found" message should be in response payload
     */


    @DisplayName("GET request /api/spartans/{id} Negative Test")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");

        //verify status code
        assertEquals(404,response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //verify Not Found in the json payload/body
        assertTrue(response.body().asString().contains("Not Found"));
    }








}
