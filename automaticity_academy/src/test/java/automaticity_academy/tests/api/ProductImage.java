package automaticity_academy.tests.api;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automaticity_academy.api.LoginApi;
import automaticity_academy.api.ProductImageApi;
import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.User;
import automaticity_academy.utils.JsonGenerator;
import io.restassured.response.Response;

public class ProductImage {

    ProductImageApi pImage;
    LoginApi login;
    Response response;
    String token;
    String id;

    @BeforeClass
    public void initialize() {
        pImage = new ProductImageApi();
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
    public void addNewProductImage() {
        JSONObject body = pImage.generateProductImageBody();
        response = pImage.addNewProductImage(body, token);
        pImage.checkStatusCode(response, ApiConstans.StatusAndCode.CREATED.getCode());
        pImage.checkStatusMessage(response, ApiConstans.StatusAndCode.CREATED.getStatusMessage());
    }

    @Test
    public void getOneProductImages() {
        response = pImage.showOneProductImage(id, token);
        pImage.checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        pImage.checkStatusMessage(response, ApiConstans.StatusAndCode.OK.getStatusMessage());
    }

    @Test
    public void getAllProductImages() {
        response = pImage.getAllProductImages(token);
        pImage.checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        pImage.checkStatusMessage(response, ApiConstans.StatusAndCode.OK.getStatusMessage());
        Assert.assertTrue(response.jsonPath().getList("images").size() > 0);
    }

    @Test(enabled = false)
    public void removeProductImage() {
        JSONObject body = pImage.generateProductImageBody();
        response = pImage.addNewProductImage(body, token);
        String newProductImageId = response.jsonPath().getString("product_image.id");
        pImage.checkStatusCode(response, ApiConstans.StatusAndCode.CREATED.getCode());
        pImage.checkStatusMessage(response, ApiConstans.StatusAndCode.CREATED.getStatusMessage());

        response = pImage.removeProductImage(newProductImageId, token);
        pImage.checkStatusCode(response, ApiConstans.StatusAndCode.OK.getCode());
        pImage.checkStatusMessage(response, ApiConstans.StatusAndCode.OK.getStatusMessage());
    }

}
