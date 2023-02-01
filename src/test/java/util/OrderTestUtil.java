package util;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Order;

import static io.restassured.RestAssured.given;

public class OrderTestUtil {

    private static final String CREATE_API = "/api/v1/orders";
    private static final String CANCEL_API = "/api/v1/orders/cancel";
    private static final String ORDER_LIST_API = "/api/v1/orders";

    @Step("Creating order. Send POST request to " + CREATE_API)
    public static Response createTestOrder(Order order) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(order)
                .when()
                .post(CREATE_API);
    }

    @Step("Canceling order. Send PUT request to " + CANCEL_API)
    public static Response cancelTestOrder(Order order) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(order)
                .when()
                .put(CANCEL_API);
    }

    @Step("Getting order. Send GET request to " + ORDER_LIST_API)
    public static Response getOrderList() {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(ORDER_LIST_API);
    }

}
