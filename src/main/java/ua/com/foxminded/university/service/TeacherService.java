package ua.com.foxminded.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
import ua.com.foxminded.university.persistance.model.LessonEntity;
import ua.com.foxminded.university.persistance.model.TeacherEntity;
import ua.com.foxminded.university.persistance.repository.LessonRepository;
import ua.com.foxminded.university.persistance.repository.TeacherRepository;
import ua.com.foxminded.university.util.modelmapper.LessonMapper;
import ua.com.foxminded.university.util.modelmapper.TeacherMapper;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final LessonMapper lessonMapper;

    @Autowired
    public TeacherService(LessonRepository lessonRepository, TeacherRepository teacherRepository, TeacherMapper teacherMapper, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.lessonMapper = lessonMapper;
    }

    public void createTeacher(TeacherDTO dto) {
        TeacherEntity entity = teacherMapper.toEntity(dto);
        teacherRepository.save(entity);
    }

    public TeacherDTO readTeacherById(Long id) {
        Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isEmpty()) {
            throw new IllegalArgumentException("teacher with id = " + id + " not found");
        }
        return teacherMapper.toDto(optionalTeacher.get());
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    public List<LessonDTO> getLessonsForTeacherForDay(Long id, ZonedDateTime day) {
        ZonedDateTime from = day.truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime to = from.plusDays(1);
        List<LessonEntity> lessons = lessonRepository.findAllLessonsByTeacherIdAndDate(id, from, to);
        return lessons.stream().map(lessonMapper::toDto).collect(Collectors.toList());
    }

    public List<LessonDTO> getLessonsForTeacherForMonth(Long id, ZonedDateTime month) {
        ZonedDateTime from = month.truncatedTo(ChronoUnit.DAYS).withDayOfMonth(1);
        ZonedDateTime to = from.plusMonths(1).truncatedTo(ChronoUnit.DAYS);
        List<LessonEntity> lessons = lessonRepository.findAllLessonsByTeacherIdAndDate(id, from, to);
        return lessons.stream().map(lessonMapper::toDto).collect(Collectors.toList());
    }

}
