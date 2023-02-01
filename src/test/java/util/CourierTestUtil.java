package util;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Courier;

import static io.restassured.RestAssured.given;

public class CourierTestUtil {

    private static final String CREATE_API = "/api/v1/courier";
    private static final String LOGIN_API = "/api/v1/courier/login";
    private static final String DELETE_API = "/api/v1/courier/{id}";


    @Step("Creating courier. Send POST request to " + CREATE_API)
    public static Response createTestCourier(Courier courier) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(courier)
                .when()
                .post(CREATE_API);
    }

    @Step("Deleting courier. Send DELETE request to " + DELETE_API)
    public static Response deleteTestCourier(Courier courier) {
        String id = String.valueOf(
                loginTestCourier(courier).body().as(Courier.class).getId()
        );
        return given()
                .contentType(ContentType.JSON)
                .and()
                .when()
                .delete(DELETE_API, id);
    }

    @Step("Log in courier. Send POST request to " + LOGIN_API)
    public static Response loginTestCourier(Courier courier) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(courier)
                .when()
                .post(LOGIN_API);
    }
}
