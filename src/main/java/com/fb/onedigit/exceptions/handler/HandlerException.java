package com.fb.onedigit.exceptions.handler;

import com.fb.onedigit.exceptions.handler.OneDigitException;
import com.fb.onedigit.messages.Messages;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Component
@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @Override
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var fields = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.toList());
        var message = Messages.MALFORMED_JSON_REQUEST.split("#");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OneDigitException(message[0], message[1], message[2] + " " + fields, ex));
    }
}
