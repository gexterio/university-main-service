package ua.com.foxminded.university.persistance.models;


import java.util.Objects;

public class FacultyEntity {
    private Long iD;
    private String name;
    private Integer duration;

    public FacultyEntity() {

    }

    public FacultyEntity(FacultyEntityBuilder builder) {
        this.iD = builder.iD;
        this.name=builder.name;
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
