package tripDemo.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    private static final ContentType CONTENT_TYPE = ContentType.JSON;
    private static final ContentType ACCEPT = ContentType.JSON;

    public static Response post(String path, String body) {
        return prepare()
                .body(body)
                .when()
                .post(path)
                .thenReturn();
    }

    public static Response get(String path, long id) {
        return prepare()
                .pathParam("id", id)
                .when()
                .get(path + "/{id}")
                .thenReturn();
    }

    public static Response put(String path, String body) {
        return prepare()
                .body(body)
                .when()
                .put(path)
                .thenReturn();
    }

    public static Response delete(String path, long id) {
        return prepare()
                .pathParam("id", id)
                .when()
                .delete(path + "/{id}")
                .thenReturn();
    }

    private static RequestSpecification prepare() {
        return given()
                .log().all(true)
                .contentType(CONTENT_TYPE)
                .accept(ACCEPT);
    }
}