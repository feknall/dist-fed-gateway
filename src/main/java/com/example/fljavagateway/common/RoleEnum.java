package com.example.fljavagateway.common;

public enum RoleEnum {
    TRAINER("trainer"),
    AGGREGATOR("aggregator"),
    FL_ADMIN("flAdmin"),
    LEAD_AGGREGATOR("leadAggregator");

    private final String value;
    private RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RoleEnum toEnum(String value) {
        if (value.equals(TRAINER.getValue())) {
            return TRAINER;
        } else if (value.equals(AGGREGATOR.getValue())) {
            return AGGREGATOR;
        } else if (value.equals(LEAD_AGGREGATOR.getValue())) {
            return LEAD_AGGREGATOR;
        } else if (value.equals(FL_ADMIN.getValue())) {
            return FL_ADMIN;
        }
        throw new RuntimeException(String.format("Unknown value: %s", value));
    }
}
