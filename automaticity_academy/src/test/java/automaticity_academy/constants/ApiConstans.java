package automaticity_academy.constants;

public class ApiConstans {

    public interface StatusMessages {
        String USER_CREATED = "User created successfully";
    }

    public interface urlEndpoint {
        String LOGIN = "/auth/login";
        String REGISTER = "/auth/register";
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
        OK(200, "Success"),
        CREATED(201, "Created"),
        UNAUTHENTICATED(401, "Unauthenticated"),
        NOT_FOUND(404, "Not found"),
        METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
        UNPROCESSABLE(422, "Unprocessable Content"),
        INTERNAL_SERVER_ERROR(500, "Internal server error");

        private final int code;
        private final String status;

        StatusAndCode(int code, String status) {
            this.code = code;
            this.status = status;
        }

        public int getCode() {
            return this.code;
        }

        public String getStatus() {
            return this.status;
        }
    };

}
