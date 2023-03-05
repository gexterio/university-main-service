package ua.com.foxminded.university.persistance.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne()
    @JoinColumn(name = "faculty_id")
    private FacultyEntity faculty;

    @OneToMany(mappedBy = "group",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<StudentEntity> students = new ArrayList<>();

    @OneToMany(mappedBy = "group",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List<LessonEntity> lessons = new ArrayList<>();

    public GroupEntity() {
    }

    public GroupEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.faculty = builder.faculty;
        this.students = builder.students;
    }

    public static class Builder {

        private Long id;
        private final String name;

        private FacultyEntity faculty;

        private List<StudentEntity> students = new ArrayList<>();


        public Builder(String name) {
            this.name = name;
        }

        public Builder setFaculty(FacultyEntity faculty) {
            this.faculty = faculty;
            return this;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setStudents(List<StudentEntity> students) {
            this.students = students;
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

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

    public List<LessonEntity> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonEntity> lessons) {
        this.lessons = lessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupEntity that = (GroupEntity) o;
        return Objects.equals(id, that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String
    toString() {
        return "GroupEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

