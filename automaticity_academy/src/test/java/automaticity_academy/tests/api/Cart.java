package automaticity_academy.tests.api;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automaticity_academy.api.CartApi;
import automaticity_academy.api.LoginApi;
import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.User;
import automaticity_academy.utils.General;
import automaticity_academy.utils.JsonGenerator;
import io.restassured.response.Response;

public class Cart {

    CartApi cart;
    LoginApi login;
    Response response;
    String token;
    String id;

    @BeforeClass
    public void initialize() {
        cart = new CartApi();
        login = new LoginApi();
    }

    @BeforeMethod
    public void login() {
        String[][] user = User.generateUsersDataForLogin(User.TEST_USER);
        JSONObject bodyJson = JsonGenerator.generateJson(user);
        response = login.login(bodyJson, null, null);
        token = login.getToken(response);
        id = login.getUsersId(response);
    }

    @Test
    public void showOneCart() {
        response = cart.showOneCart(id, token);
        cart.checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        cart.checkStatusMessage(response, ApiConstans.StatusAndCode.OK.getStatusMessage());
    }

    @Test(enabled = false)
    public void removeCart() {
        response = cart.removeCart("65", token);
        cart.checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        cart.checkStatusMessage(response, ApiConstans.StatusAndCode.OK.getStatusMessage());
    }

    @Test(enabled = false)
    public void addProductIntoCart() {
        String productId = String.valueOf(General.generateRandomIntNumber(10, 30));
        String cartId = String.valueOf(General.generateRandomIntNumber(10, 30));
        response = cart.addProductIntoCart(cartId, productId, token);
        cart.checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        cart.checkStatusMessage(response, ApiConstans.StatusAndCode.OK.getStatusMessage());

        response = cart.showOneCart(cartId, token);

        // COMPLETE TEST AFTER API REQUEST IS FIXED - TEST SKIPPED
        cart.checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        cart.checkStatusMessage(response, ApiConstans.StatusAndCode.OK.getStatusMessage());
    }

    @Test(enabled = false)
    public void removeProductFromCart() {
        String productId = String.valueOf(General.generateRandomIntNumber(10, 30));
        String cartId = String.valueOf(General.generateRandomIntNumber(10, 30));
        response = cart.removeProductFromCart(cartId, productId, token);
        cart.checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        cart.checkStatusMessage(response, ApiConstans.StatusAndCode.OK.getStatusMessage());

        response = cart.showOneCart(cartId, token);

        // COMPLETE TEST AFTER API REQUEST IS FIXED - TEST SKIPPED
        cart.checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        cart.checkStatusMessage(response, ApiConstans.StatusAndCode.OK.getStatusMessage());
    }

}
