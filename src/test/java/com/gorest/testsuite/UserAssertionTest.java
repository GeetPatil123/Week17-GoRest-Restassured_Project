package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void Test1() {
        response.body("id.size()", equalTo(20));
    }

    //2. Verify the if the name of id =  2272678 is equal to ”The Hon. Prema Guha”
    @Test
    public void Test2() {
        response.body("[2].name", equalTo("The Hon. Prema Guha"));
    }

    //3. Check the single ‘Name’ in the Array list (Bhavani Abbott)
    @Test
    public void Test3() {
        response.body("[5].name", equalTo("Bhavani Abbott"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Karunanidhi Guneta, Dhyaneshwar Asan IV
    //Ameyatma Bhat)
    @Test
    public void Test4() {
        response
                .body("[7].name", equalTo("Karunanidhi Guneta"))
                .body("[6].name", equalTo("Dhyaneshwar Asan IV"))
                .body("[12].name", equalTo("Ameyatma Bhat"));
    }

    //5. Verify the emai of userid = 5471 is equal “the_prema_hon_guha@rodriguez-leannon.example”
    @Test
    public void Test5(){
        response.body("[2].email", equalTo("the_prema_hon_guha@rodriguez-leannon.example"));
    }

    //6. Verify the status is “Active” of user name is “Esha Gupta”
    @Test
    public void Test6(){
        response.body("[10].status", equalTo("active"));
    }

    //7. Verify the Gender = female of user name is “Gobinda Singh JD”
    @Test
    public void Test7(){
        response.body("[9].gender", equalTo("female"));
    }

}
