package com.fb.onedigit.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class OneDigitException {
    private String code;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String detailedException;

    public OneDigitException(String code, String title, String message, Throwable error) {
        this.code = code;
        this.timestamp = LocalDateTime.now();
        this.title = title;
        this.message = message;
        this.detailedException = error.toString();
    }
}
