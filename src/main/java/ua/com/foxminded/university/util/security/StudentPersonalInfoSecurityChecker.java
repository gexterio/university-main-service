package ua.com.foxminded.university.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.foxminded.university.consumer.service.StudentService;

@Component
public class StudentPersonalInfoSecurityChecker {

    private final StudentService studentService;

    @Autowired
    public StudentPersonalInfoSecurityChecker(StudentService studentService) {
        this.studentService = studentService;
    }

    public boolean checkStudentId(Authentication authentication, @PathVariable Long pathId) {
        User user = (User) authentication.getPrincipal();
        if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_STUDENT"))) {
            return pathId.equals(studentService.findByEmail(user.getUsername()).getId());
        }
        return false;
    }
}
