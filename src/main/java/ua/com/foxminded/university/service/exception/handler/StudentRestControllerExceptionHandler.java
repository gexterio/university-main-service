package ua.com.foxminded.university.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.com.foxminded.university.service.exception.StudentAlreadyExistException;
import ua.com.foxminded.university.service.exception.StudentNotFoundException;

@RestControllerAdvice
public class StudentRestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = StudentNotFoundException.class)
    private ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = StudentAlreadyExistException.class)
    private ResponseEntity<Object> handeStudentAlreadyExistException(StudentAlreadyExistException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
