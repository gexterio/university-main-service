package ua.com.foxminded.university.consumer.exception;

import java.time.ZonedDateTime;

public class ExceptionMessage {
    private final int statusCode;
    private final ZonedDateTime timestamp;
    private final String message;
    private final String description;

    public ExceptionMessage(int statusCode, ZonedDateTime timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
