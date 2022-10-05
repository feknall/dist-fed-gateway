package com.example.fljavagateway.role.trainer;

import com.example.fljavagateway.common.RoleEnum;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class IsTrainerCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String role = context.getEnvironment().getProperty("fl.role");
        return RoleEnum.toEnum(role) == RoleEnum.TRAINER;
    }
}
