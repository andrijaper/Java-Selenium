package automaticity_academy.constants;

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

    public static String[][] generateUsersDataForLogin(User user) {
        return new String[][] { { "email", user.getEmail() }, { "password", user.getPassword() } };
    }

    public static String[][] generateUsersDataForRegistration(User user) {
        return new String[][] { { "username", user.getUsername() }, { "email", user.getEmail() },
                { "password", user.getPassword() } };
    }

    public static String[][] generateRandomUserForRegistration() {
        return new String[][] { { "username", General.generateRandomString(8) },
                { "email", General.generateRandomString(8) + "@test.com" },
                { "password", "Johndoe@123" } };
    }
}
