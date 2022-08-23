package com.cybertek.Day4;

import com.cybertek.Utilites.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ORDSApiTestWithPath extends HrTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\": 2}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first country ID
        String firstCountryID = response.path("items[0].country_id");
        System.out.println("firstCountryID = " + firstCountryID);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);


        //print third country href in the links
        String thirdCountryHRef = response.path("items[2].links[0].href");
        System.out.println("thirdCountryHRef = " + thirdCountryHRef);


        //get me all country names
        List<String> countryNames=response.path("items.country_name");

        System.out.println("countryNames = " + countryNames);


        //assert that all region Ids are equal to 2.
        List<Integer> allRegionIDs=response.path("items.region_id");

        for (Integer regionsID : allRegionIDs) {

            System.out.println("regionsID = " + regionsID);
            assertEquals(2,regionsID);
        }

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

            //make sure we have only IT_PROG as a job_id
            List<String>allJobIds=response.path("items.job_id");

            for (String jobID : allJobIds) {
                System.out.println("jobID = " + jobID);
                assertEquals("IT_PROG", jobID);
            }


          //HW
          //print each name of IT_PROG

            List<String>allITprogNames=response.path("items.first_name");

            for (String allITprogName : allITprogNames) {
                System.out.println(allITprogName);
            }

        }



    }



