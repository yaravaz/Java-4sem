package com.example.lab13.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String login;
    private byte[] password;
    private String status = "user";

    public User(String login, byte[] password) {
        this.login = login;
        this.password = password;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        User us = (User)obj;
        return Objects.equals(this.login, us.login) && Objects.equals(this.status, us.status);
    }
}