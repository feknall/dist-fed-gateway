package com.example.fljavagateway.common;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class IsLeadAggregatorCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String role = context.getEnvironment().getProperty("fl.role");
        return RoleEnum.toEnum(role) == RoleEnum.LEAD_AGGREGATOR;
    }
}
