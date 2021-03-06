package com.example.e_collegeapp.modal;

public class Student1 {
    public String docID;
    public String Name;
    public String Email;
    public String Password;
    public String StudentType;
    public String Contact;
    public String DateOfBirth;
    public String Gender;
    public String FatherName;
    public String MotherName;
    public String Religion;
    public String Nationality;
    public String City;
    public String State;
    public String GuardianName;
    public String GuardianContact;
    public String CollegeName;
    public String CourseName;
    public String Matricpercent;
    public String MatricYOP;
    public String TwelthPercent;
    public String TwelthYOP;
    public String MatricBoard;
    public String TwelthBoard;
    public String OtherQual;
    public String OtherPercent;
    public String PermanentAddress;
    public String Pincode;
    public String BatchYear;

    public Student1() {
    }


    public Student1(String name, String email, String password, String studentType) {
        Name = name;
        Email = email;
        Password = password;
        StudentType = studentType;
    }

    public Student1(String docID, String name, String email, String password, String studentType) {
        this.docID = docID;
        Name = name;
        Email = email;
        Password = password;
        StudentType = studentType;
    }

    public Student1(String docID, String name, String email, String password, String studentType, String contact, String dateOfBirth, String gender, String fatherName, String motherName, String religion, String nationality, String city, String state, String guardianName, String guardianContact, String collegeName, String courseName, String matricpercent, String matricYOP, String twelthPercent, String twelthYOP, String matricBoard, String twelthBoard, String otherQual, String otherPercent, String permanentAddress, String pincode, String batchYear) {
        this.docID = docID;
        Name = name;
        Email = email;
        Password = password;
        StudentType = studentType;
        Contact = contact;
        DateOfBirth = dateOfBirth;
        Gender = gender;
        FatherName = fatherName;
        MotherName = motherName;
        Religion = religion;
        Nationality = nationality;
        City = city;
        State = state;
        GuardianName = guardianName;
        GuardianContact = guardianContact;
        CollegeName = collegeName;
        CourseName = courseName;
        Matricpercent = matricpercent;
        MatricYOP = matricYOP;
        TwelthPercent = twelthPercent;
        TwelthYOP = twelthYOP;
        MatricBoard = matricBoard;
        TwelthBoard = twelthBoard;
        OtherQual = otherQual;
        OtherPercent = otherPercent;
        PermanentAddress = permanentAddress;
        Pincode = pincode;
        BatchYear = batchYear;
    }

    @Override
    public String toString() {
        return "Student1{" +
                "docID='" + docID + '\'' +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", StudentType='" + StudentType + '\'' +
                ", Contact='" + Contact + '\'' +
                ", DateOfBirth='" + DateOfBirth + '\'' +
                ", Gender='" + Gender + '\'' +
                ", FatherName='" + FatherName + '\'' +
                ", MotherName='" + MotherName + '\'' +
                ", Religion='" + Religion + '\'' +
                ", Nationality='" + Nationality + '\'' +
                ", City='" + City + '\'' +
                ", State='" + State + '\'' +
                ", GuardianName='" + GuardianName + '\'' +
                ", GuardianContact='" + GuardianContact + '\'' +
                ", CollegeName='" + CollegeName + '\'' +
                ", CourseName='" + CourseName + '\'' +
                ", Matricpercent='" + Matricpercent + '\'' +
                ", MatricYOP='" + MatricYOP + '\'' +
                ", TwelthPercent='" + TwelthPercent + '\'' +
                ", TwelthYOP='" + TwelthYOP + '\'' +
                ", MatricBoard='" + MatricBoard + '\'' +
                ", TwelthBoard='" + TwelthBoard + '\'' +
                ", OtherQual='" + OtherQual + '\'' +
                ", OtherPercent='" + OtherPercent + '\'' +
                ", PermanentAddress='" + PermanentAddress + '\'' +
                ", Pincode='" + Pincode + '\'' +
                ", BatchYear='" + BatchYear + '\'' +
                '}';
    }
}
