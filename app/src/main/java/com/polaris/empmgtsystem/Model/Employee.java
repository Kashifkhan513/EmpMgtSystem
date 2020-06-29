package com.polaris.empmgtsystem.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Employee implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String name;
    String designation;
    String age;
    String salary;
    String address;

    public Employee(String name, String designation, String age, String salary, String address) {
        this.name = name;
        this.designation = designation;
        this.age = age;
        this.salary = salary;
        this.address = address;
    }

    public Employee(int id, String name, String designation, String age, String salary, String address) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.age = age;
        this.salary = salary;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NonNull
    @Override
    public String toString() {
        return name.toString();
    }
}
