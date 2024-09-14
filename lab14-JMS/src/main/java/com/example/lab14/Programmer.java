package com.example.lab14;

import java.io.Serializable;

public class Programmer {
    public String name;
    public String surname;
    public String status;
    public double salary;

    public Programmer(String name, String surname, String status, double salary){
        this.name =name;
        this.surname = surname;
        this.status = status;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString(){
        return name + " " + surname + " - " + status + "(" + salary + ")";
    }
}
