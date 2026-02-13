package com.yugmehta.auth_service.model;

public class Student {

    private int id;
    private int marks;
    private String name;

    //here we generated getters,setters,parameterized constructor using ide automatically but we can also do the same by using LOMBOK.
    public Student(int id, int marks, String name) {
        this.id = id;
        this.marks = marks;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", marks=" + marks +
                ", name='" + name + '\'' +
                '}';
    }
}
