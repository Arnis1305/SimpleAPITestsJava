import APIConfig.URL;
import Models.GetSingleUser.Data;
import Models.GetSingleUser.GetSingleUser;
import Models.GetSingleUser.Support;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class GetTest {

    @Test
    public void testGetUsersPage2StatusCode() {
        RestAssured.baseURI = URL.baseUrl;

        Response response = RestAssured.get("/api/users?page=2");

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200, "Response status code does not match");
    }

    @Test
    public void testStatusCodeForGetUsersPage2WithGiven() {
        Data expectedData = new Data(8, "lindsay.ferguson@reqres.in", "Lindsay", "Ferguson", "https://reqres.in/img/faces/8-image.jpg");

        List<Data> dataList = given()
            .baseUri(URL.baseUrl)
            .when()
            .get("/api/users?page=2")
            .then().log().body()
            .assertThat().statusCode(200)
            .extract().response().jsonPath().getList("data", Data.class);

        Assert.assertTrue(dataList.contains(expectedData), "Expected data is not listed");
    }

    @Test
    public void testGetUser2Details() {
        given()
            .baseUri(URL.baseUrl)
            .when()
            .get("/api/users/2")
            .then().log().body()
            .assertThat().statusCode(200)
            .and().body("data.id", equalTo(2))
            .and().body("data.first_name", equalTo("Janet"))
            .and().body("data.last_name", equalTo("Weaver"))
            .and().body("data.email", equalTo("janet.weaver@reqres.in"));
    }

    @Test
    public void testGetUser2DetailsWithPojo() {
        Data expectedData = new Data(2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg");
        Support expectedSupport = new Support("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!");
        GetSingleUser expectedGetSingleUser = new GetSingleUser(expectedData, expectedSupport);

        GetSingleUser getSingleUser = given()
                .baseUri(URL.baseUrl)
                .when()
                .get("/api/users/2")
                .then().log().body()
                .assertThat().statusCode(200)
                .extract().body().as(GetSingleUser.class);

        Assert.assertEquals(getSingleUser.toString(), expectedGetSingleUser.toString());
    }
}