package com.gorest.crudtest;

import com.gorest.model.PostsPojo;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static int userId;

    @Test
    public void test001() { // Test to perform Post request
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName("Dev Patel");
        postsPojo.setEmail("devpatel@gmail.com");
        postsPojo.setGender("Male");
        postsPojo.setStatus("Active");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(postsPojo)
                .post();
        response.then().log().all().statusCode(201);
    }

    @Test
    public void test002() { // Test to perform Put request
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Dev K Patel");
        userPojo.setEmail("devpatel123@gmail.com");
        userPojo.setStatus("Inactive");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .put();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test003() { // Test to perform Delete request
        given()
                .pathParam("id", userId)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(204);

        given()
                .pathParam("id", userId)
                .when()
                .get("/{id}")
                .then().statusCode(404);

    }
}
