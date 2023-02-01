package ua.com.foxminded.university.persistance.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private Byte age;
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "course")
    private Byte course;
    @Column(name = "email")
    private String email;

    public StudentEntity() {

    }

    public StudentEntity(StudentEntityBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.groupId = builder.groupId;
        this.course = builder.course;
        this.email = builder.email;
    }

    public static class StudentEntityBuilder {
        private Long id;

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

        public StudentEntityBuilder setID(Long id) {
            this.id = id;
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

    public Long getGroupId() {
        return groupId;
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
        return "StudentEntity{" +
                "id=" + id +
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
        return Objects.equals(id, that.id) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && Objects.equals(age, that.age) && Objects.equals(groupId, that.groupId) && Objects.equals(course, that.course) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, groupId, course, email);
    }
}
