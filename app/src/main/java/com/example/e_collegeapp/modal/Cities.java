package com.example.e_collegeapp.modal;

public class Cities {
    public String doc_id;
    public String Name;


    public Cities() {
    }

    public Cities(String doc_id, String name) {
        this.doc_id = doc_id;
        Name = name;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "doc_id='" + doc_id + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }



}
