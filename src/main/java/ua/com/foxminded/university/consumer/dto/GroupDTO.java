package ua.com.foxminded.university.consumer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ua.com.foxminded.university.util.validation.GroupNamePattern;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "Group entity")
public class GroupDTO {

    @Schema(description = "The id of the Group", example = "123")
    private Long id;

    @Schema(format = "regex [A-Z{2}-/d{2}", description = "The name of the Group", example = "AB-12")
    @NotBlank
    @GroupNamePattern
    private String name;

    private FacultyDTO faculty;


    public GroupDTO() {
    }

    public GroupDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.faculty = builder.faculty;
    }

    public static class Builder {

        private Long id;

        private String name;

        private FacultyDTO faculty;


        public Builder setName(String name) {
            this.name = name;
            return this;
        }


        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setFaculty(FacultyDTO faculty) {
            this.faculty = faculty;
            return this;
        }

        public GroupDTO build() {
            return new GroupDTO(this);
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

    public FacultyDTO getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO groupDTO = (GroupDTO) o;
        return Objects.equals(id, groupDTO.id) && Objects.equals(name, groupDTO.name) && Objects.equals(faculty, groupDTO.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, faculty);
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faculty=" + faculty +
                '}';
    }
}
