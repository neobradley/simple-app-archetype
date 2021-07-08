package lab.neobradley.simple.application.dto.response;

public enum StatusCode {
    SUCCESS(200, "success"),
    NO_PERMISSION(401, "User has no permission"),
    NO_PATHPAGE(404, "Path or page does not exist"),
    SERVER_ERROR(500, "Server error"),
    SERVER_CONNECTION_ERROR(504, "Server connection upstream service timeout or error"),
    JSON_PARSE_ERROR(10003, "Cannot be parse to JSON"),
    PHONE_FORMAT_ERROR(500001, "");

    private Integer status;
    private String message;

    StatusCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
