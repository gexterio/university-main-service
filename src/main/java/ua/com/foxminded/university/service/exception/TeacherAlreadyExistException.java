package ua.com.foxminded.university.service.exception;

public class TeacherAlreadyExistException extends ServiceException{

    private static final String  ALREADY_EXIST_WITH_ID = "Teacher with this ID is already exist. ID = ";

    public TeacherAlreadyExistException(Long id) {
        super(ALREADY_EXIST_WITH_ID+id);
    }

    public TeacherAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
