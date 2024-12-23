package automaticity_academy.api;

import automaticity_academy.constants.ApiConstants;
import io.restassured.response.Response;

public class CartApi extends BaseApi {

    public CartApi() {
    }

    public Response showOneCart(String id, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.GET.getMethod(), ApiConstants.Endpoint.CART + "/" + id, token,
            null);
    }

    public Response removeCart(String id, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.DELETE.getMethod(), ApiConstants.Endpoint.PRODUCTS + "/" + id,
            token,
            null);
    }

    public Response addProductIntoCart(String cartId, String productId, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.POST.getMethod(),
            ApiConstants.Endpoint.CART + "/" + cartId + ApiConstants.Endpoint.PRODUCTS + "/" + productId, token,
            null);
    }

    public Response removeProductFromCart(String cartId, String productId, String token) {
        return sendApiRequest(ApiConstants.HttpMethods.DELETE.getMethod(),
            ApiConstants.Endpoint.CART + "/" + cartId + ApiConstants.Endpoint.PRODUCTS + "/" + productId, token,
            null);
    }
}
