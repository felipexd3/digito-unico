package com.fb.onedigit.exceptions.handler;

import com.fb.onedigit.exceptions.ApplicationException;
import com.fb.onedigit.messages.OneDigitException;
import com.fb.onedigit.messages.Messages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<OneDigitException> handleApplicationException(ApplicationException ex) {
        var messages = this.deserializedMessage(ex.getMessage());
        var status = Objects.nonNull(ex.getHttpStatus()) ? ex.getHttpStatus() : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new OneDigitException(messages.get(0), messages.get(1), messages.get(2), ex));
    }

    @Override
    @ResponseBody
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var fields = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.toList());
        var messages = deserializedMessage(Messages.MALFORMED_JSON_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OneDigitException(messages.get(0), messages.get(1), messages.get(2) + " " + fields, ex));
    }

    private List<String> deserializedMessage(String message) {
        return Arrays.asList(message.split("#").clone());
    }
}
