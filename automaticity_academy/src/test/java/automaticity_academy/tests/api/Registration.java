package automaticity_academy.tests.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automaticity_academy.api.RegistrationApi;
import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.User;
import automaticity_academy.utils.General;
import automaticity_academy.utils.JsonGenerator;
import io.restassured.response.Response;

public class Registration {

        RegistrationApi registration;
        Map<String, String> userData;
        String[][] user;
        JSONObject bodyJson;
        JsonGenerator json;
        String[] keys = User.getKeys();

        @BeforeMethod
        public void initialize() {
                registration = new RegistrationApi();
                userData = new HashMap<>();
                json = new JsonGenerator();
                bodyJson = new JSONObject();
        }

        @Test
        public void existingUserShouldntRegisterAgain() {
                user = User.generateUsersData(User.REGISTRATION_USER);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void methodNotAllowed() {
                user = User.generateRandomUserForRegistration();
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.PATCH.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.METHOD_NOT_ALLOWED.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.METHOD_NOT_ALLOWED.getStatusMessage());
        }

        @Test
        public void invalidUrl() {
                user = User.generateRandomUserForRegistration();
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER + "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.NOT_FOUND.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.NOT_FOUND.getStatusMessage());
        }

        @Test
        public void shouldntBeAbleToRegisterWithAllEmptyFields() {
                userData.put(keys[0], "");
                userData.put(keys[1], "");
                userData.put(keys[2], "");

                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntBeAbleToRegisterWithOneEmptyField() {
                for (String key : keys) {
                        userData.put(key, "");
                        user = User.generateRandomUserWithInvalidValues(userData);
                        bodyJson = JsonGenerator.generateJson(user);
                        Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                        "/auth"+ApiConstans.Endpoint.REGISTER,
                                        null,
                                        bodyJson);
                        registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                        registration.checkStatusMessage(response,
                                        ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
                }
        }

        @Test
        public void shouldntBeAbleToRegisterWithBlankSpaceInOneField() {
                for (String key : keys) {
                        userData.put(key, " ");
                        user = User.generateRandomUserWithInvalidValues(userData);
                        bodyJson = JsonGenerator.generateJson(user);
                        Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                        "/auth"+ApiConstans.Endpoint.REGISTER,
                                        null,
                                        bodyJson);
                        registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                        registration.checkStatusMessage(response,
                                        ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
                }
        }

        @Test
        public void shouldntBeAbleToRegisterWithObjectInOneField() {
                String[][] objectToConvert = User.generateRandomUserForRegistration();
                JSONObject object = JsonGenerator.generateJson(objectToConvert);
                for (String key : keys) {
                        userData.put(key, object.toString());
                        user = User.generateRandomUserWithInvalidValues(userData);
                        bodyJson = JsonGenerator.generateJson(user);
                        JSONObject jsonUser = new JSONObject(bodyJson);
                        Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                        "/auth"+ApiConstans.Endpoint.REGISTER,
                                        null,
                                        jsonUser);
                        registration.checkStatusCode(response,
                                        ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                        registration.checkStatusMessage(response,
                                        ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
                }
        }

        @Test
        public void shouldntBeAbleToRegisterWithArraytInOneField() {
                for (String key : keys) {
                        userData.put(key, keys.toString());
                        user = User.generateRandomUserWithInvalidValues(userData);
                        bodyJson = JsonGenerator.generateJson(user);
                        JSONObject jsonUser = new JSONObject(bodyJson);
                        Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                        "/auth"+ApiConstans.Endpoint.REGISTER,
                                        null,
                                        jsonUser);
                        registration.checkStatusCode(response,
                                        ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                        registration.checkStatusMessage(response,
                                        ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
                }
        }

        @Test
        public void shouldntRegisterWithInvalidEmailMissingAtSign() {
                userData.put(keys[1], General.generateRandomString(8) + ".com");
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithInvalidEmailMissingDomain() {
                userData.put(keys[1], General.generateRandomString(8) + "@test");
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithInvalidEmailOnlyAtSign() {
                userData.put(keys[1], "@");
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithInvalidEmailOnlyAtSignAndDomain() {
                userData.put(keys[1], "@.com");
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithInvalidEmailMissingMailServer() {
                userData.put(keys[1], General.generateRandomString(5) + "@.com");
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithExistingEmail() {
                userData.put(keys[1], User.TEST_USER.getEmail());
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithExistingUsername() {
                userData.put(keys[0], User.TEST_USER.getUsername());
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWith256CharactersAsUsername() {
                userData.put(keys[0], General.generateRandomString(256));
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithFiveCharacterPassword() {
                userData.put(keys[2], General.generateRandomString(5));
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void ableToRegisterWithSixCharacterPassword() {
                userData.put(keys[2], General.generateRandomString(6));
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkIfUserRegistrated(response, bodyJson);
                registration.checkStatusCode(response,
                                ApiConstans.StatusAndCode.OK.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.OK.getStatusMessage());
        }

        @Test
        public void ableToRegisterWith255CharactersAsUsername() {
                userData.put(keys[0], General.generateRandomString(255));
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkIfUserRegistrated(response, bodyJson);
                registration.checkStatusCode(response,
                                ApiConstans.StatusAndCode.OK.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.OK.getStatusMessage());
        }

        @Test
        public void ableToRegisterWithAllLowerCasePassword() {
                userData.put(keys[2], General.generateRandomString(8).toLowerCase());
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkIfUserRegistrated(response, bodyJson);
                registration.checkStatusCode(response,
                                ApiConstans.StatusAndCode.OK.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.OK.getStatusMessage());
        }

        @Test
        public void successfullRegistration() {
                user = User.generateRandomUserForRegistration();
                bodyJson = JsonGenerator.generateJson(user);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                "/auth"+ApiConstans.Endpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkIfUserRegistrated(response, bodyJson);
                registration.checkStatusCode(response,
                                ApiConstans.StatusAndCode.OK.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.OK.getStatusMessage());
        }
}
