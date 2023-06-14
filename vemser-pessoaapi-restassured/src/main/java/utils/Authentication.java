package utils;

import static io.restassured.RestAssured.given;

public class Authentication {

    private static String url = "http://vemser-dbc.dbccompany.com.br:39000/vemser/dbc-pessoa-api/auth";

    public static String getTokenAdmin() {

        return
                given().
                        contentType("application/json").
                        body("{\n" +
                                "  \"login\": \"admin\",\n" +
                                "  \"senha\": \"123\"\n" +
                                "}").
                when()
                        .post(url)
                .then()
                        .extract().asString();
    }
}
