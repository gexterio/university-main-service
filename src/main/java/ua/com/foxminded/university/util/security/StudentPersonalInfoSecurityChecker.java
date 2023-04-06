package ua.com.foxminded.university.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.foxminded.university.persistance.repository.StudentRepository;

@Component
public class StudentPersonalInfoSecurityChecker {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentPersonalInfoSecurityChecker(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean checkStudentId(Authentication authentication, @PathVariable Long pathId) {
        User user = (User) authentication.getPrincipal();
        if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        } else if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_STUDENT"))) {
            return pathId.equals(studentRepository.findByEmail(user.getUsername()).getId());
        }
        return false;
    }
}
