package com.simbirsoft.helpers;

import com.simbirsoft.config.CredentialConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class BrowserStack {

    public static CredentialConfig credentials = ConfigFactory.create(CredentialConfig.class);

    public static String videoUrl(String sessionId) {
        return given()
                .auth().basic(credentials.browserstack_user(), credentials.browserstack_key())
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response()
                .path("automation_session.video_url");

    }
}
