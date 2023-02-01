import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import model.Courier;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static util.CourierTestUtil.createTestCourier;
import static util.CourierTestUtil.deleteTestCourier;

public class CourierCreatingTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    @DisplayName("Check creating courier is possible")
    @Description("Returns 201 code and body with ok:true message")
    public void creatingIsPossibleTest() {
        Courier newCourier = new Courier("maboi", "1234", "nikita");
        deleteTestCourier(newCourier);

        Response response = createTestCourier(newCourier);

        response.then().statusCode(201).and().assertThat().body("ok", equalTo(true));

        deleteTestCourier(newCourier);
    }

    @Test
    @DisplayName("Check creating already existed courier is impossible")
    @Description("Returns 409 code and body with description error message")
    public void sameCredentialsTest() {
        Courier newCourier = new Courier("maboi", "1234", "nikita");
        createTestCourier(newCourier);

        Response response = createTestCourier(newCourier);

        response.then().statusCode(409).and()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        deleteTestCourier(newCourier);
    }

    @Test
    @DisplayName("Check creating courier without required fields is impossible")
    @Description("Returns 400 code and body with description error message")
    public void missingFieldTest() {
        Courier newCourier = new Courier(null, "1234", "nikita");
        deleteTestCourier(newCourier);

        Response response = createTestCourier(newCourier);

        response.then().statusCode(400).and()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));

        deleteTestCourier(newCourier);
    }
}
