package ua.com.foxminded.university.consumer.dto;


import java.util.Objects;

public class TeacherDTO {

    private Long iD;
    private String firstName;
    private String lastName;
    private Byte age;
    private String grade;
    private Integer experience;
    private String email;
    private Long facultyID;
    private String facultyName;


    public TeacherDTO() {
    }

    public TeacherDTO(TeacherDTOBuilder builder) {
        this.iD = builder.iD;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.grade = builder.grade;
        this.experience = builder.experience;
        this.email = builder.email;
        this.facultyID = builder.facultyID;
        this.facultyName = builder.facultyName;
    }

    public static class TeacherDTOBuilder {
        private Long iD;
        private String firstName;
        private String lastName;
        private Byte age;
        private String grade;
        private Integer experience;
        private String email;
        private Long facultyID;

        private String facultyName;


        public TeacherDTOBuilder setID(Long iD) {
            this.iD = iD;
            return this;
        }

        public TeacherDTOBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public TeacherDTOBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public TeacherDTOBuilder setAge(Byte age) {
            this.age = age;
            return this;
        }

        public TeacherDTOBuilder setExperience(Integer experience) {
            this.experience = experience;
            return this;
        }

        public TeacherDTOBuilder setGrade(String grade) {
            this.grade = grade;
            return this;
        }

        public TeacherDTOBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public TeacherDTOBuilder setFacultyId(Long facultyID) {
            this.facultyID = facultyID;
            return this;
        }

        public TeacherDTOBuilder setFacultyName(String facultyName) {
            this.facultyName = facultyName;
            return this;
        }

        public TeacherDTO build() {
            return new TeacherDTO(this);
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

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "iD=" + iD +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", grade='" + grade + '\'' +
                ", experience=" + experience +
                ", email='" + email + '\'' +
                ", facultyID=" + facultyID +
                ", facultyName='" + facultyName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDTO that = (TeacherDTO) o;
        return Objects.equals(iD, that.iD) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(age, that.age) && Objects.equals(grade, that.grade) && Objects.equals(experience, that.experience) && Objects.equals(email, that.email) && Objects.equals(facultyID, that.facultyID) && Objects.equals(facultyName, that.facultyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, firstName, lastName, age, grade, experience, email, facultyID, facultyName);
    }
}
