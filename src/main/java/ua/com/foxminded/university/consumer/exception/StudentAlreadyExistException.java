package ua.com.foxminded.university.consumer.exception;

public class StudentAlreadyExistException extends RuntimeException {

    private static final String STUDENT_ALREADY_EXIST_WITH_ID = "Student with ID = %d is already exist!";

    public StudentAlreadyExistException(Long id) {
        super(String.format(STUDENT_ALREADY_EXIST_WITH_ID, id));
    }

    public StudentAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
