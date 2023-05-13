package ua.com.foxminded.university.util.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PointCuts {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceLayer() {
    }

    @Pointcut("execution(public * findById(*))throws *NotFoundException")
    public void isAnyFindByIdServiceMethod() {
    }

    @Pointcut("execution(public * findAll(*))")
    public void isAnyFindAllServiceMethod() {
    }

    @Pointcut("execution(public * create(*))")
    public void isAnyCreateServiceMethod() {
    }

    @Pointcut("execution(public * update(*))")
    public void isAnyUpdateServiceMethod() {
    }

    @Pointcut("execution(public * delete(*))")
    public void isAnyDeleteServiceMethod() {
    }

    @Pointcut("execution(public * findLessons*(*,*))")
    public void isAnyFindLessonsServiceMethod() {
    }
}
