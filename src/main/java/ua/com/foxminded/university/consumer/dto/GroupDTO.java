package ua.com.foxminded.university.consumer.dto;

import java.util.Objects;

public class GroupDTO {

    private Long iD;

    private String name;

    private Long facultyID;

    private String facultyName;

    public GroupDTO() {
    }

    public GroupDTO(GroupDTOBuilder builder) {
        this.iD = builder.iD;
        this.name = builder.name;
        this.facultyID = builder.facultyID;
        this.facultyName = builder.facultyName;
    }

    public static class GroupDTOBuilder {

        private Long iD;

        private String name;

        private Long facultyID;

        private String facultyName;


        public GroupDTOBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public GroupDTOBuilder setFacultyID(Long iD) {
            this.facultyID = iD;
            return this;
        }

        public GroupDTOBuilder setFacultyName(String facultyName) {
            this.facultyName = facultyName;
            return this;
        }

        public GroupDTOBuilder setID(Long iD) {
            this.iD = iD;
            return this;
        }

        public GroupDTO build() {
            return new GroupDTO(this);
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

    public Long getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Long facultyID) {
        this.facultyID = facultyID;
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
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", facultyID=" + facultyID +
                ", facultyName='" + facultyName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO groupDTO = (GroupDTO) o;
        return Objects.equals(iD, groupDTO.iD) && Objects.equals(name, groupDTO.name) && Objects.equals(facultyID, groupDTO.facultyID) && Objects.equals(facultyName, groupDTO.facultyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, name, facultyID, facultyName);
    }
}
