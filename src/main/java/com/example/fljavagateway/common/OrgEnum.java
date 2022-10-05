package com.example.fljavagateway.common;

public enum OrgEnum {
    ORG1("org1"),
    ORG2("org2");

    private final String value;
    private OrgEnum(String value) {
        this.value = value;
    }

    public static OrgEnum toEnum(String value) {
        return value.equals("org1") ? OrgEnum.ORG1 : OrgEnum.ORG2;
    }
}
