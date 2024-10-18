package automaticity_academy.constants;

import java.util.Map;

import automaticity_academy.utils.General;

public enum User {

    TEST_USER("JaneDoe", "jane@test.com", "Janedoe@123"),
    REGISTRATION_USER("JohnDoe", "john@test.com", "Johndoe@123");

    private final String username;
    private final String email;
    private final String password;

    User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public static String[] getKeys() {
        return new String[] { "username", "email", "password" };
    }

    public static String[][] generateUsersDataForLogin(User user) {
        return new String[][] { { getKeys()[1], user.getEmail() }, { getKeys()[2], user.getPassword() } };
    }

    public static String[][] generateUsersDataForRegistration(User user) {
        return new String[][] { { getKeys()[0], user.getUsername() }, { getKeys()[1], user.getEmail() },
                { getKeys()[2], user.getPassword() } };
    }

    public static String[][] generateRandomUserForRegistration() {
        return new String[][] { { getKeys()[0], General.generateRandomString(8) },
                { getKeys()[1], General.generateRandomString(8) + "@test.com" },
                { getKeys()[2], REGISTRATION_USER.getPassword() } };
    }

    public static String[][] generateRandomUserWithInvalidValues(Map<String, String> newValues) {
        String[][] user = new String[3][2];

        user[0][0] = getKeys()[0];
        user[0][1] = General.generateRandomString(8);

        user[1][0] = getKeys()[1];
        user[1][1] = General.generateRandomString(8) + "@test.com";

        user[2][0] = getKeys()[2];
        user[2][1] = REGISTRATION_USER.getPassword();

        for (Map.Entry<String, String> entry : newValues.entrySet()) {
            String key = entry.getKey();
            String newValue = entry.getValue();

            switch (key) {
                case "username":
                    user[0][1] = newValue;
                    break;
                case "email":
                    user[1][1] = newValue;
                    break;
                case "password":
                    user[2][1] = newValue;
                    break;
            }
        }
        return user;
    }

}
