package ua.com.foxminded.university.persistance.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teachers")
public class TeacherEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iD;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private Byte age;
    @Column(name = "grade")
    private String grade;
    @Column(name = "experience")
    private Integer experience;
    @Column(name = "email")
    private String email;
    @Column(name = "faculty_id")
    private Long facultyID;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<LessonEntity> lessons = new ArrayList<>();

    public TeacherEntity() {
    }

    public TeacherEntity(TeacherEntityBuilder builder) {
        this.iD = builder.iD;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.grade = builder.grade;
        this.experience = builder.experience;
        this.email = builder.email;
        this.facultyID = builder.facultyID;
        this.lessons=builder.lessons;
    }

    public static class TeacherEntityBuilder {
        private Long iD;
        private final String firstName;
        private final String lastName;
        private Byte age;
        private String grade;
        private Integer experience;
        private String email;
        private Long facultyID;
        private List<LessonEntity> lessons = new ArrayList<>();


        public TeacherEntityBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public TeacherEntityBuilder setID(Long iD) {
            this.iD = iD;
            return this;
        }

        public TeacherEntityBuilder setAge(Byte age) {
            this.age = age;
            return this;
        }

        public TeacherEntityBuilder setExperience(Integer experience) {
            this.experience = experience;
            return this;
        }

        public TeacherEntityBuilder setGrade(String grade) {
            this.grade = grade;
            return this;
        }

        public TeacherEntityBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public TeacherEntityBuilder setFacultyId(Long facultyID) {
            this.facultyID = facultyID;
            return this;
        }

        public TeacherEntityBuilder addLesson(LessonEntity lesson) {
            lessons.add(lesson);
            return this;
        }

        public TeacherEntity build() {
            return new TeacherEntity(this);
        }
    }

    public Long getID() {
        return iD;
    }

    public void setID(Long iD) {
        this.iD = iD;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Long facultyID) {
        this.facultyID = facultyID;
    }

    public List<LessonEntity> getLessons() {
        return lessons;
    }

    public void addLesson(LessonEntity lesson) {
        lessons.add(lesson);
    }

    public void removeLesson(LessonEntity lesson) {
        lessons.remove(lesson);
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "iD=" + iD +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", grade='" + grade + '\'' +
                ", experience=" + experience +
                ", email='" + email + '\'' +
                ", facultyID=" + facultyID +
                ", lessons=" + lessons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity that = (TeacherEntity) o;
        return age == that.age && experience == that.experience && Objects.equals(iD, that.iD) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && Objects.equals(grade, that.grade) && Objects.equals(email, that.email) && Objects.equals(facultyID, that.facultyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, firstName, lastName, age, grade, experience, email, facultyID);
    }
}
