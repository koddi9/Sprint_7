import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import model.Courier;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static util.CourierTestUtil.*;

public class CourierLoginTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    @DisplayName("Check log in courier is possible")
    @Description("Returns 200 code and body with courier id number")
    public void loginIsPossibleTest() {
        Courier newCourier = new Courier("maboi", "1234", "nikita");
        createTestCourier(newCourier);

        Response response = loginTestCourier(newCourier);

        response.then().statusCode(200).and().assertThat().body("id", is(greaterThan(-1)));

        deleteTestCourier(newCourier);
    }

    @Test
    @DisplayName("Check log in courier with wrong credentials is impossible")
    @Description("Returns 404 code and body with description error message")
    public void wrongCredentialsTest() {
        Courier newCourier = new Courier("maboi", "1234", "nikita");
        createTestCourier(newCourier);

        newCourier.setLogin("not_existed_login");
        newCourier.setPassword("any_other_password");
        Response response = loginTestCourier(newCourier);

        response.then().statusCode(404).and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));

        deleteTestCourier(newCourier);
    }

    @Test
    @DisplayName("Check log in courier without required fields is impossible")
    @Description("Returns 400 code and body with description error message")
    public void missingFieldTest() {
        Courier newCourier = new Courier("maboi", "1234", "nikita");
        createTestCourier(newCourier);

        newCourier.setLogin(null);
        Response response = loginTestCourier(newCourier);

        response.then().statusCode(400).and()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));

        deleteTestCourier(newCourier);
    }
}
