package ua.com.foxminded.university.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.foxminded.university.consumer.dto.TransactionDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService {

    public static final String ENDPOINT_FOR_USER = "http://localhost:8081/api/v2/users/%s/transactions";
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserService userService;

    @Autowired
    public TransactionService(StudentService studentService, TeacherService teacherService, UserService userService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.userService = userService;
    }

    public List<TransactionDTO> getTransactionsForStudent(Long id) {
        Long userId = userService.getIdByUsername(studentService.findById(id).getEmail());
        String url = String.format(ENDPOINT_FOR_USER, userId);
        try {
            return Arrays.asList(new RestTemplate().getForObject(new URI(url), TransactionDTO[].class));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public List<TransactionDTO> getTransactionsForTeacher(Long id) {
        Long userId = userService.getIdByUsername(teacherService.findById(id).getEmail());
        String url = String.format(ENDPOINT_FOR_USER, userId);
        try {
            return Arrays.asList(new RestTemplate().getForObject(new URI(url), TransactionDTO[].class));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
