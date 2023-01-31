package ua.com.foxminded.university.persistance.models;

import java.util.Objects;

public class StudentEntity {

    private Long iD;

    private String firstName;

    private String lastName;

    private Byte age;

    private Long groupId;

    private Byte course;

    private String email;

    public StudentEntity() {

    }

    public StudentEntity(StudentEntityBuilder builder) {
        this.iD = builder.iD;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.groupId = builder.groupId;
        this.course = builder.course;
        this.email = builder.email;
    }

    public static class StudentEntityBuilder {
        private Long iD;

        private final String firstName;

        private final String lastName;

        private Byte age;

        private Long groupId;

        private Byte course;

        private String email;


        public StudentEntityBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public StudentEntityBuilder setID(Long iD) {
            this.iD = iD;
            return this;
        }

        public StudentEntityBuilder setAge(Byte age) {
            this.age = age;
            return this;
        }

        public StudentEntityBuilder setGroupID(Long groupID) {
            this.groupId = groupID;
            return this;
        }

        public StudentEntityBuilder setCourse(Byte course) {
            this.course = course;
            return this;
        }

        public StudentEntityBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public StudentEntity build() {
            return new StudentEntity(this);
        }
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "iD=" + iD +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", groupId=" + groupId +
                ", course=" + course +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(iD, that.iD) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && Objects.equals(age, that.age) && Objects.equals(groupId, that.groupId) && Objects.equals(course, that.course) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, firstName, lastName, age, groupId, course, email);
    }
}
