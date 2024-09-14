package com.example.lab13.util.pages;

public enum Page {
    LOGIN_PAGE("/WEB-INF/views/Login.jsp"),
    REGISTER_PAGE("/WEB-INF/views/Register.jsp"),
    WELCOME_PAGE ("/WEB-INF/views/Welcome.jsp"),
    ERROR_PAGE ("/WEB-INF/views/ErrorPage.jsp");
    private final String value;
    Page(String value) {
        this.value = value;
    }
    public String getPage() {
        return value;
    }
}

