package automaticity_academy.tests.api;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automaticity_academy.api.LoginApi;
import automaticity_academy.api.ProductsApi;
import automaticity_academy.constants.ApiConstants;
import automaticity_academy.constants.User;
import automaticity_academy.utils.General;
import automaticity_academy.utils.JsonGenerator;
import io.restassured.response.Response;

public class Products {

    ProductsApi products;
    LoginApi login;
    JSONObject body;
    Response response;
    String token;
    String id;

    @BeforeClass
    public void initialize() {
        products = new ProductsApi();
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
    public void showOneProduct() {
        id = String.valueOf(General.generateRandomIntNumber(5, 60));
        response = products.showOneProduct(id, token);
        products.checkStatusCode(response, ApiConstants.StatusAndCode.OK.getCode());
        products.checkStatusMessage(response, ApiConstants.StatusAndCode.OK.getStatusMessage());
    }

    @Test
    public void shouldUpdateOneProduct() {
        JSONObject bodyNewProduct = products.generateRandomProductBody();
        Response responseNewProduct = products.addNewProduct(bodyNewProduct, token);
        id = responseNewProduct.jsonPath().getString("product.id");

        products.checkStatusCode(responseNewProduct, ApiConstants.StatusAndCode.OK.getCode());
        products.checkStatusMessage(responseNewProduct, ApiConstants.StatusAndCode.OK.getStatusMessage());

        JSONObject bodyForUpdateProduct = products.generateRandomProductBody();
        Response responseUpdatedProduct = products.updateProduct(bodyForUpdateProduct, id, token);

        products.checkStatusCode(responseUpdatedProduct, ApiConstants.StatusAndCode.OK.getCode());
        products.checkStatusMessage(responseUpdatedProduct, ApiConstants.StatusAndCode.OK.getStatusMessage());
        Assert.assertNotEquals(bodyNewProduct.getString("name"),
            responseUpdatedProduct.jsonPath().getString("product.name"));
    }

    @Test
    public void deleteProduct() {
        JSONObject bodyNewProduct = products.generateRandomProductBody();
        Response responseNewProduct = products.addNewProduct(bodyNewProduct, token);
        id = responseNewProduct.jsonPath().getString("product.id");

        products.checkStatusCode(responseNewProduct, ApiConstants.StatusAndCode.OK.getCode());
        products.checkStatusMessage(responseNewProduct, ApiConstants.StatusAndCode.OK.getStatusMessage());

        Response responseAfterRemoval = products.removeProduct(id, token);
        products.checkStatusCode(responseAfterRemoval, ApiConstants.StatusAndCode.OK.getCode());
        products.checkStatusMessage(responseAfterRemoval, ApiConstants.StatusAndCode.OK.getStatusMessage());

        response = products.showOneProduct(id, token);
        products.checkStatusCode(response, ApiConstants.StatusAndCode.NOT_FOUND.getCode());
        products.checkStatusMessage(response, ApiConstants.StatusAndCode.NOT_FOUND.getStatusMessage());
    }

    @Test
    public void addNewProduct() {
        Response numberOfProductsBefore = products.showAllProducts(token);
        body = products.generateRandomProductBody();
        response = products.addNewProduct(body, token);

        products.checkStatusCode(response, ApiConstants.StatusAndCode.OK.getCode());
        products.checkStatusMessage(response, ApiConstants.StatusAndCode.OK.getStatusMessage());

        Response numberOfProductsAfter = products.showAllProducts(token);

        Assert.assertEquals(numberOfProductsBefore.jsonPath().getList("products").size() + 1,
            numberOfProductsAfter.jsonPath().getList("products").size());
    }

    @Test
    public void showAllProducts() {
        response = products.showAllProducts(token);
        products.checkStatusCode(response, ApiConstants.StatusAndCode.OK.getCode());
        products.checkStatusMessage(response, ApiConstants.StatusAndCode.OK.getStatusMessage());
        Assert.assertFalse(response.jsonPath().getList("products").isEmpty());
    }
}
