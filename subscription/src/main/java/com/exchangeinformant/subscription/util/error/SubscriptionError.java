package com.exchangeinformant.subscription.util.error;

public class SubscriptionError{
    private String title;
    private int statusCode;
    private String message;

    private String requestId;

    public SubscriptionError () {
    }

    public SubscriptionError (String title, int statusCode, String message, String requestId) {
        this.statusCode = statusCode;
        this.message = message;
        this.title = title;
        this.requestId = requestId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public String getRequestId() {
        return requestId;
    }

}
