package automaticity_academy.api;

import org.json.JSONObject;

import automaticity_academy.constants.ApiConstans;
import automaticity_academy.utils.General;
import io.restassured.response.Response;

public class ProductImageApi extends BaseApi {

    public ProductImageApi() {
    }

    public Response getAllProductImages(String token) {
        return sendApiRequest(ApiConstans.HttpMethods.GET.getMethod(), ApiConstans.urlEndpoint.PRODUCT_IMAGE, token,
                null);
    }

    public Response addNewProductImage(JSONObject body, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(), ApiConstans.urlEndpoint.PRODUCT_IMAGE, token,
                body);
    }

    public Response showOneProductImage(String id, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.GET.getMethod(),
                ApiConstans.urlEndpoint.PRODUCT_IMAGE + "/" + id, token,
                null);
    }

    public Response removeProductImage(String id, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.DELETE.getMethod(),
                ApiConstans.urlEndpoint.PRODUCT_IMAGE + "/" + id, token,
                null);
    }

    public JSONObject generateProductImageBody() {
        JSONObject json = new JSONObject();
        json.put("src", "automaticity_academy/src/test/java/resources/product_images/test_image.png");
        json.put("title", General.generateRandomString(10));
        json.put("description", General.generateRandomString(10));
        json.put("mime_type", "image/png");
        json.put("alt_text", General.generateRandomString(10));
        return json;
    }

}
