package com.cybertek.Day4;

import com.cybertek.Utilites.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanWithJsonPath extends SpartanTestBase {

    @DisplayName("GET one spartan with JsonPath")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json", response.getContentType());

        //print name with path method
        System.out.println(response.path("name").toString());


        //assigning response to jsonpath
        JsonPath jsonPath=response.jsonPath();

        int id=jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");


        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

    }
}
