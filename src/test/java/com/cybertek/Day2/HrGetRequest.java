package com.cybertek.Day2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HrGetRequest {

    //BeforeAll is a annotation equals to @BeforeClass int testNG, we use it with static method name

    @BeforeAll
    public static void init(){
       //save baseurl inside this variable so that we dont need to type each http method.
        RestAssured.baseURI="http://54.226.176.142:1000/ords/hr";

    }

    @Test
    public void test1(){

        Response response = RestAssured.get("/regions");

        //print the status code
        System.out.println(response.statusCode());


    }

    /* Given accept type is application/json
    When user sends GET request to /regions/2
    Then response status code must be 200
    And content type equals to application/json
    And response body contains Americas
     */

@DisplayName("GET request to /regions/2")
@Test
    public void test2(){


    Response response = RestAssured.get("/regions/2");

    //print the status code
    System.out.println(response.statusCode());

    Assertions.assertEquals("application/json",response.contentType());

    response.prettyPrint();

    Assertions.assertEquals(response.body().asString().contains("Americas"),true);


}


}

