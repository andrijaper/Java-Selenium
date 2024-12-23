package automaticity_academy.api.customersApi;

import org.json.JSONObject;
import org.testng.Assert;

import automaticity_academy.api.BaseApi;
import automaticity_academy.constants.ApiConstants;
import automaticity_academy.constants.ShippingInfoConst;
import automaticity_academy.utils.General;
import io.restassured.response.Response;

public class ShippingInfoApi extends BaseApi {

    public ShippingInfoApi() {
    }

    ;

    public Response getShippingInfo(String id, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.GET.getMethod(),
            ApiConstants.Endpoint.CUSTOMERS + "/" + id + ApiConstants.Endpoint.SHIPPING_INFO,
            token,
            null);
    }

    public Response updateShippingInfo(JSONObject body, String id, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.PUT.getMethod(),
            ApiConstants.Endpoint.CUSTOMERS + "/" + id + ApiConstants.Endpoint.SHIPPING_INFO,
            token,
            body);
    }

    public JSONObject generateShippingInfoBody(ShippingInfoConst shippingInfo) {
        JSONObject json = new JSONObject();
        json.put(ShippingInfoConst.getShippingInfoKeys()[0], shippingInfo.getFirstName());
        json.put(ShippingInfoConst.getShippingInfoKeys()[1], shippingInfo.getLastName());
        json.put(ShippingInfoConst.getShippingInfoKeys()[2], shippingInfo.getEmail());
        json.put(ShippingInfoConst.getShippingInfoKeys()[3], shippingInfo.getStreetAndNumber());
        json.put(ShippingInfoConst.getShippingInfoKeys()[4], shippingInfo.getPhoneNumber());
        json.put(ShippingInfoConst.getShippingInfoKeys()[5], shippingInfo.getCity());
        json.put(ShippingInfoConst.getShippingInfoKeys()[6], shippingInfo.getPostalCode());
        json.put(ShippingInfoConst.getShippingInfoKeys()[7], shippingInfo.getCountry());
        return json;
    }

    public JSONObject generateRandomShippingInfoBody() {
        JSONObject json = new JSONObject();
        json.put(ShippingInfoConst.getShippingInfoKeys()[0], General.generateRandomString(8));
        json.put(ShippingInfoConst.getShippingInfoKeys()[1], General.generateRandomString(8));
        json.put(ShippingInfoConst.getShippingInfoKeys()[2], General.generateRandomString(8) + "@test.com");
        json.put(ShippingInfoConst.getShippingInfoKeys()[3],
            General.generateRandomString(7) + " " + String.valueOf(General.generateRandomInt(3)));
        json.put(ShippingInfoConst.getShippingInfoKeys()[4], String.valueOf(General.generateRandomInt(10)));
        json.put(ShippingInfoConst.getShippingInfoKeys()[5], General.generateRandomString(8));
        json.put(ShippingInfoConst.getShippingInfoKeys()[6], General.generateRandomInt(5));
        json.put(ShippingInfoConst.getShippingInfoKeys()[7], General.generateRandomString(8));
        return json;
    }

    public void checkIfShippingInfoIsUpdated(Response response, JSONObject user) {
        Assert.assertEquals(response.jsonPath().getString("shipping_info.first_name"),
            user.getString(ShippingInfoConst.getShippingInfoKeys()[0]));
        Assert.assertEquals(response.jsonPath().getString("shipping_info.last_name"),
            user.getString(ShippingInfoConst.getShippingInfoKeys()[1]));
        Assert.assertEquals(response.jsonPath().getString("shipping_info.email"),
            user.getString(ShippingInfoConst.getShippingInfoKeys()[2]));
        Assert.assertEquals(response.jsonPath().getString("shipping_info.street_and_number"),
            user.getString(ShippingInfoConst.getShippingInfoKeys()[3]));
        Assert.assertEquals(response.jsonPath().getString("shipping_info.phone_number"),
            user.getString(ShippingInfoConst.getShippingInfoKeys()[4]));
        Assert.assertEquals(response.jsonPath().getString("shipping_info.city"),
            user.getString(ShippingInfoConst.getShippingInfoKeys()[5]));
        Assert.assertEquals(response.jsonPath().getString("shipping_info.postal_code"),
            String.valueOf(user.getInt(ShippingInfoConst.getShippingInfoKeys()[6])));
        Assert.assertEquals(response.jsonPath().getString("shipping_info.country"),
            user.getString(ShippingInfoConst.getShippingInfoKeys()[7]));
    }
}
