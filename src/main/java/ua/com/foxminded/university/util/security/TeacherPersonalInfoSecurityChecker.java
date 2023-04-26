package ua.com.foxminded.university.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.foxminded.university.consumer.service.TeacherService;

@Component
public class TeacherPersonalInfoSecurityChecker {

    private final TeacherService teacherService;

    @Autowired
    public TeacherPersonalInfoSecurityChecker(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    public boolean checkTeacherId(Authentication authentication, @PathVariable Long pathId) {
        User user = (User) authentication.getPrincipal();
        if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_TEACHER"))) {
            return pathId.equals(teacherService.findByEmail(user.getUsername()).getId());
        }
        return false;
    }
}
