package ua.com.foxminded.university.consumer.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import ua.com.foxminded.university.util.validation.AdultTeacher;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

@Schema(description = "Teacher entity")
public class TeacherDTO {

    @Schema(description = "The id of the Teacher", example = "123")
    private Long id;

    @Schema(description = "The first name of the Teacher", minimum = "3", maximum = "64", example = "Nik")
    @NotBlank
    @Size(min = 3, max = 32)
    private String firstName;

    @Schema(description = "The last name of the Teacher", minimum = "3", maximum = "64", example = "Volkov")
    @NotBlank
    @Size(min = 3, max = 32)
    private String lastName;

    @Schema(type = "integer", description = "The age of the Teacher", minimum = "21", example = "53")
    @AdultTeacher
    private Byte age;

    @Schema(description = "The grade of the Teacher", minimum = "3", maximum = "32", example = "Master's Degree")
    @Size(min = 3, max = 32)
    private String grade;

    @Schema(description = "The experience of the Teacher in years", minimum = "1", example = "8")
    @Positive
    private Integer experience;

    @Schema(description = "The email of the Teacher", format = "email", example = "nikVolkov@example.com")
    @Max(64)
    @Email
    private String email;

    private FacultyDTO faculty;


    public TeacherDTO() {
    }

    public TeacherDTO(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.grade = builder.grade;
        this.experience = builder.experience;
        this.email = builder.email;
        this.faculty = builder.faculty;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private Byte age;
        private String grade;
        private Integer experience;
        private String email;
        private FacultyDTO faculty;


        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setFaculty(FacultyDTO faculty) {
            this.faculty = faculty;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setAge(Byte age) {
            this.age = age;
            return this;
        }

        public Builder setExperience(Integer experience) {
            this.experience = experience;
            return this;
        }

        public Builder setGrade(String grade) {
            this.grade = grade;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }


        public TeacherDTO build() {
            return new TeacherDTO(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public FacultyDTO getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDTO that = (TeacherDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(age, that.age) && Objects.equals(grade, that.grade) && Objects.equals(experience, that.experience) && Objects.equals(email, that.email) && Objects.equals(faculty, that.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, grade, experience, email, faculty);
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", grade='" + grade + '\'' +
                ", experience=" + experience +
                ", email='" + email + '\'' +
                ", faculty=" + faculty +
                '}';
    }
}
