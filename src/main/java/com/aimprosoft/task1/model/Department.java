package com.aimprosoft.task1.model;

public class Department {

    private long id;
    private String name;
    private String info;

    public Department() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
