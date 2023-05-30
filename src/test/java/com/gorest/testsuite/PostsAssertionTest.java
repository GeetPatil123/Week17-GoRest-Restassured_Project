package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 10
    @Test
    public void Test1() {
        response.body(("size()"), equalTo(10));
    }

    //2. Verify the if the title of id = 39671 is equal to "Bonus cunae undique deprecator pecto vitium recusandae denuncio."
    @Test
    public void Test2() {
        response.body("findAll{it.id == 39671}.title", hasItem("Bonus cunae undique deprecator pecto vitium recusandae denuncio."));
    }

    //3. Check the single user_id in the Array list (39653)
    @Test
    public void Test3() {
        response.body("findAll{it}.id", hasItem( 39653));
    }

    //4. Check the multiple ids in the ArrayList (39686, 39663, 39653)
    @Test
    public void Test4() {
        response.body("findAll{it}.id", hasItems(39297, 39295, 39292));
    }

    //5. Verify the body of userid = 39304 is equal "Argumentum adultus virga. Aequus vinculum abbas. Alveus spero aurum. Aptus dolorem depono. Fugiat voluptate suspendo. Ustilo alii vinco. Spargo cruciamentum mollitia. Deprimo depulso sunt. Cribro quas desino. Autem vitae atavus. Perspiciatis tot decumbo. Vigilo congregatio turba. Crepusculum vacuus solvo. Corrigo desolo tempora."
    @Test
    public void Test5() {
        response.body("findAll{it.id == 39304}.body", hasItem("Argumentum adultus virga. Aequus vinculum abbas. Alveus spero aurum. Aptus dolorem depono. Fugiat voluptate suspendo. Ustilo alii vinco. Spargo cruciamentum mollitia. Deprimo depulso sunt. Cribro quas desino. Autem vitae atavus. Perspiciatis tot decumbo. Vigilo congregatio turba. Crepusculum vacuus solvo. Corrigo desolo tempora."));
    }
}