package ua.com.foxminded.university.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.com.foxminded.university.service.exception.TeacherAlreadyExistException;
import ua.com.foxminded.university.service.exception.TeacherNotFoundException;

@RestControllerAdvice
public class TeacherRestControllerExceptionHandler {

    @ExceptionHandler(TeacherNotFoundException.class)
    private ResponseEntity<Object> handleTeacherNotFoundException(TeacherNotFoundException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TeacherAlreadyExistException.class)
    private ResponseEntity<Object> handleTeacherAlreadyExistException(TeacherAlreadyExistException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
