package com.example.ju_cse_short_circuit;

public class Ass {
    private String Course;

    private String Details;
    private String Due;




    // Default constructor (required for Firestore)
    public Ass() {
        // Default constructor required for Firestore
    }

    public Ass(String Details, String Course, String Due) {
        this.Details = Details;
        this.Course = Course;
        this.Due = Due;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String Details) {
        this.Details = Details;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String Course) {
        this.Course = Course;
    }

    public String getDue() {
        return Due;
    }

    public void setDue(String Due) {
        this.Due = Due;
    }
}
