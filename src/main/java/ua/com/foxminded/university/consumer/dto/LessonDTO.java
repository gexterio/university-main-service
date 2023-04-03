package ua.com.foxminded.university.consumer.dto;

import ua.com.foxminded.university.util.validation.NotPast;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Objects;

public class LessonDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 64)
    private String name;

    @NotNull
    @Positive
    private Integer duration;

    @NotPast
    private ZonedDateTime startTime;

    private SubjectDTO subject;

    private GroupDTO group;

    private TeacherDTO teacher;

    public LessonDTO() {

    }

    public LessonDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.duration = builder.duration;
        this.startTime = builder.startTime;
        this.subject = builder.subject;
        this.group = builder.group;
        this.teacher = builder.teacher;

    }


    public static class Builder {

        private Long id;
        private String name;

        private Integer duration;

        private ZonedDateTime startTime;

        private TeacherDTO teacher;

        private SubjectDTO subject;

        private GroupDTO group;

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

        public Builder setStartTime(ZonedDateTime startTime) {
            this.startTime = startTime;
            return this;
        }


        public Builder setSubject(SubjectDTO subject) {
            this.subject = subject;
            return this;
        }

        public Builder setGroup(GroupDTO group) {
            this.group = group;
            return this;
        }


        public Builder setTeacher(TeacherDTO teacher) {
            this.teacher = teacher;
            return this;
        }

        public LessonDTO build() {
            return new LessonDTO(this);
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

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonDTO lessonDTO = (LessonDTO) o;
        return Objects.equals(id, lessonDTO.id) && Objects.equals(name, lessonDTO.name) && Objects.equals(duration, lessonDTO.duration) && Objects.equals(startTime, lessonDTO.startTime) && Objects.equals(subject, lessonDTO.subject) && Objects.equals(group, lessonDTO.group) && Objects.equals(teacher, lessonDTO.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, startTime, subject, group, teacher);
    }

    @Override
    public String toString() {
        return "LessonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", startTime=" + startTime +
                ", subject=" + subject +
                ", group=" + group +
                ", teacher=" + teacher +
                '}';
    }
}
