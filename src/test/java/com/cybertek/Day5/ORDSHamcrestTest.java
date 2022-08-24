package com.cybertek.Day5;

import com.cybertek.Utilites.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class ORDSHamcrestTest extends HrTestBase {

    @DisplayName("GET request to Regions endpoint and chaining")
    @Test
    public void employeesTest(){

        List<String>names= Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        //send a get request to employees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are... (find proper method to check list against list)
        //verify emails without checking order(provide emails in different order, just make sure it has same emails)

        given().accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"job_id\":\"IT_PROG\"}")
        .when()
                .get("/employees")
        .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.first_name",containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"))
                .body("items.email",containsInAnyOrder("VPATABAL","DAUSTIN","BERNST","AHUNOLD","DLORENTZ"))
                .body("items.first_name",equalToObject(names)); //equality of lists assertion

    }



    @Test
    public void employeesTest2(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();
       //extract() --> method that allows us to get response object after we use then() method

        //assert that we have only 5 firstnames
        assertThat(jsonPath.getList("items.first_name"),hasSize(5));

        //assert first names order
        assertThat(jsonPath.getList("items.first_name"),containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"));

    }

}
