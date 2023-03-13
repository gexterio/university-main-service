package ua.com.foxminded.university.consumer.dto;

import java.util.Objects;

public class GroupDTO {

    private Long id;

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
