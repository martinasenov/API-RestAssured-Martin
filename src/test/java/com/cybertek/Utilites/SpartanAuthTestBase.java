package com.cybertek.Utilites;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanAuthTestBase {


    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = ConfigurationReader.getProperty("SpartanAuthTestBase");

       /* DBUtils.createConnection(ConfigurationReader.getProperty("SpartanDataBase"),
                ConfigurationReader.getProperty("SpartanDataBaseUsername"),
                ConfigurationReader.getProperty("SpartanDataBasePassword"));
    }*/
    }

}
