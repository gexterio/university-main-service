package ua.com.foxminded.university.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ua.com.foxminded.university.service.exception.ExceptionMessage;
import ua.com.foxminded.university.service.exception.StudentAlreadyExistException;
import ua.com.foxminded.university.service.exception.StudentNotFoundException;

import java.time.ZonedDateTime;

@ControllerAdvice
public class StudentRestControllerExceptionHandler {

    @ExceptionHandler(value = StudentNotFoundException.class)
    private ResponseEntity<ExceptionMessage> handleStudentNotFoundException(StudentNotFoundException e, WebRequest request) {
        ExceptionMessage message = new ExceptionMessage(
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = StudentAlreadyExistException.class)
    private ResponseEntity<ExceptionMessage> handeStudentAlreadyExistException(StudentAlreadyExistException e, WebRequest request) {
        ExceptionMessage message = new ExceptionMessage(
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
