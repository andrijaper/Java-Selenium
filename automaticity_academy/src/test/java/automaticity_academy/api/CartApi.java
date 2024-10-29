package automaticity_academy.api;

import automaticity_academy.constants.ApiConstans;
import io.restassured.response.Response;

public class CartApi extends BaseApi {

    public CartApi() {
    }

    public Response showOneCart(String id, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.GET.getMethod(), ApiConstans.urlEndpoint.CART + "/" + id, token,
                null);
    }

    public Response removeCart(String id, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.DELETE.getMethod(), ApiConstans.urlEndpoint.PRODUCTS + "/" + id,
                token,
                null);
    }

    public Response addProductIntoCart(String cartId, String productId, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                ApiConstans.urlEndpoint.CART + "/" + cartId + ApiConstans.urlEndpoint.PRODUCTS + "/" + productId, token,
                null);
    }

    public Response removeProductFromCart(String cartId, String productId, String token) {
        return sendApiRequest(ApiConstans.HttpMethods.DELETE.getMethod(),
                ApiConstans.urlEndpoint.CART + "/" + cartId + ApiConstans.urlEndpoint.PRODUCTS + "/" + productId, token,
                null);
    }
}
