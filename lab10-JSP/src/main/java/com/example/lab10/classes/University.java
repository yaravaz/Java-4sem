package com.example.lab10.classes;

public class University {
    private int universityId;
    private String universityName;
    private int universityRating;
    private String universityAddress;

    public University() { }

    public University(int universityId, String className, int universityRating, String universityAddress) {
        this.universityId = universityId;
        this.universityName = className;
        this.universityRating = universityRating;
        this.universityAddress = universityAddress;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getUniversityRating() {
        return universityRating;
    }

    public void setUniversityRating(int universityRating) {
        this.universityRating = universityRating;
    }

    public String getUniversityAddress() {
        return universityAddress;
    }

    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }
}
