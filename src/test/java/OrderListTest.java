import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static util.OrderTestUtil.getOrderList;

public class OrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    @DisplayName("Check getting order list")
    @Description("Returns 200 code and body with orders array")
    public void orderListReturnsOrdersTest() {
        Response response = getOrderList();

        response.then().statusCode(200).and().assertThat().body("orders", is(not(empty())));
    }
}
