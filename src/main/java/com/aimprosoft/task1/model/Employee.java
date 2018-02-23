package com.aimprosoft.task1.model;

import java.util.Date;

public class Employee {

    private long id;
    private String email;
    private String name;
    private Date birthday;
    private int room;
    private String departmentName;

    public Employee() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", room=" + room +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
