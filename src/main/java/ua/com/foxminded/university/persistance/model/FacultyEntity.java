package ua.com.foxminded.university.persistance.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "faculties")
public class FacultyEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private Integer duration;

    

    public FacultyEntity() {

    }

    public FacultyEntity(FacultyEntityBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.duration = builder.duration;
    }

    public static class FacultyEntityBuilder {
        private Long id;

        private final String name;

        private Integer duration;

        public FacultyEntityBuilder(String name) {
            this.name = name;
        }

        public FacultyEntityBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public FacultyEntityBuilder setDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public FacultyEntity build() {
            return new FacultyEntity(this);
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "FacultyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyEntity that = (FacultyEntity) o;
        return duration == that.duration && Objects.equals(id, that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }
}
