package com.cybertek.Day6;

import com.cybertek.Utilites.HrTestBase;
import com.cybertek.pojo.Region;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class ORDSPojoGetRequestTest extends HrTestBase {

//check jsonschema2pojo.org

    @Test
    public void test1(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region1=jsonPath.getObject("items[0]",Region.class);

        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRegion_id());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());


    }
}
