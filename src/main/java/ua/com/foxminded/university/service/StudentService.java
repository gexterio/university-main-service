package ua.com.foxminded.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.consumer.dto.StudentDTO;
import ua.com.foxminded.university.persistance.model.LessonEntity;
import ua.com.foxminded.university.persistance.model.StudentEntity;
import ua.com.foxminded.university.persistance.repository.LessonRepository;
import ua.com.foxminded.university.persistance.repository.StudentRepository;
import ua.com.foxminded.university.util.modelmapper.LessonMapper;
import ua.com.foxminded.university.util.modelmapper.StudentMapper;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final LessonMapper lessonMapper;

    @Autowired
    public StudentService(LessonRepository lessonRepository, StudentRepository studentRepository, StudentMapper studentMapper, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.lessonMapper = lessonMapper;
    }

    public void createStudent(StudentDTO dto) {
        StudentEntity entity = studentMapper.toEntity(dto);
        studentRepository.save(entity);
    }

    public StudentDTO readStudentById(Long id) {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new IllegalArgumentException("student with id = " + id + " not found");
        }
        return studentMapper.toDto(optionalStudent.get());
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public List<LessonDTO> getLessonsForStudentForDay(Long id, ZonedDateTime day) {
        ZonedDateTime from = day.truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime to = from.plusDays(1);
        List<LessonEntity> lessons = lessonRepository.findAllLessonsByStudentIdAndDate(id, from, to);
        return lessons.stream().map(lessonMapper::toDto).collect(Collectors.toList());
    }

    public List<LessonDTO> getLessonsForStudentForMonth(Long id, ZonedDateTime month) {
        ZonedDateTime from = month.truncatedTo(ChronoUnit.DAYS).withDayOfMonth(1);
        ZonedDateTime to = from.plusMonths(1);
        List<LessonEntity> lessons = lessonRepository.findAllLessonsByStudentIdAndDate(id, from, to);
        return lessons.stream().map(lessonMapper::toDto).collect(Collectors.toList());
    }
}
