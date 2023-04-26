package ua.com.foxminded.university.consumer.exception;

public class StudentNotFoundException extends RuntimeException {

    private static final String STUDENT_NOT_FOUND_WITH_ID = "Student with ID = %d not found!";
    private static final String STUDENT_NOT_FOUND_WITH_EMAIL = "Student with email = %s not found!";


    public StudentNotFoundException(Long id) {
        super(String.format(STUDENT_NOT_FOUND_WITH_ID, id));
    }
    public StudentNotFoundException(String email) {
        super(String.format(STUDENT_NOT_FOUND_WITH_EMAIL, email));
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
