package ua.com.foxminded.university.persistance.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private SubjectEntity subject;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    public LessonEntity() {

    }

    public LessonEntity(Builder builder) {
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
        private final String name;

        private Integer duration;

        private ZonedDateTime startTime;

        private SubjectEntity subject;

        private GroupEntity group;

        private TeacherEntity teacher;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setId(Long id) {
            this.id = id;
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

        public Builder setGroup(GroupEntity group) {
            this.group = group;
            return this;
        }

        public Builder setSubject(SubjectEntity subject) {
            this.subject = subject;
            return this;
        }

        public Builder setTeacher(TeacherEntity teacher) {
            this.teacher = teacher;
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

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonEntity that = (LessonEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(duration, that.duration) && Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, startTime);
    }

    @Override
    public String toString() {
        return "LessonEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", startTime=" + startTime +
                '}';
    }
}