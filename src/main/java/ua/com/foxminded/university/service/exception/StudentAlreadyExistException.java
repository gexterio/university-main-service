package ua.com.foxminded.university.service.exception;

public class StudentAlreadyExistException extends ServiceException{

    private static final String  ALREADY_EXIST_WITH_ID = "Student with this ID is already exist. ID = ";

    public StudentAlreadyExistException(Long id) {
        super(ALREADY_EXIST_WITH_ID+id);
    }

    public StudentAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
