package ua.com.foxminded.university.consumer.dto;


import java.util.Objects;

public class SubjectDTO {


    private Long iD;

    private String name;

    private String description;

    public SubjectDTO() {
    }

    public SubjectDTO(SubjectDTOBuilder builder) {
        this.iD = builder.iD;
        this.name = builder.name;
        this.description = builder.description;
    }

    public static class SubjectDTOBuilder {

        private Long iD;

        private String name;

        private String description;


        public SubjectDTOBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public SubjectDTOBuilder setID(Long iD) {
            this.iD = iD;
            return this;
        }

        public SubjectDTOBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SubjectDTO build() {
            return new SubjectDTO(this);
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
        return "SubjectDTO{" +
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDTO that = (SubjectDTO) o;
        return Objects.equals(iD, that.iD) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, name, description);
    }
}
