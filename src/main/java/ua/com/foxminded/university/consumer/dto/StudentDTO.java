package ua.com.foxminded.university.consumer.dto;

import java.util.Objects;

public class StudentDTO {
    private Long iD;

    private String firstName;

    private String lastName;

    private Byte age;

    private Long groupId;

    private String groupName;

    private Byte course;

    private String email;

    public StudentDTO() {

    }

    public StudentDTO(StudentDTOBuilder builder) {
        this.iD = builder.iD;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.groupId = builder.groupId;
        this.groupName = builder.groupName;
        this.course = builder.course;
        this.email = builder.email;
    }

    public static class StudentDTOBuilder {

        private Long iD;

        private String firstName;

        private String lastName;

        private Byte age;

        private Long groupId;

        public String groupName;

        private Byte course;

        private String email;


        public StudentDTOBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StudentDTOBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public StudentDTOBuilder setID(Long iD) {
            this.iD = iD;
            return this;
        }

        public StudentDTOBuilder setAge(Byte age) {
            this.age = age;
            return this;
        }

        public StudentDTOBuilder setGroupID(Long groupID) {
            this.groupId = groupID;
            return this;
        }

        public StudentDTOBuilder setGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public StudentDTOBuilder setCourse(Byte course) {
            this.course = course;
            return this;
        }

        public StudentDTOBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public StudentDTO build() {
            return new StudentDTO(this);
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

    public Long getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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
                "iD=" + iD +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", course=" + course +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return Objects.equals(iD, that.iD) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(age, that.age) && Objects.equals(groupId, that.groupId) && Objects.equals(groupName, that.groupName) && Objects.equals(course, that.course) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, firstName, lastName, age, groupId, groupName, course, email);
    }
}

