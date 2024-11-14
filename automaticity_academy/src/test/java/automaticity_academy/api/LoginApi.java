package automaticity_academy.api;

import org.json.JSONObject;
import org.testng.Assert;

import automaticity_academy.constants.ApiConstants;
import io.restassured.response.Response;

public class LoginApi extends BaseApi {

    public LoginApi() {

    }

    public Response login(JSONObject body, String apiMethod, String Endpoint) {
        String method = ApiConstants.HttpMethods.POST.getMethod();
        String endpoint = "/auth" + ApiConstants.Endpoint.LOGIN;
        if (apiMethod != null) {
            method = apiMethod;
        } else if (Endpoint != null) {
            endpoint = Endpoint;
        }
        return sendApiRequest(method,
            endpoint,
            null,
            body);
    }

    public void checkIfUserIdLoggedIn(Response response, JSONObject user) {
        int id = Integer.parseInt(response.jsonPath().getString("user.id"));
        String email = response.jsonPath().getString("user.email");
        String password = response.jsonPath().getString("user.password");
        String token = getToken(response);

        Assert.assertTrue(id > 0);
        Assert.assertEquals(user.getString("email"), email);
        Assert.assertFalse(password.isEmpty());
        Assert.assertFalse(token.isEmpty());
    }

}
