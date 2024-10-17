package automaticity_academy.api;

import java.util.Map;

import org.testng.Assert;

import automaticity_academy.constants.Urls;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    public String getToken(Response response) {
        return response.jsonPath().getString("auth.token");
    }

    public int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    public String getStatusMessage(Response response) {
        return response.jsonPath().getString("message");
    }

    public void checkStatusMessage(Response response, String message) {
        String responseMessage = getStatusMessage(response);
        Assert.assertEquals(responseMessage, message);
    }

    public void checkStatusCode(Response response, int code) {
        int statusCode = getStatusCode(response);
        Assert.assertEquals(statusCode, code);
    }

    public Response sendApiRequest(String method, String urlEndpoint, String token, Map<String, Object> body) {
        RequestSpecification request = RestAssured.given();
        if (token != null) {
            request.header("Authorization", "Bearer " + token);
        }
        if (body != null) {
            request.header("Content-type", "application/json");
            request.body(body);
        }
        switch (method) {
            case "GET":
                return request.get(Urls.PRODUCTION_URL + "/api/v1" + urlEndpoint);
            case "POST":
                return request.post(Urls.PRODUCTION_URL + "/api/v1" + urlEndpoint);
            case "PUT":
                return request.put(Urls.PRODUCTION_URL + "/api/v1" + urlEndpoint);
            case "DELETE":
                return request.delete(Urls.PRODUCTION_URL + "/api/v1" + urlEndpoint);
            case "PATCH":
                return request.patch(Urls.PRODUCTION_URL + "/api/v1" + urlEndpoint);
            default:
                throw new IllegalArgumentException();
        }
    }
}
