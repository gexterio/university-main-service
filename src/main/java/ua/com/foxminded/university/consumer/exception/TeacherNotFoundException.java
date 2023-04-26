package ua.com.foxminded.university.consumer.exception;

public class TeacherNotFoundException extends RuntimeException {

    public static final String TEACHER_WITH_ID_NOT_FOUND = "Teacher with ID = %d not found!";
    public static final String TEACHER_WITH_EMAIL_NOT_FOUND = "Teacher with email = %s not found!";


    public TeacherNotFoundException(Long id) {
        super(String.format(TEACHER_WITH_ID_NOT_FOUND, id));
    }

    public TeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeacherNotFoundException(String email) {
        super(String.format(TEACHER_WITH_EMAIL_NOT_FOUND, email));
    }
}
