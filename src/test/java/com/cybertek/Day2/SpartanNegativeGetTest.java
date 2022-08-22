package com.cybertek.Day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    //BeforeAll is an annotation equals to @BeforeClass int testNG, we use it with static method name

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI="http://54.160.162.92:8000";
    }


    /*
    Given Accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    And response content type must be application/xml;charset=UFT-8
     */
      @DisplayName("GET request to /api/spartans/10")
        @Test
    public void test1(){

          Response response = given().accept(ContentType.XML).when().get("/api/spartans/10");


          assertEquals(406,response.statusCode());

          assertEquals("application/xml;charset=UTF-8",response.contentType());


    }




}
