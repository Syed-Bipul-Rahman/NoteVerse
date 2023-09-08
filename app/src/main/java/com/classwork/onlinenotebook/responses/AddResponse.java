package com.classwork.onlinenotebook.responses;

public class AddResponse {
    String notes;
    String date;

    public AddResponse(String notes, String date) {
        this.notes = notes;
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
