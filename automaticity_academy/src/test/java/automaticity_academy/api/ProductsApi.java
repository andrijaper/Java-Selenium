package automaticity_academy.api;

import org.json.JSONObject;

import automaticity_academy.constants.ApiConstants;
import automaticity_academy.utils.General;
import io.restassured.response.Response;

public class ProductsApi extends BaseApi {

    public ProductsApi() {
    }

    public Response showAllProducts(String token) {
        return sendApiRequest(ApiConstants.HttpMethods.GET.getMethod(), ApiConstants.Endpoint.PRODUCTS, token, null);
    }

    public Response showOneProduct(String id, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.GET.getMethod(), ApiConstants.Endpoint.PRODUCTS + "/" + id,
            token, null);
    }

    public Response updateProduct(JSONObject body, String id, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.PUT.getMethod(), ApiConstants.Endpoint.PRODUCTS + "/" + id,
            token, body);
    }

    public Response addNewProduct(JSONObject newProductBody, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.POST.getMethod(), ApiConstants.Endpoint.PRODUCTS, token,
            newProductBody);
    }

    public Response removeProduct(String id, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.DELETE.getMethod(), ApiConstants.Endpoint.PRODUCTS + "/" + id,
            token,
            null);
    }

    public JSONObject generateRandomProductBody() {
        JSONObject json = new JSONObject();
        json.put("name", General.generateRandomString(10));
        json.put("description", General.generateRandomString(5));
        json.put("price", General.generateRandomDouble(1000, 2000));
        json.put("in_stock", true);
        json.put("quantity", General.generateRandomInt(2));
        json.put("rating", General.generateRandomDouble(1, 5));
        return json;
    }

}
