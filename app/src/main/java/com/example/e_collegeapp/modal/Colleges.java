package com.example.e_collegeapp.modal;

public class Colleges {
    public String docID;
    public String name;
    public String email;
    public String password;
    public String city;
    public String state;

    public Colleges() {
    }

    public Colleges(String name, String email, String password, String city,String state) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.city=city;
        this.state=state;
    }

    public Colleges(String docID, String name, String email, String password, String city, String state) {
        this.docID = docID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Colleges{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
