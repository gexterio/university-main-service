package ua.com.foxminded.university.consumer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ua.com.foxminded.university.persistance.model.StudentEntity;
import ua.com.foxminded.university.util.validation.AdultStudent;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

@Schema(description = "Student entity")
public class StudentDTO {


    @Schema(description = "The id of the Student", example = "123")
    private Long id;

    @Schema(description = "The first name of the Student", example = "Adam")
    @NotBlank
    @Size(min = 3, max = 32)
    private String firstName;

    @Schema(description = "The last name of the Student", example = "Moore")
    @NotBlank
    @Size(min = 3, max = 32)
    private String lastName;

    @Schema(minimum = "18", type = "integer", description = "The age of the Student", example = "19")
    @AdultStudent
    private Byte age;

    private GroupDTO group;

    @Schema(minimum = "1", type = "integer", description = "The course of the Student", example = "2")
    @NotNull
    @Positive
    private Byte course;

    @Schema(format = "email", description = "The email of the Student", example = "adam.moore@example.com")
    @Email
    private String email;

    public StudentDTO() {

    }

    public StudentDTO(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.group = builder.group;
        this.course = builder.course;
        this.email = builder.email;
    }

    public StudentDTO(StudentEntity entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.age = entity.getAge();
        this.course = entity.getCourse();
        this.email = entity.getEmail();
    }

    public static class Builder {

        private Long id;

        private String firstName;

        private String lastName;

        private Byte age;

        private GroupDTO group;

        private Byte course;

        private String email;


        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setAge(Byte age) {
            this.age = age;
            return this;
        }

        public Builder setGroup(GroupDTO group) {
            this.group = group;
            return this;
        }

        public Builder setCourse(Byte course) {
            this.course = course;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public StudentDTO build() {
            return new StudentDTO(this);
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

    public GroupDTO getGroup() {
        return group;
    }


    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public Byte getCourse() {
        return course;
    }

    public void setCourse(Byte course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", course=" + course +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(age, that.age) && Objects.equals(group, that.group) && Objects.equals(course, that.course) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, group, course, email);
    }
}

