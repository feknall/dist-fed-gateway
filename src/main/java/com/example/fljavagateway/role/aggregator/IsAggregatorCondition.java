package com.example.fljavagateway.role.aggregator;

import com.example.fljavagateway.common.RoleEnum;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class IsAggregatorCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String role = context.getEnvironment().getProperty("fl.role");
        return RoleEnum.toEnum(role) == RoleEnum.AGGREGATOR;
    }
}
