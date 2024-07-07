import APIConfig.URL;
import Models.GetSingleUser.GetSingleUser;
import Models.PostCreateUser.User;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class PostTest {

    @Test
    public void testCreateUser() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
            .baseUri(URL.baseUrl)
            .contentType("application/json")
            .body(requestBody)
            .when()
            .post("/api/users")
            .then().log().body()
            .assertThat().statusCode(201)
            .and().body("name", equalTo("morpheus"))
            .and().body("job", equalTo("leader"));
    }

    @Test
    public void testCreateUserPojo() {
        User userToPost = new User("morpheus", "leader");

        User userFromResponse = given()
                .baseUri(URL.baseUrl)
                .contentType("application/json")
                .body(userToPost)
                .when()
                .post("/api/users")
                .then().log().body()
                .assertThat().statusCode(201)
                .extract().body().as(User.class);

        assertEquals(userToPost.getName(), userFromResponse.getName(), "Name fields do not match");
        assertEquals(userToPost.getJob(), userFromResponse.getJob(), "Job fields do not match");
        assertNotNull(userFromResponse.getId(), "ID field is null in response");
        assertNotNull(userFromResponse.getCreatedAt(), "CreatedAt field is null in response");
    }
}