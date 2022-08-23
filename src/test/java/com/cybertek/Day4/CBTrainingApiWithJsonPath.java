package com.cybertek.Day4;

import com.cybertek.Utilites.CyberTekTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class CBTrainingApiWithJsonPath extends CyberTekTestBase {



    @DisplayName("GET request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 23401 as path parameter
        //verify status code 200/content type /Content-Encoding = gzip
        //verify Date header exists
        //assert that
         /*
         firstname = vera
         batch = 14
         section=12
         email address=aaa@gmail.com
         companyName Cybertek
         state IL
         zipCode 60606

         using JsonPath
          */

        Response response = given().accept(ContentType.JSON)
                .when().get("/student/all");

                 assertEquals(200,response.statusCode());
                 assertEquals("application/json;charset=UTF-8", response.getContentType());


        JsonPath jsonPath=response.jsonPath();
        String name = jsonPath.getString("students.findAll{it.firstName==\"Geralyn\"}.firstName");
        System.out.println(name);
    }

}
