package ua.com.foxminded.university.consumer.dto;

import ua.com.foxminded.university.persistance.model.GroupEntity;

import java.util.Objects;

public class GroupDTO {

    private Long id;

    private String name;

    private Long facultyId;

    private String facultyName;

    public GroupDTO() {
    }

    public GroupDTO(GroupDTOBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.facultyId = builder.facultyId;
        this.facultyName = builder.facultyName;
    }

    public GroupDTO(GroupEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.facultyId = entity.getFacultyId();
    }

    public static class GroupDTOBuilder {

        private Long id;

        private String name;

        private Long facultyId;

        private String facultyName;


        public GroupDTOBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public GroupDTOBuilder setFacultyId(Long id) {
            this.facultyId = id;
            return this;
        }

        public GroupDTOBuilder setFacultyName(String facultyName) {
            this.facultyName = facultyName;
            return this;
        }

        public GroupDTOBuilder setId(Long id) {
            this.id = id;
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

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", facultyId=" + facultyId +
                ", facultyName='" + facultyName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO groupDTO = (GroupDTO) o;
        return Objects.equals(id, groupDTO.id) && Objects.equals(name, groupDTO.name) && Objects.equals(facultyId, groupDTO.facultyId) && Objects.equals(facultyName, groupDTO.facultyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, facultyId, facultyName);
    }
}
