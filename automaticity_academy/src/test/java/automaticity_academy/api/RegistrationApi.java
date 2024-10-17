package automaticity_academy.api;

import java.util.Map;

import org.testng.Assert;

import io.restassured.response.Response;

public class RegistrationApi extends BaseApi {

    public RegistrationApi() {

    };

    public void checkIfUserRegistrated(Response response, Map<String, Object> user) {
        BaseApi base = new BaseApi();
        int id = Integer.parseInt(response.jsonPath().getString("user.id"));
        String email = response.jsonPath().getString("user.email");
        String password = response.jsonPath().getString("user.password");
        String token = base.getToken(response);

        Assert.assertTrue(id > 0);
        Assert.assertEquals(user.get("email"), email);
        Assert.assertTrue(password.length() > 0);
        Assert.assertTrue(token.length() > 0);
    }

}
