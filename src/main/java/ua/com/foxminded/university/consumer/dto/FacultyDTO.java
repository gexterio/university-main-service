package ua.com.foxminded.university.consumer.dto;

import java.util.Objects;

public class FacultyDTO {

    private Long iD;
    private String name;
    private Integer duration;

    public FacultyDTO() {

    }

    public FacultyDTO(FacultyDTOBuilder builder) {
        this.iD = builder.iD;
        this.name = builder.name;
        this.duration = builder.duration;
    }

    public static class FacultyDTOBuilder {
        private Long iD;

        private String name;

        private Integer duration;


        public FacultyDTOBuilder setID(Long iD) {
            this.iD = iD;
            return this;
        }

        public FacultyDTOBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public FacultyDTOBuilder setDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public FacultyDTO build() {
            return new FacultyDTO(this);
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
        return "FacultyDTO{" +
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyDTO that = (FacultyDTO) o;
        return Objects.equals(iD, that.iD) && Objects.equals(name, that.name) && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, name, duration);
    }
}
