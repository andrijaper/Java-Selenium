package automaticity_academy.tests.api.customers;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import automaticity_academy.api.LoginApi;
import automaticity_academy.api.LogoutApi;
import automaticity_academy.api.RegistrationApi;
import automaticity_academy.api.customersApi.CustomerApi;
import automaticity_academy.constants.ApiConstants;
import automaticity_academy.constants.User;
import automaticity_academy.utils.General;
import automaticity_academy.utils.JsonGenerator;
import io.restassured.response.Response;

public class Customer {

    String token;
    CustomerApi customer;
    String id;
    RegistrationApi registration;
    String[] keys = User.getKeys();
    LoginApi login;

    @BeforeClass
    public void initialize() {
        customer = new CustomerApi();
        registration = new RegistrationApi();
        login = new LoginApi();
    }

    @BeforeMethod
    public void login() {
        String[][] user = User.generateUsersDataForLogin(User.TEST_USER);
        JSONObject bodyJson = JsonGenerator.generateJson(user);
        Response response = login.login(bodyJson, null, null);
        token = login.getToken(response);
        id = login.getUsersId(response);
    }

    @Test
    public void shouldntUpdateCustomersDataWithExistingCustomersData() {
        String[][] user = User.generateRandomUserForRegistration();
        JSONObject bodyJson = JsonGenerator.generateJson(user);
        Response registrationResponse = registration.registerUser(bodyJson);
        registration.checkIfUserRegistrated(registrationResponse, bodyJson);
        registration.checkStatusCode(registrationResponse,
            ApiConstants.StatusAndCode.OK.getCode());
        registration.checkStatusMessage(registrationResponse,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
        id = registration.getUsersId(registrationResponse);

        String[][] newUserData = User.generateUsersData(User.TEST_USER);
        JSONObject bodyNewData = JsonGenerator.generateJson(newUserData);
        bodyNewData.remove(keys[2]);
        Response updatedUserResponse = customer.updateUsersData(bodyNewData, id, token);
        customer.checkStatusCode(updatedUserResponse,
            ApiConstants.StatusAndCode.INTERNAL_SERVER_ERROR.getCode());
        customer.checkStatusMessage(updatedUserResponse,
            ApiConstants.StatusAndCode.INTERNAL_SERVER_ERROR.getStatusMessage());
    }

    @Test
    public void shouldntUpdateCustomersDataWithInavlidMethod() {
        String[][] user = User.generateRandomUserForRegistration();
        JSONObject bodyJson = JsonGenerator.generateJson(user);
        Response registrationResponse = registration.registerUser(bodyJson);
        registration.checkIfUserRegistrated(registrationResponse, bodyJson);
        registration.checkStatusCode(registrationResponse,
            ApiConstants.StatusAndCode.OK.getCode());
        registration.checkStatusMessage(registrationResponse,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
        id = registration.getUsersId(registrationResponse);

        String[][] newUserData = User.generateRandomUserForRegistration();
        JSONObject bodyNewData = JsonGenerator.generateJson(newUserData);
        bodyNewData.remove(keys[2]);
        Response updatedUserResponse = customer.sendApiRequest(ApiConstants.HttpMethods.PATCH.getMethod(),
            ApiConstants.Endpoint.CUSTOMERS + "/" + id, token, bodyNewData);
        customer.checkStatusCode(updatedUserResponse, ApiConstants.StatusAndCode.METHOD_NOT_ALLOWED.getCode());
        customer.checkStatusMessage(updatedUserResponse,
            ApiConstants.StatusAndCode.METHOD_NOT_ALLOWED.getStatusMessage());
    }

    @Test
    public void shouldntUpdateCustomersDoBAsMinor() {
        String[][] user = User.generateRandomUserForRegistration();
        JSONObject bodyJson = JsonGenerator.generateJson(user);
        Response registrationResponse = registration.registerUser(bodyJson);
        registration.checkIfUserRegistrated(registrationResponse, bodyJson);
        registration.checkStatusCode(registrationResponse,
            ApiConstants.StatusAndCode.OK.getCode());
        registration.checkStatusMessage(registrationResponse,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
        id = registration.getUsersId(registrationResponse);

        String[][] newUserData = User.generateRandomUserForRegistration();
        JSONObject bodyNewData = JsonGenerator.generateJson(newUserData);
        bodyNewData.remove(keys[2]);
        String dateOfBirth = General.generateRandomDateBetween(LocalDate.of(2020, 1, 1), LocalDate.now())
            .toString();

        bodyNewData.put("date_of_birth", dateOfBirth);
        Response updatedUserResponse = customer.updateUsersData(bodyNewData, id, token);
        customer.checkStatusCode(updatedUserResponse,
            ApiConstants.StatusAndCode.UNPROCESSABLE.getCode());
        customer.checkStatusMessage(updatedUserResponse,
            ApiConstants.StatusAndCode.UNPROCESSABLE.getStatusMessage());
    }

    @Test
    public void shouldntUpdateCustomersDoBIfMonthIsWrittenWithLetters() {
        String[][] user = User.generateRandomUserForRegistration();
        JSONObject bodyJson = JsonGenerator.generateJson(user);
        Response registrationResponse = registration.registerUser(bodyJson);
        registration.checkIfUserRegistrated(registrationResponse, bodyJson);
        registration.checkStatusCode(registrationResponse,
            ApiConstants.StatusAndCode.OK.getCode());
        registration.checkStatusMessage(registrationResponse,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
        id = registration.getUsersId(registrationResponse);

        String[][] newUserData = User.generateRandomUserForRegistration();
        JSONObject bodyNewData = JsonGenerator.generateJson(newUserData);
        bodyNewData.remove(keys[2]);
        bodyNewData.put("date_of_birth", "2000-January-01");
        Response updatedUserResponse = customer.updateUsersData(bodyNewData, id, token);
        customer.checkStatusCode(updatedUserResponse,
            ApiConstants.StatusAndCode.UNPROCESSABLE.getCode());
        customer.checkStatusMessage(updatedUserResponse,
            ApiConstants.StatusAndCode.UNPROCESSABLE.getStatusMessage());
    }

    @Test
    public void shouldntLogginWithPrevouslyDeletedUser() {
        String[][] user = User.generateRandomUserForRegistration();
        JSONObject bodyJson = JsonGenerator.generateJson(user);
        Response registrationResponse = registration.registerUser(bodyJson);
        registration.checkIfUserRegistrated(registrationResponse, bodyJson);
        registration.checkStatusCode(registrationResponse,
            ApiConstants.StatusAndCode.OK.getCode());
        registration.checkStatusMessage(registrationResponse,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
        id = registration.getUsersId(registrationResponse);
        Response deleteResponse = customer.deleteCustomer(id, token);
        customer.checkStatusCode(deleteResponse,
            ApiConstants.StatusAndCode.OK.getCode());
        customer.checkStatusMessage(deleteResponse,
            ApiConstants.StatusAndCode.OK.getStatusMessage());

        Response loginResponse = login.login(bodyJson, null, null);

        login.checkStatusCode(loginResponse,
            ApiConstants.StatusAndCode.INTERNAL_SERVER_ERROR.getCode());
        login.checkStatusMessage(loginResponse,
            ApiConstants.StatusAndCode.INTERNAL_SERVER_ERROR.getStatusMessage());
    }

    @Test
    public void shouldntDeleteNonexistingUser() {
        Response deleteResponse = customer.deleteCustomer("999999", token);
        customer.checkStatusCode(deleteResponse,
            ApiConstants.StatusAndCode.NOT_FOUND.getCode());
        customer.checkStatusMessage(deleteResponse,
            ApiConstants.StatusAndCode.NOT_FOUND.getStatusMessage());
    }

    @Test
    public void shouldListAllCustomers() {
        Response response = customer.getAllCustomers(token);
        customer.checkStatusCode(response, ApiConstants.StatusAndCode.OK.getCode());
        customer.checkStatusMessage(response,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
        Assert.assertTrue(response.jsonPath().getList("customers").size() >= 0);
    }

    @Test
    public void shouldListOneCustomer() {
        Response response = customer.getCustomerById(id, token);
        customer.checkStatusCode(response, ApiConstants.StatusAndCode.OK.getCode());
        customer.checkStatusMessage(response,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
    }

    @Test
    public void shouldDeleteCustomer() {
        String[][] user = User.generateRandomUserForRegistration();
        JSONObject bodyJson = JsonGenerator.generateJson(user);
        Response registrationResponse = registration.registerUser(bodyJson);
        registration.checkIfUserRegistrated(registrationResponse, bodyJson);
        registration.checkStatusCode(registrationResponse,
            ApiConstants.StatusAndCode.OK.getCode());
        registration.checkStatusMessage(registrationResponse,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
        id = registration.getUsersId(registrationResponse);
        Response deleteResponse = customer.deleteCustomer(id, token);
        customer.checkStatusCode(deleteResponse,
            ApiConstants.StatusAndCode.OK.getCode());
        customer.checkStatusMessage(deleteResponse,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
    }

    @Test
    public void shouldUpdateCustomersData() {
        String[][] user = User.generateRandomUserForRegistration();
        JSONObject bodyJson = JsonGenerator.generateJson(user);
        Response registrationResponse = registration.registerUser(bodyJson);
        registration.checkIfUserRegistrated(registrationResponse, bodyJson);
        registration.checkStatusCode(registrationResponse,
            ApiConstants.StatusAndCode.OK.getCode());
        registration.checkStatusMessage(registrationResponse,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
        id = registration.getUsersId(registrationResponse);

        String[][] newUserData = User.generateRandomUserForRegistration();
        JSONObject bodyNewData = JsonGenerator.generateJson(newUserData);
        bodyNewData.remove(keys[2]);
        Response updatedUserResponse = customer.updateUsersData(bodyNewData, id, token);
        customer.checkStatusCode(updatedUserResponse,
            ApiConstants.StatusAndCode.OK.getCode());
        customer.checkStatusMessage(updatedUserResponse,
            ApiConstants.StatusAndCode.OK.getStatusMessage());
        customer.checkIsUserUpdated(bodyNewData, id, token);
    }

    @AfterClass
    public void logout() {
        LogoutApi logout = new LogoutApi();
        logout.logout(token);
    }
}
