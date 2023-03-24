package ua.com.foxminded.university.presentation.exception.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerMethodArgumentNotValidExceptionHandler {

    Logger log = LogManager.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException e, HttpServletRequest request) {
        StringBuilder errors = new StringBuilder();
        e.getBindingResult().getAllErrors()
                .forEach(err -> {
                    if (err.getArguments() == null) {
                        errors.append(String.format("%s: %s%n",
                                "unknown",
                                err.getDefaultMessage()));
                    } else errors.append(String.format("%s: %s%n",
                            ((DefaultMessageSourceResolvable) err.getArguments()[0]).getDefaultMessage(),
                            err.getDefaultMessage()));

                });
        log.info("can't create or update entity! Cause:\n {}", errors);
        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }

}
