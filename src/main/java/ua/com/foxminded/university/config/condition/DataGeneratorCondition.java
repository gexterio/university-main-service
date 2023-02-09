package ua.com.foxminded.university.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DataGeneratorCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            String property = context.getEnvironment().getProperty("data-generator-enable");
            if (property == null) {
                return false;
            }
            return property.equals("true");
        } catch (Exception e) {
            return false;
        }
    }
}
