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
@Table(name = "groups")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "faculty_id")
    private Long facultyId;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<StudentEntity> students = new ArrayList<>();

    public GroupEntity() {
    }

    public GroupEntity(GroupEntityBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.facultyId = builder.facultyId;
        this.students = builder.students;
    }

    public static class GroupEntityBuilder {

        private Long id;
        private final String name;

        private Long facultyId;

        private List<StudentEntity> students = new ArrayList<>();


        public GroupEntityBuilder(String name) {
            this.name = name;
        }

        public GroupEntityBuilder setFacultyId(Long id) {
            this.facultyId = id;
            return this;
        }

        public GroupEntityBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public GroupEntityBuilder addStudent(StudentEntity student) {
            students.add(student);
            return this;
        }

        public GroupEntity build() {
            return new GroupEntity(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void addStudent(StudentEntity student) {
        students.add(student);
    }

    public void removeStudent(StudentEntity student) {
        students.remove(student);
    }

    @Override
    public String toString() {
        return "GroupEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", facultyId=" + facultyId +
                ", students=" + students +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupEntity that = (GroupEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(facultyId, that.facultyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, facultyId);
    }
}
