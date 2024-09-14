package com.example.lab13.builder;

public class BuilderFactory {
    private static final String USER = "user";
    private static final String UNIVERSITY = "universities";
    private static final String MESSAGE= "Unknown Builder name!";
    public static Builder create(String builderName) {
        switch (builderName) {
            case USER: {
                return (Builder) new UserBuilder();
            }
            case UNIVERSITY: {
                return (Builder) new UniversityBuilder();
            }
            default:
                throw new IllegalArgumentException(MESSAGE);
        }
    }
}

