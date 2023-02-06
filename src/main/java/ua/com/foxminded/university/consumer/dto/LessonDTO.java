package ua.com.foxminded.university.consumer.dto;

import ua.com.foxminded.university.persistance.model.LessonEntity;

import java.time.ZonedDateTime;
import java.util.Objects;

public class LessonDTO {

    private Long id;


    private String name;

    private Integer duration;

    private ZonedDateTime startTime;

    private Long subjectId;

    private String subjectName;

    private Long groupId;

    private String groupName;

    private Long teacherId;

    private String teacherName;

    public LessonDTO() {

    }

    public LessonDTO(LessonDTOBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.duration = builder.duration;
        this.startTime = builder.startTime;
        this.subjectId = builder.subjectId;
        this.subjectName = builder.subjectName;
        this.groupId = builder.groupId;
        this.groupName = builder.groupName;
        this.teacherId = builder.teacherId;
        this.teacherName = builder.teacherName;

    }

    public LessonDTO(LessonEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.duration = entity.getDuration();
        this.startTime = entity.getStartTime();
        this.subjectId = entity.getSubjectId();
        this.groupId = entity.getGroupId();
        this.teacherId = entity.getTeacherId();
    }

    public static class LessonDTOBuilder {

        private Long id;
        private String name;

        private Integer duration;

        private ZonedDateTime startTime;

        private Long subjectId;

        private String subjectName;

        private Long groupId;

        private String groupName;

        private Long teacherId;

        private String teacherName;


        public LessonDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public LessonDTOBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public LessonDTOBuilder setDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public LessonDTOBuilder setStartTime(ZonedDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public LessonDTOBuilder setSubjectId(Long subjectId) {
            this.subjectId = subjectId;
            return this;
        }

        public LessonDTOBuilder setSubjectName(String subjectName) {
            this.subjectName = subjectName;
            return this;
        }

        public LessonDTOBuilder setGroupId(Long groupId) {
            this.groupId = groupId;
            return this;
        }

        public LessonDTOBuilder setGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public LessonDTOBuilder setTeacherId(Long teacherId) {
            this.teacherId = teacherId;
            return this;
        }

        public LessonDTOBuilder setTeacherName(String teacherName) {
            this.teacherName = teacherName;
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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "LessonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", startTime=" + startTime +
                ", subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonDTO lessonDTO = (LessonDTO) o;
        return Objects.equals(id, lessonDTO.id) && Objects.equals(name, lessonDTO.name) && Objects.equals(duration, lessonDTO.duration) && Objects.equals(startTime, lessonDTO.startTime) && Objects.equals(subjectId, lessonDTO.subjectId) && Objects.equals(subjectName, lessonDTO.subjectName) && Objects.equals(groupId, lessonDTO.groupId) && Objects.equals(groupName, lessonDTO.groupName) && Objects.equals(teacherId, lessonDTO.teacherId) && Objects.equals(teacherName, lessonDTO.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, startTime, subjectId, subjectName, groupId, groupName, teacherId, teacherName);
    }
}
