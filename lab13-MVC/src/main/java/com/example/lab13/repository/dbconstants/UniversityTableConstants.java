package com.example.lab13.repository.dbconstants;

public enum UniversityTableConstants {
    ID("ID"),
    NAME("name"),
    RATING("rating"),
    ADDRESS("address");
    private String fieldName;

    private UniversityTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
