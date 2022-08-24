package com.cybertek.Day5;

import com.cybertek.Utilites.CyberTekTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class CBTrainingWithHamcrestMatchers extends CyberTekTestBase {


    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 21887)
       .when()
                .get("/teacher/{id}")
       .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length","231")
                .and()
                .header("Date",notNullValue())
                .body("teachers[0].firstName",is("Steve"))
                .body("teachers[0].lastName",is("Peter"))
                .body("teachers[0].gender",is(equalToIgnoringCase("male")));

    }



    @DisplayName("GET request to teacher/all anc chaining")
    @Test
    public void teachersTest(){


        given()
                .accept(ContentType.JSON)

        .when()
                .get("/teacher/all")
        .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Adrian","Russell","Sharan","Steve"));

    }


}
