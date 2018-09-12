package nix.exercise.two.controller;

import java.util.function.Supplier;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import nix.exercise.two.domain.exception.IllegalPhoneArticleException;
import nix.exercise.two.domain.exception.PhoneCatalogServiceException;

@ControllerAdvice
@RestController
public class ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleDownService(PhoneCatalogServiceException ex) {
        return constructResponseEntity(ex::getMessage);
    }

    @ExceptionHandler(IllegalPhoneArticleException.class)
    public ResponseEntity<ErrorDetails> handleIllegalOrder(IllegalPhoneArticleException ex) {
        return constructResponseEntity(ex::getMessage);
    }

    private ResponseEntity<ErrorDetails> constructResponseEntity(Supplier<String> stringSupplier) {
        return ResponseEntity.ok(new ErrorDetails("rejected", stringSupplier.get()));
    }

    @Data
    private class ErrorDetails {
        private final String status;
        private final String reason;
    }
}
