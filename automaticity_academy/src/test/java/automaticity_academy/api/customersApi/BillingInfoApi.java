package automaticity_academy.api.customersApi;

import org.json.JSONObject;

import automaticity_academy.api.BaseApi;
import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.BillingInfo;
import io.restassured.response.Response;

public class BillingInfoApi extends BaseApi {

    public BillingInfoApi() {
    };

    public Response getBillingInfo(String id, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.GET.getMethod(),
                ApiConstans.urlEndpoint.CUSTOMERS + "/" + id + ApiConstans.urlEndpoint.BILLING_INFO,
                token,
                null);
    }

    public Response updateBillingInfo(JSONObject body, String id, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.PUT.getMethod(),
                ApiConstans.urlEndpoint.CUSTOMERS + "/" + id + ApiConstans.urlEndpoint.BILLING_INFO,
                token,
                body);
    }

    public static Object[][] generateBillingInfo(BillingInfo billingInfo) {
        return new Object[][] { { BillingInfo.getBillingInfoKeys()[0], billingInfo.getCardholder() },
                { BillingInfo.getBillingInfoKeys()[1], billingInfo.getCardType() },
                { BillingInfo.getBillingInfoKeys()[2], billingInfo.getCardNumber() },
                { BillingInfo.getBillingInfoKeys()[3], billingInfo.getCvv() },
                { BillingInfo.getBillingInfoKeys()[4], billingInfo.getCardExpirationDate() }
        };
    }

}
