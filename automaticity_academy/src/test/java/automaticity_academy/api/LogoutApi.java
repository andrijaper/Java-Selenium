package automaticity_academy.api;

import automaticity_academy.constants.ApiConstants;
import io.restassured.response.Response;

public class LogoutApi extends BaseApi {

    public void logout(String token) {
        Response response = sendApiRequest(ApiConstants.HttpMethods.POST.getMethod(), "/auth" + ApiConstants.Endpoint.LOGOUT,
            token, null);
        checkStatusCode(response, ApiConstants.StatusAndCode.OK.getCode());
        checkStatusMessage(response, ApiConstants.StatusAndCode.OK.getStatusMessage());
    }
}
