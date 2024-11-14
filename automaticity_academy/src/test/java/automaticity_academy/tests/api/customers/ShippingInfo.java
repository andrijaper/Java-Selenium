package automaticity_academy.tests.api.customers;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automaticity_academy.api.LoginApi;
import automaticity_academy.api.customersApi.ShippingInfoApi;
import automaticity_academy.constants.ApiConstants;
import automaticity_academy.constants.ShippingInfoConst;
import automaticity_academy.constants.User;
import automaticity_academy.utils.JsonGenerator;
import io.restassured.response.Response;

public class ShippingInfo {

    ShippingInfoApi shippingInfo;
    LoginApi login;
    String token;
    String id;
    JSONObject bodyJson;

    @BeforeClass
    public void initialize() {
        shippingInfo = new ShippingInfoApi();
        login = new LoginApi();
    }

    @BeforeMethod
    public void login() {
        String[][] user = User.generateUsersDataForLogin(User.TEST_USER);
        JSONObject bodyJson = JsonGenerator.generateJson(user);
        Response response = login.login(bodyJson, null, null);
        token = login.getToken(response);
        id = login.getUsersId(response);
    }

    @Test
    public void shouldListCustomersBillingInfo() {
        Response response = shippingInfo.getShippingInfo(id, token);
        shippingInfo.checkStatusCode(response, ApiConstants.StatusAndCode.OK.getCode());
        shippingInfo.checkStatusMessage(response,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
    }

    @Test
    public void updateCustomersShippingInfo() {
        bodyJson = shippingInfo.generateShippingInfoBody(ShippingInfoConst.SHIPPING_INFO);
        Response responseUpdate = shippingInfo.updateShippingInfo(bodyJson, id, token);
        shippingInfo.checkStatusCode(responseUpdate, ApiConstants.StatusAndCode.OK.getCode());
        shippingInfo.checkStatusMessage(responseUpdate,
            ApiConstants.StatusAndCode.OK.getStatusMessage());

        Response responseGetUser = shippingInfo.getShippingInfo(id, token);

        shippingInfo.checkIfShippingInfoIsUpdated(responseGetUser, bodyJson);
    }

}
