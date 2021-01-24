package com.fb.onedigit.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OneDigitException {
    private String code;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    public OneDigitException(String code, String title, String message) {
        this.code = code;
        this.timestamp = LocalDateTime.now();
        this.title = title;
        this.message = message;
    }
}
