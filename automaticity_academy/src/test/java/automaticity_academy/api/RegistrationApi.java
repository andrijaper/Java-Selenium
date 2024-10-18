package automaticity_academy.api;

import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.response.Response;

public class RegistrationApi extends BaseApi {

    public RegistrationApi() {

    };

    public void checkIfUserRegistrated(Response response, JSONObject user) {
        int id = Integer.parseInt(response.jsonPath().getString("user.id"));
        String email = response.jsonPath().getString("user.email");
        String password = response.jsonPath().getString("user.password");
        String token = getToken(response);

        Assert.assertTrue(id > 0);
        Assert.assertEquals(user.getString("email"), email);
        Assert.assertTrue(password.length() > 0);
        Assert.assertTrue(token.length() > 0);
    }
}
