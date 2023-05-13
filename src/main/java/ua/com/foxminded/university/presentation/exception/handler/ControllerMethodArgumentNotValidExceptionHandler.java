package ua.com.foxminded.university.presentation.exception.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerMethodArgumentNotValidExceptionHandler {

    Logger log = LogManager.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder msg = new StringBuilder();
        e.getBindingResult().getAllErrors()
                .stream()
                .map(FieldError.class::cast)
                .forEach(er -> msg.append(String.format("%s = %s. %s%n",
                        er.getField(),
                        er.getRejectedValue(),
                        er.getDefaultMessage())).append("\n"));
        log.info("can't create or update entity! Cause:\n {}", msg);
        return new ResponseEntity<>(msg.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder msg = new StringBuilder();
        e.getConstraintViolations()
                .forEach(ex ->
                        msg.append(String.format("value = %s %s",
                                ex.getInvalidValue(),
                                ex.getMessage())).append("\n"));
        log.info("can't get entity! Cause:\n{}", msg);
        return new ResponseEntity<>(msg.toString(), HttpStatus.BAD_REQUEST);
    }

}
