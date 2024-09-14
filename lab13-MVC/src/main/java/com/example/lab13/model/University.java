package com.example.lab13.model;

public class University {
    private int universityId;
    private String universityName;
    private int universityRating;
    private String universityAddress;

    public University() { }

    public University(String className, String universityRating, String universityAddress) {
        this.universityName = className;
        this.universityRating = Integer.parseInt(universityRating);
        this.universityAddress = universityAddress;
    }

    public int getId() {
        return universityId;
    }

    public void setId(int universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return universityName;
    }

    public void setName(String universityName) {
        this.universityName = universityName;
    }

    public int getRating() {
        return universityRating;
    }

    public void setRating(int universityRating) {
        this.universityRating = universityRating;
    }

    public String getAddress() {
        return universityAddress;
    }

    public void setAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }
}
