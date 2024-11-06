package automaticity_academy.api;

import org.json.JSONObject;
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
        int code = getStatusCode(response);
        return response.statusLine().split(Integer.toString(code))[1].trim();
    }

    public void checkStatusMessage(Response response, String message) {
        String responseMessage = getStatusMessage(response);
        Assert.assertEquals(responseMessage, message);
    }

    public void checkStatusCode(Response response, int code) {
        int statusCode = getStatusCode(response);
        Assert.assertEquals(statusCode, code);
    }

    public String getUsersId(Response response) {
        return response.jsonPath().getString("user.id");
    }

    public Response sendApiRequest(String method, String endpoint, String token, JSONObject body) {
        RestAssured.baseURI = Urls.PRODUCTION_URL + "/api/v1";
        RequestSpecification request = RestAssured.given();

        if (token != null) {
            request.header("Authorization", "Bearer " + token);
        }
        if (body != null) {
            request.header("Accept", "application/json");
            request.header("Content-Type", "application/json");
            request.body(body.toString());
        }

        return switch (method) {
            case "GET" -> request.get(endpoint);
            case "POST" -> request.post(endpoint);
            case "PUT" -> request.put(endpoint);
            case "DELETE" -> request.delete(endpoint);
            case "PATCH" -> request.patch(endpoint);
            default -> throw new IllegalArgumentException();
        };
    }
}
