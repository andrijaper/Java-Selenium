package automaticity_academy.api;

import org.json.JSONObject;

import automaticity_academy.constants.ApiConstans;
import automaticity_academy.utils.General;
import io.restassured.response.Response;

public class ProductsApi extends BaseApi {

    public ProductsApi() {
    }

    public Response showAllProducts(String token) {
        return sendApiRequest(ApiConstans.HttpMethods.GET.getMethod(), ApiConstans.urlEndpoint.PRODUCTS, token, null);
    }

    public Response showOneProduct(String id, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.GET.getMethod(), ApiConstans.urlEndpoint.PRODUCTS + "/" + id,
                token, null);
    }

    public Response updateProduct(JSONObject body, String id, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.PUT.getMethod(), ApiConstans.urlEndpoint.PRODUCTS + "/" + id,
                token, body);
    }

    public Response addNewProduct(JSONObject newProductBody, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(), ApiConstans.urlEndpoint.PRODUCTS, token,
                newProductBody);
    }

    public Response removeProduct(String id, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.DELETE.getMethod(), ApiConstans.urlEndpoint.PRODUCTS + "/" + id,
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
