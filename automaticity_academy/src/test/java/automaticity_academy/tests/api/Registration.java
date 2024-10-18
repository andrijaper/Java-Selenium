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
        String[][] userJson;
        JSONObject bodyJson;
        JsonGenerator json;
        String[] keys = User.getKeys();

        @BeforeMethod
        public void createRandomUser() {
                registration = new RegistrationApi();
                userData = new HashMap<>();
                json = new JsonGenerator();
                bodyJson = new JSONObject();
        }

        @Test
        public void existingUserShouldntRegisterAgain() {
                userJson = User.generateUsersDataForRegistration(User.REGISTRATION_USER);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void methodNotAllowed() {
                userJson = User.generateRandomUserForRegistration();
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.PATCH.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.METHOD_NOT_ALLOWED.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.METHOD_NOT_ALLOWED.getStatusMessage());
        }

        @Test
        public void invalidUrl() {
                userJson = User.generateRandomUserForRegistration();
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER + ApiConstans.urlEndpoint.REGISTER,
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

                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
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
                        userJson = User.generateRandomUserWithInvalidValues(userData);
                        bodyJson = JsonGenerator.generateJson(userJson);
                        Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                        ApiConstans.urlEndpoint.REGISTER,
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
                        userJson = User.generateRandomUserWithInvalidValues(userData);
                        bodyJson = JsonGenerator.generateJson(userJson);
                        Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                        ApiConstans.urlEndpoint.REGISTER,
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
                        userJson = User.generateRandomUserWithInvalidValues(userData);
                        bodyJson = JsonGenerator.generateJson(userJson);
                        JSONObject jsonUser = new JSONObject(bodyJson);
                        Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                        ApiConstans.urlEndpoint.REGISTER,
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
                        userJson = User.generateRandomUserWithInvalidValues(userData);
                        bodyJson = JsonGenerator.generateJson(userJson);
                        JSONObject jsonUser = new JSONObject(bodyJson);
                        Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                        ApiConstans.urlEndpoint.REGISTER,
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
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithInvalidEmailMissingDomain() {
                userData.put(keys[1], General.generateRandomString(8) + "@test");
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithInvalidEmailOnlyAtSign() {
                userData.put(keys[1], "@");
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithInvalidEmailOnlyAtSignAndDomain() {
                userData.put(keys[1], "@.com");
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithInvalidEmailMissingMailServer() {
                userData.put(keys[1], General.generateRandomString(5) + "@.com");
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithExistingEmail() {
                userData.put(keys[1], User.TEST_USER.getEmail());
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithExistingUsername() {
                userData.put(keys[0], User.TEST_USER.getUsername());
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWith256CharactersAsUsername() {
                userData.put(keys[0], General.generateRandomString(256));
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntRegisterWithFiveCharacterPassword() {
                userData.put(keys[2], General.generateRandomString(5));
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void ableToRegisterWithSixCharacterPassword() {
                userData.put(keys[2], General.generateRandomString(6));
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
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
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
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
                userJson = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
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
                userJson = User.generateRandomUserForRegistration();
                bodyJson = JsonGenerator.generateJson(userJson);
                Response response = registration.sendApiRequest(ApiConstans.HttpMethods.POST.getMethod(),
                                ApiConstans.urlEndpoint.REGISTER,
                                null,
                                bodyJson);
                registration.checkIfUserRegistrated(response, bodyJson);
                registration.checkStatusCode(response,
                                ApiConstans.StatusAndCode.OK.getCode());
                registration.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.OK.getStatusMessage());
        }
}
