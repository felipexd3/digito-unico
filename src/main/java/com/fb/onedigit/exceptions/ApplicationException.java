package com.fb.onedigit.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApplicationException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    private HttpStatus httpStatus;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Throwable throwable) {
        super(throwable.getMessage(), throwable.getCause());
    }

    public ApplicationException(String message, String logMessage) {
        super(message, new Error((logMessage)));
    }

    public ApplicationException(String message, HttpStatus httpStatus) {
        super(message);
        this.setHttpStatus(httpStatus);
    }
}
