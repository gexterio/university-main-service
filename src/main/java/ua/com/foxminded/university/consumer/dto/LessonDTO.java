package ua.com.foxminded.university.consumer.dto;

import java.time.ZonedDateTime;
import java.util.Objects;

public class LessonDTO {

    private Long iD;


    private String name;

    private Integer duration;

    private ZonedDateTime startTime;

    private Long subjectID;

    private String subjectName;

    private Long groupID;

    private String groupName;

    private Long teacherID;

    private String teacherName;

    public LessonDTO() {

    }

    public LessonDTO(LessonDTOBuilder builder) {
        this.iD = builder.iD;
        this.name = builder.name;
        this.duration = builder.duration;
        this.startTime = builder.startTime;
        this.subjectID = builder.subjectID;
        this.subjectName = builder.subjectName;
        this.groupID = builder.groupID;
        this.groupName = builder.groupName;
        this.teacherID = builder.teacherID;
        this.teacherName = builder.teacherName;

    }

    public static class LessonDTOBuilder {

        private Long iD;
        private String name;

        private Integer duration;

        private ZonedDateTime startTime;

        private Long subjectID;

        public String subjectName;

        private Long groupID;

        public String groupName;

        private Long teacherID;

        public String teacherName;


        public LessonDTOBuilder setID(Long iD) {
            this.iD = iD;
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

        public LessonDTOBuilder setSubjectID(Long subjectID) {
            this.subjectID = subjectID;
            return this;
        }

        public LessonDTOBuilder setSubjectName(String subjectName) {
            this.subjectName = subjectName;
            return this;
        }

        public LessonDTOBuilder setGroupID(Long groupID) {
            this.groupID = groupID;
            return this;
        }

        public LessonDTOBuilder setGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public LessonDTOBuilder setTeacherID(Long teacherID) {
            this.teacherID = teacherID;
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

    public Long getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Long subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Long teacherID) {
        this.teacherID = teacherID;
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
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", startTime=" + startTime +
                ", subjectID=" + subjectID +
                ", subjectName='" + subjectName + '\'' +
                ", groupID=" + groupID +
                ", groupName='" + groupName + '\'' +
                ", teacherID=" + teacherID +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonDTO lessonDTO = (LessonDTO) o;
        return Objects.equals(iD, lessonDTO.iD) && Objects.equals(name, lessonDTO.name) && Objects.equals(duration, lessonDTO.duration) && Objects.equals(startTime, lessonDTO.startTime) && Objects.equals(subjectID, lessonDTO.subjectID) && Objects.equals(subjectName, lessonDTO.subjectName) && Objects.equals(groupID, lessonDTO.groupID) && Objects.equals(groupName, lessonDTO.groupName) && Objects.equals(teacherID, lessonDTO.teacherID) && Objects.equals(teacherName, lessonDTO.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, name, duration, startTime, subjectID, subjectName, groupID, groupName, teacherID, teacherName);
    }
}
