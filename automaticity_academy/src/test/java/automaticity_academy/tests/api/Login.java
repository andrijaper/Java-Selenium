package automaticity_academy.tests.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automaticity_academy.api.LoginApi;
import automaticity_academy.api.LogoutApi;
import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.User;
import automaticity_academy.utils.General;
import automaticity_academy.utils.JsonGenerator;
import io.restassured.response.Response;

public class Login {

        String[][] user;
        JSONObject bodyJson;
        LoginApi login;
        Map<String, String> userData;
        String[] keys = User.getKeys();

        @BeforeMethod
        public void initialize() {
                bodyJson = new JSONObject();
                login = new LoginApi();
                userData = new HashMap<>();
        }

        @Test
        public void shouldntLoginAsNonexisitngUser() {
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                bodyJson.remove("username");
                Response response = login.login(bodyJson, null, null);
                login.checkStatusCode(response, ApiConstans.StatusAndCode.UNAUTHORIZED.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNAUTHORIZED.getStatusMessage());
        }

        @Test
        public void shouldntLoginIfOneCredentialIsMissing() {
                for (int index = 1; index <= 2; index++) {
                        userData.put(keys[index], "");
                        user = User.generateRandomUserWithInvalidValues(userData);
                        bodyJson = JsonGenerator.generateJson(user);
                        bodyJson.remove("username");
                        Response response = login.login(bodyJson, null, null);
                        login.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                        login.checkStatusMessage(response,
                                        ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
                }
        }

        @Test
        public void methodNotAllowed() {
                user = User.generateUsersDataForLogin(User.TEST_USER);
                bodyJson = JsonGenerator.generateJson(user);
                bodyJson.remove("username");
                Response response = login.login(bodyJson, ApiConstans.HttpMethods.PATCH.getMethod(),
                                null);
                login.checkStatusCode(response, ApiConstans.StatusAndCode.METHOD_NOT_ALLOWED.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.METHOD_NOT_ALLOWED.getStatusMessage());
        }

        @Test
        public void invalidUrl() {
                user = User.generateUsersDataForLogin(User.TEST_USER);
                bodyJson = JsonGenerator.generateJson(user);
                bodyJson.remove("username");
                Response response = login.login(bodyJson, null,
                                ApiConstans.urlEndpoint.LOGIN + ApiConstans.urlEndpoint.LOGIN);
                login.checkStatusCode(response, ApiConstans.StatusAndCode.NOT_FOUND.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.NOT_FOUND.getStatusMessage());
        }

        @Test
        public void shouldntLoginWithEmptyFields() {
                userData.put(keys[1], "");
                userData.put(keys[2], "");

                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                bodyJson.remove("username");
                Response response = login.login(bodyJson, null, null);
                login.checkStatusCode(response, ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
        }

        @Test
        public void shouldntBeAbleToLoginWithObjectInOneField() {
                String[][] objectToConvert = User.generateRandomUserForRegistration();
                JSONObject object = JsonGenerator.generateJson(objectToConvert);
                for (int index = 1; index <= 2; index++) {
                        userData.put(keys[index], object.toString());
                        userData.remove("username");
                        user = User.generateRandomUserWithInvalidValues(userData);
                        bodyJson = JsonGenerator.generateJson(user);
                        bodyJson.remove("username");
                        Response response = login.login(bodyJson, null, null);
                        login.checkStatusCode(response,
                                        ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                        login.checkStatusMessage(response,
                                        ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
                }
        }

        @Test
        public void shouldntBeAbleToRegisterWithArraytInOneField() {
                for (int index = 1; index <= 2; index++) {
                        userData.put(keys[index], keys.toString());
                        user = User.generateRandomUserWithInvalidValues(userData);
                        bodyJson = JsonGenerator.generateJson(user);
                        bodyJson.remove("username");
                        Response response = login.login(bodyJson, null, null);
                        login.checkStatusCode(response,
                                        ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
                        login.checkStatusMessage(response,
                                        ApiConstans.StatusAndCode.UNPROCESSABLE.getStatusMessage());
                }
        }

        @Test
        public void shouldntLoginWithInvalidPassword() {
                userData.put(keys[1], User.TEST_USER.getEmail());
                userData.put(keys[2], General.generateRandomString(8));
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                bodyJson.remove("username");
                Response response = login.login(bodyJson, null, null);
                login.checkStatusCode(response, ApiConstans.StatusAndCode.UNAUTHORIZED.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNAUTHORIZED.getStatusMessage());
        }

        @Test
        public void shouldntLoginWithPasswordToUppercase() {
                userData.put(keys[1], User.TEST_USER.getEmail());
                userData.put(keys[2], User.TEST_USER.getPassword().toUpperCase());
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                bodyJson.remove("username");
                Response response = login.login(bodyJson, null, null);
                login.checkStatusCode(response, ApiConstans.StatusAndCode.UNAUTHORIZED.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNAUTHORIZED.getStatusMessage());
        }

        @Test
        public void shouldntLoginWithPasswordHasWhitespaceAtTheEnd() {
                userData.put(keys[1], User.TEST_USER.getEmail());
                userData.put(keys[2], User.TEST_USER.getPassword() + " ");
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                bodyJson.remove("username");
                Response response = login.login(bodyJson, null, null);
                login.checkStatusCode(response, ApiConstans.StatusAndCode.UNAUTHORIZED.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNAUTHORIZED.getStatusMessage());
        }

        @Test
        public void shouldntLoginWithValidPasswordInArrayAsNewPassword() {
                String[] passwordInArray = { User.TEST_USER.getPassword() };
                userData.put(keys[1], User.TEST_USER.getEmail());
                userData.put(keys[2], passwordInArray.toString());
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                bodyJson.remove("username");
                Response response = login.login(bodyJson, null, null);
                login.checkStatusCode(response, ApiConstans.StatusAndCode.UNAUTHORIZED.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.UNAUTHORIZED.getStatusMessage());
        }

        @Test
        public void shouldLoginWithEmailHasWhitespaceAtTheEnd() {
                userData.put(keys[1], User.TEST_USER.getEmail() + " ");
                userData.put(keys[2], User.TEST_USER.getPassword());
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                bodyJson.remove("username");
                Response response = login.login(bodyJson, null, null);
                bodyJson.put("email", User.TEST_USER.getEmail());
                login.checkIfUserIdLoggedIn(response, bodyJson);
                login.checkStatusCode(response,
                                ApiConstans.StatusAndCode.OK.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.OK.getStatusMessage());
        }

        @Test
        public void shouldLoginWithEmailHasWhitespaceAtTheBeginning() {
                userData.put(keys[1], " " + User.TEST_USER.getEmail());
                userData.put(keys[2], User.TEST_USER.getPassword());
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                bodyJson.remove("username");
                Response response = login.login(bodyJson, null, null);
                bodyJson.put("email", User.TEST_USER.getEmail());
                login.checkIfUserIdLoggedIn(response, bodyJson);
                login.checkStatusCode(response,
                                ApiConstans.StatusAndCode.OK.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.OK.getStatusMessage());
        }

        @Test
        public void shouldLoginWithEmailToUppercase() {
                userData.put(keys[1], User.TEST_USER.getEmail().toUpperCase());
                userData.put(keys[2], User.TEST_USER.getPassword());
                user = User.generateRandomUserWithInvalidValues(userData);
                bodyJson = JsonGenerator.generateJson(user);
                bodyJson.remove("username");
                Response response = login.login(bodyJson, null, null);
                bodyJson.put("email", User.TEST_USER.getEmail());
                login.checkIfUserIdLoggedIn(response, bodyJson);
                login.checkStatusCode(response,
                                ApiConstans.StatusAndCode.OK.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.OK.getStatusMessage());
        }

        @Test
        public void successfullLogin() {
                user = User.generateUsersDataForLogin(User.TEST_USER);
                bodyJson = JsonGenerator.generateJson(user);
                Response response = login.login(bodyJson, null, null);
                login.checkIfUserIdLoggedIn(response, bodyJson);
                login.checkStatusCode(response,
                                ApiConstans.StatusAndCode.OK.getCode());
                login.checkStatusMessage(response,
                                ApiConstans.StatusAndCode.OK.getStatusMessage());
        }

        @AfterClass
        public void logout() {
                LogoutApi logout = new LogoutApi();
                logout.logout();
        }
}
