package ua.com.foxminded.university.consumer.dto;

import ua.com.foxminded.university.persistance.model.FacultyEntity;

import java.util.Objects;

public class FacultyDTO {

    private Long id;
    private String name;
    private Integer duration;

    public FacultyDTO() {

    }

    public FacultyDTO(FacultyDTOBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.duration = builder.duration;
    }

    public FacultyDTO(FacultyEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.duration = entity.getDuration();
    }

    public static class FacultyDTOBuilder {
        private Long id;

        private String name;

        private Integer duration;


        public FacultyDTOBuilder setId(Long id) {
            this.id = id;
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
        return "FacultyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyDTO that = (FacultyDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }
}
