package automaticity_academy.tests.api;

import java.util.Map;

import org.testng.annotations.Test;

import automaticity_academy.api.RegistrationApi;
import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.User;
import automaticity_academy.utils.JsonGenerator;
import io.restassured.response.Response;

public class Registration {

    @Test
    public void successfullRegistration() {
        RegistrationApi registration = new RegistrationApi();
        String[][] userJson = User.generateRandomUserForRegistration();
        // System.out.println(userJson);
        Map<String, Object> userBody = JsonGenerator.generateJson(userJson);
        System.out.println(userBody);
        Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                ApiConstans.urlEndpoint.REGISTER,
                null,
                userBody);
        // System.out.println(response.toString());
        registration.checkIfUserRegistrated(response, userBody);
        registration.checkStatusCode(response, 200);
        registration.checkStatusMessage(response, ApiConstans.StatusMessages.USER_CREATED);
    }
}
