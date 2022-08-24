package com.cybertek.Day5;
import com.cybertek.Utilites.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;



public class HamcrestMatchersApiTest extends SpartanTestBase  {
    /*
           given accept type is json
           And path param id is 15
           When user sends a get request to spartans/{id}
           Then status code is 200
           And content type is Json
           And json data has following

           "id":15,
           "name":"Meta",
           "gender": "Female",
           "phone":1938695106

            */
    @DisplayName("One Spartan With Hamcrest and chaining")
    @Test
    public void test(){

    given().
            accept(ContentType.JSON)
            .and().pathParam("id",15)
            .when()
            .get("/api/spartans/{id}")
            .then()

            .statusCode(200)
            .and()
            .contentType("application/json")
            .and()
                               .body("id", equalTo(15),
                    "name", is("Meta"),
                                         "gender", is("Female"),
                                         "phone", is(1938695106));


    }




}
