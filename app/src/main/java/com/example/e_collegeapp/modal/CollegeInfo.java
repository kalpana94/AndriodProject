package com.example.e_collegeapp.modal;

public class CollegeInfo {
   public String info;

    public CollegeInfo() {
    }

    public CollegeInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "CollegeInfo{" +
                "info='" + info + '\'' +
                '}';
    }
}
