package br.com.sgq.handler;


import br.com.sgq.exception.NotFoundException;
import br.com.sgq.exception.StandardError;
import br.com.sgq.exception.ValidacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(StandardError.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(e.getLocalizedMessage())
                        .error("Dado(s) não encontrado(s).")
                        .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentResponse(IllegalArgumentException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(StandardError.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getLocalizedMessage())
                        .error(HttpStatus.BAD_REQUEST.name())
                        .build());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<StandardError> validacaoException(ValidacaoException e) {
        return new ResponseEntity<>(
                StandardError.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(e.getLocalizedMessage())
                        .error("Validacao")
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}