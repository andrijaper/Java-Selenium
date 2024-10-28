package automaticity_academy.tests.api.customers;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automaticity_academy.api.LoginApi;
import automaticity_academy.api.customersApi.BillingInfoApi;
import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.BillingInfoConst;
import automaticity_academy.constants.User;
import automaticity_academy.utils.General;
import automaticity_academy.utils.JsonGenerator;
import io.restassured.response.Response;

public class BillingInfo {

    String token;
    String id;
    BillingInfoApi billingInfo;
    LoginApi login;
    String[] keys = User.getKeys();
    JSONObject bodyJson;

    @BeforeClass
    public void initialize() {
        billingInfo = new BillingInfoApi();
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
    public void shouldntBeAllowedForTwoCustomersToShareSameCardNumber() {
        bodyJson = billingInfo.generateBillingInfoBody(BillingInfoConst.BILLING_INFO);
        Response responseFirstUser = billingInfo.updateBillingInfo(bodyJson, id, token);
        billingInfo.checkStatusCode(responseFirstUser, ApiConstans.StatusAndCode.OK.getCode());
        billingInfo.checkStatusMessage(responseFirstUser,
                ApiConstans.StatusAndCode.OK.getStatusMessage());

        bodyJson = billingInfo.generateRandomBillingInfoBody();
        bodyJson.put(BillingInfoConst.getBillingInfoKeys()[2], BillingInfoConst.BILLING_INFO.getCardNumber());
        Response responseSecondUser = billingInfo.updateBillingInfo(bodyJson,
                String.valueOf(General.generateRandomNumber(2, 10)), token);

        billingInfo.checkStatusCode(responseSecondUser,
                ApiConstans.StatusAndCode.INTERNAL_SERVER_ERROR.getCode());
        billingInfo.checkStatusMessage(responseSecondUser,
                ApiConstans.StatusAndCode.INTERNAL_SERVER_ERROR.getStatusMessage());
    }

    @Test
    public void shouldntBeAllowedForCardNumberToContainElevenDigits() {
        bodyJson = billingInfo.generateBillingInfoBody(BillingInfoConst.BILLING_INFO);
        bodyJson.put(BillingInfoConst.getBillingInfoKeys()[2],
                String.valueOf(General.generateRandomNumber(10000000000L, 99999999999L)));
        Response response = billingInfo.updateBillingInfo(bodyJson, id, token);

        System.out.println(response.asPrettyString());
        billingInfo.checkStatusCode(response,
                ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
        billingInfo.checkStatusMessage(response,
                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
    }

    @Test
    public void shouldntBeAllowedForCvvToHaveTwoDigits() {
        bodyJson = billingInfo.generateBillingInfoBody(BillingInfoConst.BILLING_INFO);
        bodyJson.put(BillingInfoConst.getBillingInfoKeys()[3],
                String.valueOf(General.generateRandomInt(2)));
        Response response = billingInfo.updateBillingInfo(bodyJson, id, token);

        System.out.println(response.asPrettyString());
        billingInfo.checkStatusCode(response,
                ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
        billingInfo.checkStatusMessage(response,
                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
    }

    @Test
    public void shouldntBeAllowedForCvvToHaveFourDigits() {
        bodyJson = billingInfo.generateBillingInfoBody(BillingInfoConst.BILLING_INFO);
        bodyJson.put(BillingInfoConst.getBillingInfoKeys()[3],
                String.valueOf(General.generateRandomInt(4)));
        Response response = billingInfo.updateBillingInfo(bodyJson, id, token);

        System.out.println(response.asPrettyString());
        billingInfo.checkStatusCode(response,
                ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
        billingInfo.checkStatusMessage(response,
                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
    }

    @Test
    public void shouldBeAllowedForCardNumberToContainTwelveDigits() {
        bodyJson = billingInfo.generateBillingInfoBody(BillingInfoConst.BILLING_INFO);
        bodyJson.put(BillingInfoConst.getBillingInfoKeys()[2],
                String.valueOf(General.generateRandomNumber(100000000000L, 999999999999L)));
        Response response = billingInfo.updateBillingInfo(bodyJson, id, token);

        billingInfo.checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        billingInfo.checkStatusMessage(response,
                ApiConstans.StatusAndCode.OK.getStatusMessage());

        Response responseGetUser = billingInfo.getBillingInfo(id, token);

        billingInfo.checkIfBillingInfoIsUpdated(responseGetUser, bodyJson);
    }

    @Test
    public void shouldListCustomersBillingInfo() {
        Response response = billingInfo.getBillingInfo(id, token);
        billingInfo.checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        billingInfo.checkStatusMessage(response,
                ApiConstans.StatusAndCode.OK.getStatusMessage());
    }

    @Test
    public void updateCustomersBillingInfo() {
        bodyJson = billingInfo.generateBillingInfoBody(BillingInfoConst.BILLING_INFO);
        Response responseUpdate = billingInfo.updateBillingInfo(bodyJson, id, token);
        billingInfo.checkStatusCode(responseUpdate, ApiConstans.StatusAndCode.OK.getCode());
        billingInfo.checkStatusMessage(responseUpdate,
                ApiConstans.StatusAndCode.OK.getStatusMessage());

        Response responseGetUser = billingInfo.getBillingInfo(id, token);

        billingInfo.checkIfBillingInfoIsUpdated(responseGetUser, bodyJson);
    }
}
