package ua.com.foxminded.university.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;
import ua.com.foxminded.university.config.condition.DataGeneratorCondition;
import ua.com.foxminded.university.persistance.model.LessonEntity;
import ua.com.foxminded.university.persistance.model.StudentEntity;
import ua.com.foxminded.university.persistance.model.TeacherEntity;
import ua.com.foxminded.university.persistance.repository.LessonRepository;
import ua.com.foxminded.university.persistance.repository.StudentRepository;
import ua.com.foxminded.university.persistance.repository.TeacherRepository;
import ua.com.foxminded.university.util.FileParser;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

@Service
@Conditional(DataGeneratorCondition.class)
public class DataGenerator {

    private final LessonRepository lessonRepository;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final FileParser parser;

    private Random random;

    private List<String> firstNames;
    private List<String> lastNames;
    private List<String> grades;
    private List<String> lessons;

    @Value("${first-name-file-path}")
    private String firstNamesFilePath;
    @Value("${last-name-file-path}")
    private String lastNamesFilePath;
    @Value("${grade-file-path}")
    private String gradesFilePath;
    @Value(("${lesson-name-file-path}"))
    private String lessonsFilePath;
    @Value("${generated-student-count}")
    private int studentCount;
    @Value("${generated-teacher-count}")
    private int teacherCount;
    @Value("${generated-lesson--count}")
    private int lessonCount;
    private static final int GROUP_COUNT = 8;
    private static final int FACULTY_COUNT = 4;
    private static final int SUBJECT_COUNT = 6;

    public DataGenerator(LessonRepository lessonRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, FileParser parser) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.parser = parser;
    }

    @PostConstruct
    public void run() {
        random = new Random();
        this.firstNames = parser.parse(firstNamesFilePath);
        this.lastNames = parser.parse(lastNamesFilePath);
        this.grades = parser.parse(gradesFilePath);
        this.lessons = parser.parse(lessonsFilePath);
        saveStudents(studentCount);
        saveTeachers(teacherCount);
        saveLessons(lessonCount);
    }

    private void saveTeachers(int count) {
        for (int i = 0; i < count; i++) {
            TeacherEntity teacherEntity = generateTeacher();
            teacherRepository.save(teacherEntity);
        }
    }


    private void saveStudents(int count) {
        for (int i = 0; i < count; i++) {
            StudentEntity studentEntity = generateStudent();
            studentRepository.save(studentEntity);
        }
    }

    private void saveLessons(int count) {
        for (int i = 0; i < count; i++) {
            LessonEntity lessonEntity = generateLesson();
            lessonRepository.save(lessonEntity);
        }
    }

    private StudentEntity generateStudent() {
        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String lastName = lastNames.get(random.nextInt(lastNames.size()));
        String email = firstName + lastName + random.nextInt(999) + "@email.com";
        Byte age = (byte) (random.nextInt(20) + 18);
        Long groupId = (long) (random.nextInt(GROUP_COUNT) + 1);
        Byte course = (byte) (random.nextInt(3) + 1);
        return new StudentEntity.StudentEntityBuilder(firstName, lastName)
                .setAge(age).setEmail(email)
                .setGroupID(groupId)
                .setCourse(course)
                .build();
    }

    private TeacherEntity generateTeacher() {
        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String lastName = lastNames.get(random.nextInt(lastNames.size()));
        String email = firstName + lastName + random.nextInt(999) + "@email.com";
        Byte age = (byte) (random.nextInt(70) + 18);
        Long facultyId = (long) (random.nextInt(FACULTY_COUNT) + 1);
        Integer experience = random.nextInt(age - 17) + 1;
        String grade = grades.get(random.nextInt(grades.size()));
        return new TeacherEntity.TeacherEntityBuilder(firstName, lastName)
                .setAge(age)
                .setEmail(email)
                .setExperience(experience)
                .setFacultyId(facultyId)
                .setGrade(grade)
                .build();
    }

    private LessonEntity generateLesson() {
        String name = lessons.get(random.nextInt(lessons.size()));
        Integer duration = (random.nextInt(4) + 1) * 30;
        ZonedDateTime startTime = ZonedDateTime.now().plusHours(random.nextInt(300));
        Long groupId = (long) (random.nextInt(GROUP_COUNT) + 1);
        Long subjectId = (long) (random.nextInt(SUBJECT_COUNT) + 1);
        Long teacherId = (long) (random.nextInt(teacherCount) + 1);
        return new LessonEntity.LessonEntityBuilder(name, subjectId)
                .setDuration(duration)
                .setStartTime(startTime)
                .setGroupId(groupId)
                .setTeacherId(teacherId)
                .build();
    }

}
