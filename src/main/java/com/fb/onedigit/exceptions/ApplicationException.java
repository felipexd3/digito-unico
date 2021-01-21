package com.fb.onedigit.exceptions;

public class ApplicationException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Throwable throwable) {
        super(throwable.getMessage(), throwable.getCause());
    }

    public ApplicationException(String message, String logMessage) {
        super(message, new Error((logMessage)));
    }
}
