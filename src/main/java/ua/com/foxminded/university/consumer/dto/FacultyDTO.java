package ua.com.foxminded.university.consumer.dto;

 import io.swagger.v3.oas.annotations.media.Schema;
import ua.com.foxminded.university.persistance.model.FacultyEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

@Schema(description = "Faculty entity")
public class FacultyDTO {

    @Schema(description = "The id of the Faculty", example = "123")
    private Long id;

    @Schema(description = "The name of the Faculty", minimum = "3", maximum = "64", example = "Management")
    @NotBlank
    @Size(min = 3, max = 64)
    private String name;

    @Schema(description = "The duration of the Faculty in years", minimum = "1", example = "4")
    @NotNull
    @Positive
    private Integer duration;

    public FacultyDTO() {

    }

    public FacultyDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.duration = builder.duration;
    }

    public FacultyDTO(FacultyEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.duration = entity.getDuration();
    }

    public static class Builder {
        private Long id;

        private String name;

        private Integer duration;


        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDuration(Integer duration) {
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
