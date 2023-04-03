package ua.com.foxminded.university.util.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
@Aspect
public class LoggingAspect {

    private final Logger log = LogManager.getLogger(this.getClass());


    @Around("ua.com.foxminded.university.util.aspect.PointCuts.isServiceLayer() && ua.com.foxminded.university.util.aspect.PointCuts.isAnyFindAllServiceMethod()" +
            "&& args(pageable)")
    public Object addLoggingAroundServiceFindAllMethod(ProceedingJoinPoint joinPoint, Pageable pageable) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("Trying to get {} entities from Database...", pageable.getPageSize());
        try {
            Object result = joinPoint.proceed();
            long time = System.currentTimeMillis() - startTime;
            int size = 0;
            String type = "unknown";
            if (result instanceof PageImpl) {
                size = ((PageImpl<?>) result).getSize();
                type = ((PageImpl<?>) result).getContent().get(0).getClass().getSimpleName();
            }
            log.info("Entities were received in {}ms. Type: {}. Count: {}.", time, type, size);
            return result;
        } catch (Throwable e) {
            log.warn("Can't get entities. Cause: {}", e.getMessage());
            throw e;
        }
    }


    @Around("ua.com.foxminded.university.util.aspect.PointCuts.isServiceLayer() && ua.com.foxminded.university.util.aspect.PointCuts.isAnyFindByIdServiceMethod()" +
            "&& args(id)")
    public Object addLoggingAroundServiceFindByIdMethod(ProceedingJoinPoint joinPoint, Long id) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("Trying to get entity from Database by ID: {}...", id);
        try {
            Object result = joinPoint.proceed();
            long time = System.currentTimeMillis() - startTime;
            log.info("Entity by ID: {} was found in {} ms: [{}]", id, time, result);
            return result;
        } catch (Throwable e) {
            log.warn("Can't get entity by ID = {}. Cause: {}", id, e.getMessage());
            throw e;
        }
    }

    @Around("ua.com.foxminded.university.util.aspect.PointCuts.isServiceLayer() && ua.com.foxminded.university.util.aspect.PointCuts.isAnyCreateServiceMethod()" +
            "&& args(dto)")
    public Object addLoggingAroundServiceCreateMethod(ProceedingJoinPoint joinPoint, Object dto) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("Trying to add entity into Database: {}", dto);
        try {
            Object result = joinPoint.proceed();
            long time = System.currentTimeMillis() - startTime;
            log.info("Entity added in {}ms: {}", time, result);
            return result;
        } catch (Throwable e) {
            log.warn("Can't add entity into Database. Cause: {}. Entity: {}", e.getMessage(), dto);
            throw e;
        }
    }

    @Around("ua.com.foxminded.university.util.aspect.PointCuts.isServiceLayer() && ua.com.foxminded.university.util.aspect.PointCuts.isAnyUpdateServiceMethod()" +
            "&& args(dto)")
    public Object addLoggingAroundServiceUpdateMethod(ProceedingJoinPoint joinPoint, Object dto) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("Trying to update entity in Database: {}", dto);
        try {
            Object result = joinPoint.proceed();
            long time = System.currentTimeMillis() - startTime;
            log.info("Entity updated in {}ms: {}", time, result);
            return result;
        } catch (Throwable e) {
            log.warn("Can't update entity in Database. Cause: {}. Entity: {}", e.getMessage(), dto);
            throw e;
        }
    }

    @Around("ua.com.foxminded.university.util.aspect.PointCuts.isServiceLayer() && ua.com.foxminded.university.util.aspect.PointCuts.isAnyDeleteServiceMethod()" +
            "&& args(id)")
    public Object addLoggingAroundServiceDeleteMethod(ProceedingJoinPoint joinPoint, Long id) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("Trying to delete entity from Database: ID = {}", id);
        try {
            Object result = joinPoint.proceed();
            long time = System.currentTimeMillis() - startTime;
            log.info("Entity deleted in {}ms: ID ={}, successfully: {}", time, id, result);
            return result;
        } catch (Throwable e) {
            log.warn("Can't delete entity from Database. Cause: {}. ID: {}", e.getMessage(), id);
            throw e;
        }
    }

    @Around("PointCuts.isServiceLayer() && PointCuts.isAnyFindLessonsServiceMethod()" +
            "&&args(id, date)")
    public Object addLoggingAroundServiceFindLessonsMethod(ProceedingJoinPoint joinPoint, Long id, ZonedDateTime date) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("Trying to get lessons from Database: ID = {}, Date = {}", id, date);
        try {
            Object result = joinPoint.proceed();
            long time = System.currentTimeMillis() - startTime;
            if (result instanceof List<?>) {
                List<?> lessons = (List<?>) result;
                if (!lessons.isEmpty()) {
                    log.info("Lesson(s) for entity with ID = {} was found in {}ms: count = {}. {}", id, time, lessons.size(), lessons);
                } else {
                    log.info("Lesson(s) for entity with ID = {} was not found in {}ms: {}", id, time, lessons);
                }
            }
            return result;
        } catch (Throwable e) {
            log.warn("I don't know what can go wrong here but if you see this message something is unexpected.");
            throw e;
        }
    }


}
