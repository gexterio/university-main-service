package ua.com.foxminded.university.consumer.dto;


import ua.com.foxminded.university.persistance.model.SubjectEntity;

import java.util.Objects;

public class SubjectDTO {


    private Long id;

    private String name;

    private String description;

    public SubjectDTO() {
    }

    public SubjectDTO(SubjectDTOBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    public SubjectDTO(SubjectEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
    }

    public static class SubjectDTOBuilder {

        private Long id;

        private String name;

        private String description;


        public SubjectDTOBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public SubjectDTOBuilder setId(Long id) {
            this.id = id;
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
