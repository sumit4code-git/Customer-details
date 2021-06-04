package com.example.myapplication;

public class Customer_Model {
    int id;
    String name;
    int age;
    boolean isactive;

    public Customer_Model(int id, String name, int age, boolean isactive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isactive = isactive;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isactive=" + isactive ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
