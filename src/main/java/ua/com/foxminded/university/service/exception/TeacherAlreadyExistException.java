package ua.com.foxminded.university.service.exception;

public class TeacherAlreadyExistException extends RuntimeException {

    private static final String TEACHER_ALREADY_EXIST_WITH_ID = "Teacher with ID = %d is already exist!";

    public TeacherAlreadyExistException(Long id) {
        super(String.format(TEACHER_ALREADY_EXIST_WITH_ID, id));
    }

    public TeacherAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
