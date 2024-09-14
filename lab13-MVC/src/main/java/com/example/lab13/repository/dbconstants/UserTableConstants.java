package com.example.lab13.repository.dbconstants;

public enum UserTableConstants {
    ID("ID"),
    LOGIN("name"),
    PASSWORD("password"),
    STATUS("status");

    private String fieldName;
    private UserTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName() {
        return fieldName;
    }
}