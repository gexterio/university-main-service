package ua.com.foxminded.university.persistance.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "lessons")
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "start_time")
    private ZonedDateTime startTime;
    @Column(name = "subject_id")
    private Long subjectId;
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "teacher_id")
    private Long teacherId;

    public LessonEntity() {

    }

    public LessonEntity(LessonEntityBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.duration = builder.duration;
        this.startTime = builder.startTime;
        this.subjectId = builder.subjectId;
        this.groupId = builder.groupId;
        this.teacherId = builder.teacherId;
    }

    public static class LessonEntityBuilder {

        private Long id;
        private final String name;

        private Integer duration;

        private ZonedDateTime startTime;

        private final Long subjectId;

        private Long groupId;

        private Long teacherId;

        public LessonEntityBuilder(String name, Long subjectId) {
            this.name = name;
            this.subjectId = subjectId;
        }

        public LessonEntityBuilder setId(Long id) {
            this.id = id;
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

        public LessonEntityBuilder setGroupId(Long groupId) {
            this.groupId = groupId;
            return this;
        }

        public LessonEntityBuilder setTeacherId(Long teacherId) {
            this.teacherId = teacherId;
            return this;
        }

        public LessonEntity build() {
            return new LessonEntity(this);
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "LessonEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", startTime=" + startTime +
                ", subjectId=" + subjectId +
                ", groupId=" + groupId +
                ", teacherId=" + teacherId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonEntity that = (LessonEntity) o;
        return Objects.equals(id, that.id) && name.equals(that.name) && Objects.equals(duration, that.duration) && startTime.equals(that.startTime) && Objects.equals(subjectId, that.subjectId) && Objects.equals(groupId, that.groupId) && Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, startTime, subjectId, groupId, teacherId);
    }
}