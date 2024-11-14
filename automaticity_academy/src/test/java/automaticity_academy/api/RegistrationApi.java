package automaticity_academy.api;

import org.json.JSONObject;
import org.testng.Assert;

import automaticity_academy.constants.ApiConstants;
import io.restassured.response.Response;

public class RegistrationApi extends BaseApi {

    public RegistrationApi() {
    }

    ;

    public Response registerUser(JSONObject body) {
        return sendApiRequest(ApiConstants.HttpMethods.POST.getMethod(),
            "/auth" + ApiConstants.Endpoint.REGISTER,
            null,
            body);
    }

    public void checkIfUserRegistrated(Response response, JSONObject user) {
        Assert.assertTrue(Integer.parseInt(response.jsonPath().getString("user.id")) > 0);
        Assert.assertEquals(user.getString("email"), response.jsonPath().getString("user.email"));
        Assert.assertFalse(response.jsonPath().getString("user.password").isEmpty());
        Assert.assertFalse(getToken(response).isEmpty());
    }
}
