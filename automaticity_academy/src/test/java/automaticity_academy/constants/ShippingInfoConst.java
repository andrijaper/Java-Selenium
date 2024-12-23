package automaticity_academy.constants;

public enum ShippingInfoConst {

    SHIPPING_INFO("Jane", "Doe", "jane@test.com", "123 Main St", "+1234567890", "Novi Sad", 12345, "SRB");

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String streetAndNumber;
    private final String phoneNumber;
    private final String city;
    private final int postalCode;
    private final String country;

    private ShippingInfoConst(String firstName, String lastName, String email, String streetAndNumber,
            String phoneNumber, String city, int postalCode, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.streetAndNumber = streetAndNumber;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public static String[] getShippingInfoKeys() {
        return new String[] { "first_name", "last_name", "email", "street_and_number", "phone_number", "city",
                "postal_code", "country" };
    }
}
