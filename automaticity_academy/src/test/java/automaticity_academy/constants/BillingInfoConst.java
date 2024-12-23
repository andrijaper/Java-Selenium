package automaticity_academy.constants;

public enum BillingInfoConst {

    BILLING_INFO("Jane Doe", "Visa", "1234567898765432", "123", "12/25");

    private final String cardholder;
    private final String cardType;
    private final String cardNumber;
    private final String cvv;
    private final String cardExpirationDate;

    private BillingInfoConst(String cardholder, String cardType, String cardNumber, String cvv,
            String cardExpirationDate) {
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

    public String getCvv() {
        return cvv;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public static String[] getBillingInfoKeys() {
        return new String[] { "cardholder", "card_type", "card_number", "cvv", "card_expiration_date" };
    }
}
