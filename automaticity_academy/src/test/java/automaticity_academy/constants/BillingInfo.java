package automaticity_academy.constants;

public enum BillingInfo {

    BILLING_INFO("Jane Doe", "Visa", "1234567898765432", 123, "12/25");

    private final String cardholder;
    private final String cardType;
    private final String cardNumber;
    private final int cvv;
    private final String cardExpirationDate;

    private BillingInfo(String cardholder, String cardType, String cardNumber, int cvv, String cardExpirationDate) {
        this.cardholder = cardholder;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.cardExpirationDate = cardExpirationDate;
    }

    public String getCardholder() {
        return cardholder;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public static String[] getBillingInfoKeys() {
        return new String[] { "cardholder", "card_type", "card_number", "cvv", "card_expiration_date" };
    }
}
