package ua.com.foxminded.university.service.exception;

public class TeacherNotFoundException extends ServiceException {

    private static final String NOT_FOUND_WITH_ID= "Teacher with this ID not found. ID = ";



    public TeacherNotFoundException(Long id) {
        super(NOT_FOUND_WITH_ID+id);
    }

    public TeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
