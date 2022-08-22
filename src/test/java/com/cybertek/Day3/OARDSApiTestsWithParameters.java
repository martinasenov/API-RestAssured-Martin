package com.cybertek.Day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OARDSApiTestsWithParameters {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.160.162.92:1000/ords/hr";
    }

    @DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .log().all()
                .when()
                .get("/countries");

        //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json", response.header("Content-Type"));

        assertTrue(response.body().asString().contains("United States of America"));


        response.prettyPrint();

    }


    @DisplayName("Send a GET request to /employees and get only IT_PROG ")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .log().all()
                .when()
                .get("/employees");

        //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json", response.header("Content-Type"));

        assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();

    }











}
