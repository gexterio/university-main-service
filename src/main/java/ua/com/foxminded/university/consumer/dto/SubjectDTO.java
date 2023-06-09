package ua.com.foxminded.university.consumer.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import ua.com.foxminded.university.persistance.model.SubjectEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Schema(description = "Subject entity")
public class SubjectDTO {

    @Schema(description = "The id of the Subject", example = "123")
    private Long id;

    @Schema(description = "The name of the Subject", minimum = "3", maximum = "64", example = "Mathematics")
    @NotBlank
    @Size(min = 3, max = 64)
    private String name;

    @Schema(description = "The description of the Subject", maximum = "256", example = "Mathematicians have always been fascinated by numbers")
    @Max(256)
    private String description;

    public SubjectDTO() {
    }

    public SubjectDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    public SubjectDTO(SubjectEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
    }

    public static class Builder {

        private Long id;

        private String name;

        private String description;


        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public SubjectDTO build() {
            return new SubjectDTO(this);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SubjectDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDTO that = (SubjectDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
