import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static util.OrderTestUtil.cancelTestOrder;
import static util.OrderTestUtil.createTestOrder;

@RunWith(Parameterized.class)
public class OrderCreatingTest {

    Order testOrder;
    int expectedStatusCode;

    public OrderCreatingTest(Order testOrder, int expectedStatusCode) {
        this.testOrder = testOrder;
        this.expectedStatusCode = expectedStatusCode;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    @DisplayName("Check creating order with different colors is possible")
    @Description("Returns 201 code and body with order track number")
    public void creatingIsPossibleTest() {
        Response response = createTestOrder(testOrder);

        response.then().statusCode(expectedStatusCode).and().assertThat().body("track", is(greaterThan(-1)));

        cancelTestOrder(testOrder);
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {new Order("ivan", "ivanov", "Pushkina", "Sportivnaya", "+7(123)4567890",
                        7, "2023-01-01", "nothing"), 201},
                {new Order("ivan", "ivanov", "Pushkina", "Sportivnaya", "+7(123)4567890",
                        7, "2023-01-01", "nothing",List.of(Order.Color.BLACK.name())), 201},
                {new Order("ivan", "ivanov", "Pushkina", "Sportivnaya", "+7(123)4567890",
                        7, "2023-01-01", "nothing",List.of(Order.Color.GREY.name())), 201},
                {new Order("ivan", "ivanov", "Pushkina", "Sportivnaya", "+7(123)4567890",
                        7, "2023-01-01", "nothing",List.of(Order.Color.BLACK.name(), Order.Color.GREY.name())), 201}
        };
    }
}
