package com.example.ju_cse_short_circuit;

import java.util.Date;

public class Exam {
    private String Course;
    private String Date;
    private String Name;




    // Default constructor (required for Firestore)
    public Exam() {
        // Default constructor required for Firestore
    }

    public Exam(String Name, String Course, String date) {
        this.Name = Name;
        this.Course = Course;
        this.Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String Course) {
        this.Course = Course;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
}
