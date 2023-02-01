package ua.com.foxminded.university.persistance.models;


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
    private Long iD;
    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private Integer duration;

    public FacultyEntity() {

    }

    public FacultyEntity(FacultyEntityBuilder builder) {
        this.iD = builder.iD;
        this.name = builder.name;
        this.duration = builder.duration;
    }

    public static class FacultyEntityBuilder {
        private Long iD;

        private final String name;

        private Integer duration;

        public FacultyEntityBuilder(String name) {
            this.name = name;
        }

        public FacultyEntityBuilder setID(Long iD) {
            this.iD = iD;
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

    public Long getID() {
        return iD;
    }

    public void setID(Long iD) {
        this.iD = iD;
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
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyEntity that = (FacultyEntity) o;
        return duration == that.duration && Objects.equals(iD, that.iD) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, name, duration);
    }
}
