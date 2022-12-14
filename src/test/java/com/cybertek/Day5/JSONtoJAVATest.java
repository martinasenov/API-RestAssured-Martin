package com.cybertek.Day5;

import com.cybertek.Utilites.ConfigurationReader;
import com.cybertek.Utilites.DBUtils;
import com.cybertek.Utilites.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JSONtoJAVATest extends SpartanTestBase{


    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to the map

        Map<String,Object>jsonMap=response.as(Map.class);

        System.out.println(jsonMap.toString());

        //after we got the map, we can use hamcrest or Junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName,is("Meta"));



    }





}
