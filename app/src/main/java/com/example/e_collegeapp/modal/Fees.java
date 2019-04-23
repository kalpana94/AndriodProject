package com.example.e_collegeapp.modal;

public class Fees {
    public String newstu;
    public  String transferstu;

    public Fees() {
    }

    public Fees(String newstu, String transferstu) {
        this.newstu = newstu;
        this.transferstu = transferstu;
    }

    @Override
    public String toString() {
        return "Fees{" +
                "newstu='" + newstu + '\'' +
                ", transferstu='" + transferstu + '\'' +
                '}';
    }
}
