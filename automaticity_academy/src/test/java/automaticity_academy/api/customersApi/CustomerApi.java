package automaticity_academy.api.customersApi;

import org.json.JSONObject;
import org.testng.Assert;

import automaticity_academy.api.BaseApi;
import automaticity_academy.constants.ApiConstants;
import io.restassured.response.Response;

public class CustomerApi extends BaseApi {

    public CustomerApi() {
    }

    public Response getAllCustomers(String token) {
        return sendApiRequest(ApiConstants.HttpMethods.GET.getMethod(),
            ApiConstants.Endpoint.CUSTOMERS,
            token,
            null);
    }

    public Response getCustomerById(String id, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.GET.getMethod(),
            ApiConstants.Endpoint.CUSTOMERS + "/" + id,
            token,
            null);
    }

    public Response deleteCustomer(String id, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.DELETE.getMethod(),
            ApiConstants.Endpoint.CUSTOMERS + "/" + id,
            token,
            null);
    }

    public Response updateUsersData(JSONObject body, String id, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.PUT.getMethod(),
            ApiConstants.Endpoint.CUSTOMERS + "/" + id,
            token,
            body);
    }

    public void checkIsUserUpdated(JSONObject dataForUpdate, String userId, String token) {
        Response updatedUserResponse = getCustomerById(userId, token);
        JSONObject updatedUser = new JSONObject(updatedUserResponse.asString());
        Assert.assertEquals(updatedUser.getJSONObject("customer").getString("username"),
            dataForUpdate.getString("username"));
        Assert.assertEquals(updatedUser.getJSONObject("customer").getString("email"), dataForUpdate.getString("email"));
    }
}
