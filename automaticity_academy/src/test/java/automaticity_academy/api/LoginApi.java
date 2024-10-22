package automaticity_academy.api;

import org.json.JSONObject;
import org.testng.Assert;

import automaticity_academy.constants.ApiConstans;
import io.restassured.response.Response;

public class LoginApi extends BaseApi {

    public LoginApi() {

    }

    public Response login(JSONObject body, String apiMethod, String urlEndpoint) {
        String method = ApiConstans.HttpMethods.POST.getMethod();
        String endpoint = ApiConstans.urlEndpoint.LOGIN;
        if (apiMethod != null) {
            method = apiMethod;
        } else if (urlEndpoint != null) {
            endpoint = urlEndpoint;
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
        Assert.assertTrue(password.length() > 0);
        Assert.assertTrue(token.length() > 0);
    }

}
