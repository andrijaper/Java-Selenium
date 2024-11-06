package automaticity_academy.constants;

public class ApiConstans {
    public static class Endpoint {
        public static final String LOGIN = "/login";
        public static final String REGISTER = "/register";
        public static final String LOGOUT = "/logout";
        public static final String CUSTOMERS = "/customers";
        public static final String BILLING_INFO = "/billing-info";
        public static final String SHIPPING_INFO = "/shipping-info";
        public static final String PRODUCTS = "/products";
        public static final String PRODUCT_IMAGE = "/product-images";
        public static final String CART = "/cart";
        public static final String DASHBOARD = "/dashboard";
    }

    public enum HttpMethods {
        GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE"), PATCH("PATCH");

        private final String method;

        HttpMethods(String method) {
            this.method = method;
        }

        public String getMethod() {
            return this.method;
        }
    };

    public enum StatusAndCode {
        OK(200, "OK"),
        CREATED(201, "Created"),
        UNAUTHORIZED(401, "Unauthorized"),
        NOT_FOUND(404, "Not Found"),
        METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
        UNPROCESSABLE(422, "Unprocessable Entity"),
        INTERNAL_SERVER_ERROR(500, "Internal Server Error");

        private final int code;
        private final String statusMessage;

        StatusAndCode(int code, String statusMessage) {
            this.code = code;
            this.statusMessage = statusMessage;
        }

        public int getCode() {
            return this.code;
        }

        public String getStatusMessage() {
            return this.statusMessage;
        }
    };

}
