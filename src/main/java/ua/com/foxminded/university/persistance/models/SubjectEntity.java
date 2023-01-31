package ua.com.foxminded.university.persistance.models;

import java.util.Objects;

public class SubjectEntity {

    private Long iD;

    private String name;

    private String description;

    public SubjectEntity() {
    }

    public SubjectEntity (SubjectEntityBuilder builder) {
        this.iD= builder.iD;
        this.name= builder.name;
        this.description= builder.description;
    }

    public static class SubjectEntityBuilder {

        private Long iD;

        private final String name;

        private String description;

        public SubjectEntityBuilder(String name) {
            this.name = name;
        }

        public SubjectEntityBuilder setDescription(String description) {
            this.description=description;
            return this;
        }

        public SubjectEntityBuilder setID (Long iD) {
            this.iD = iD;
            return this;
        }

        public SubjectEntity build () {
            return new SubjectEntity(this);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SubjectEntity{" +
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectEntity that = (SubjectEntity) o;
        return Objects.equals(iD, that.iD) && name.equals(that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, name, description);
    }
}
