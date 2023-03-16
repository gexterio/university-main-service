package ua.com.foxminded.university.service.exception;

public class StudentNotFoundException extends ServiceException {

    private static final String NOT_FOUND_WITH_ID = "Student with this ID not found. ID = ";

    public StudentNotFoundException(Long id) {
        super(NOT_FOUND_WITH_ID + id);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
