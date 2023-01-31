package ua.com.foxminded.university.persistance.models;

import java.util.Objects;

public class GroupEntity {

    private Long iD;
    private String name;

    private Long facultyID;

    public GroupEntity() {
    }

    public GroupEntity(GroupEntityBuilder builder) {
        this.iD = builder.iD;
        this.name = builder.name;
        this.facultyID = builder.facultyID;
    }

    public static class GroupEntityBuilder {

        private Long iD;
        private final String name;

        private Long facultyID;

        public GroupEntityBuilder(String name) {
            this.name = name;
        }

        public GroupEntityBuilder setFacultyID(Long iD) {
            this.facultyID = iD;
            return this;
        }

        public GroupEntityBuilder setID(Long iD) {
            this.iD = iD;
            return this;
        }

        public GroupEntity build() {
            return new GroupEntity(this);
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

    @Override
    public String toString() {
        return "GroupEntity{" +
                "name='" + name + '\'' +
                ", facultyID=" + facultyID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupEntity that = (GroupEntity) o;
        return Objects.equals(iD, that.iD) && Objects.equals(name, that.name) && Objects.equals(facultyID, that.facultyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, name, facultyID);
    }
}
