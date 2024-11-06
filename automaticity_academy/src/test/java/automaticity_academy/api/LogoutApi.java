package automaticity_academy.api;

import automaticity_academy.constants.ApiConstans;
import io.restassured.response.Response;

public class LogoutApi extends BaseApi {

    public void logout(String token) {
        Response response = sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(), "/auth"+ApiConstans.Endpoint.LOGOUT,
                token, null);
        checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        checkStatusMessage(response, ApiConstans.StatusAndCode.OK.getStatusMessage());
    }
}
