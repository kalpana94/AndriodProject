package com.example.e_collegeapp.modal;

public class States {
    public String doc_id;
    public String Name;

    public States() {
    }

    public States(String doc_id, String name) {
        this.doc_id = doc_id;
        Name = name;
    }

    @Override
    public String toString() {
        return "States{" +
                "doc_id='" + doc_id + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
