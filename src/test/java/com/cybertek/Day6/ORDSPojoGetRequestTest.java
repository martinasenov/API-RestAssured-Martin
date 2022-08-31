package com.cybertek.Day6;

import com.cybertek.Utilites.HrTestBase;
import com.cybertek.pojo.Employee;
import com.cybertek.pojo.Region;
import com.cybertek.pojo.Regions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ORDSPojoGetRequestTest extends HrTestBase {

//check jsonschema2pojo.org

    @Test
    public void test1(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).log().body().extract().jsonPath();

        Region region1=jsonPath.getObject("items[0]",Region.class);

        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());


    }


    @DisplayName("Get request to /employees and onlz get couple of values as a Pojo Class")
    @Test
    public void employeeGet(){


        Employee employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println(employee1);


    }


    //send a GET request to the regions
    //verify that region ids are 1,2,3,4
    //verify that region names Europe, Americas, Asia, Middle East and Africa
    //try to use pojo as much as possible
    //ignore non-used fields

    @DisplayName("GET request to region only some fields test")
    @Test
    public void regionPojoTest(){

        //send a get request and save everything inside the regions object
        //since we prepare pojo also for the all properties we don't need to use any path so as() method is enough
        Regions regions=get("/regions").then().statusCode(200).extract().response().as(Regions.class);


        //verify that count is 4
       assertThat(regions.getCount(),is(4));
       List<String> regionNames =new ArrayList<>();
       List<Integer> regionIds=new ArrayList<>();

       List<Region>items=regions.getItems();


       //loop through each of the region, save their ids and names to empty list that we prepare
        for (Region region : items) {
            regionIds.add(region.getRId());
            regionNames.add(region.getRegion_name());

        }

        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);

        //prepare expected results
        List<Integer>expectedRegionIds= Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames=Arrays.asList("Europe", "Americas", "Asia","Middle East and Africa");

        //compare two results
        assertThat(regionIds, is(expectedRegionIds));
        assertThat(regionNames, is(equalTo(expectedRegionNames)));


    }



}
