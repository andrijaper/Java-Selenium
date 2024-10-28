package automaticity_academy.api.customersApi;

import org.json.JSONObject;
import org.testng.Assert;

import automaticity_academy.api.BaseApi;
import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.BillingInfoConst;
import automaticity_academy.utils.General;
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

        public JSONObject generateBillingInfoBody(BillingInfoConst billingInfo) {
                JSONObject json = new JSONObject();
                json.put(BillingInfoConst.getBillingInfoKeys()[0], billingInfo.getCardholder());
                json.put(BillingInfoConst.getBillingInfoKeys()[1], billingInfo.getCardType());
                json.put(BillingInfoConst.getBillingInfoKeys()[2], billingInfo.getCardNumber());
                json.put(BillingInfoConst.getBillingInfoKeys()[3], billingInfo.getCvv());
                json.put(BillingInfoConst.getBillingInfoKeys()[4], billingInfo.getCardExpirationDate());
                return json;
        }

        public JSONObject generateRandomBillingInfoBody() {
                JSONObject json = new JSONObject();
                json.put(BillingInfoConst.getBillingInfoKeys()[0], General.generateRandomString(10));
                json.put(BillingInfoConst.getBillingInfoKeys()[1], General.generateRandomString(5));
                json.put(BillingInfoConst.getBillingInfoKeys()[2],
                                String.valueOf(General.generateRandomNumber(1000000000000000L, 9999999999999999L)));
                json.put(BillingInfoConst.getBillingInfoKeys()[3],
                                String.valueOf(General.generateRandomNumber(100, 999)));
                json.put(BillingInfoConst.getBillingInfoKeys()[4],
                                BillingInfoConst.BILLING_INFO.getCardExpirationDate());
                return json;
        }

        public void checkIfBillingInfoIsUpdated(Response response, JSONObject user) {
                Assert.assertEquals(response.jsonPath().getString("billing_info.cardholder"),
                                user.getString(BillingInfoConst.getBillingInfoKeys()[0]));
                Assert.assertEquals(response.jsonPath().getString("billing_info.card_type"),
                                user.getString(BillingInfoConst.getBillingInfoKeys()[1]));
                Assert.assertEquals(response.jsonPath().getString("billing_info.card_number"),
                                user.getString(BillingInfoConst.getBillingInfoKeys()[2]));
                Assert.assertEquals(response.jsonPath().getString("billing_info.cvv"),
                                user.getString(BillingInfoConst.getBillingInfoKeys()[3]));
                Assert.assertEquals(response.jsonPath().getString("billing_info.card_expiration_date"),
                                user.getString(BillingInfoConst.getBillingInfoKeys()[4]));
        }

}
