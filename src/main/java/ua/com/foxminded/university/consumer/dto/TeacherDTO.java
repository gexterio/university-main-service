package ua.com.foxminded.university.consumer.dto;


import java.util.Objects;

public class TeacherDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Byte age;
    private String grade;
    private Integer experience;
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
