package ua.com.foxminded.university.persistance.models;

import java.time.ZonedDateTime;
import java.util.Objects;

public class LessonEntity {

    private Long iD;


    private String name;

    private Integer duration;

    private ZonedDateTime startTime;

    private Long subjectID;

    private Long groupID;

    private Long teacherID;

    public LessonEntity() {

    }

    public LessonEntity(LessonEntityBuilder builder) {
        this.iD = builder.iD;
        this.name = builder.name;
        this.duration = builder.duration;
        this.startTime = builder.startTime;
        this.subjectID = builder.subjectID;
        this.groupID = builder.groupID;
        this.teacherID = builder.teacherID;
    }

    public static class LessonEntityBuilder {

        private Long iD;
        private final String name;

        private Integer duration;

        private ZonedDateTime startTime;

        private final Long subjectID;

        private Long groupID;

        private Long teacherID;

        public LessonEntityBuilder(String name, Long subjectID) {
            this.name = name;
            this.subjectID = subjectID;
        }

        public LessonEntityBuilder setID(Long iD) {
            this.iD = iD;
            return this;
        }

        public LessonEntityBuilder setDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public LessonEntityBuilder setStartTime(ZonedDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public LessonEntityBuilder setGroupID(Long groupID) {
            this.groupID = groupID;
            return this;
        }

        public LessonEntityBuilder setTeacherID(Long teacherID) {
            this.teacherID = teacherID;
            return this;
        }

        public LessonEntity build() {
            return new LessonEntity(this);
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

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public Long getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Long teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public String toString() {
        return "LessonEntity{" +
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", startTime=" + startTime +
                ", subjectID=" + subjectID +
                ", groupID=" + groupID +
                ", teacherID=" + teacherID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonEntity that = (LessonEntity) o;
        return Objects.equals(iD, that.iD) && name.equals(that.name) && Objects.equals(duration, that.duration) && startTime.equals(that.startTime) && Objects.equals(subjectID, that.subjectID) && Objects.equals(groupID, that.groupID) && Objects.equals(teacherID, that.teacherID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, name, duration, startTime, subjectID, groupID, teacherID);
    }
}