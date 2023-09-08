package com.classwork.onlinenotebook.models;

public class AllNotes {
    int id;
    String notes;
    String updateat;

    public AllNotes(int id, String notes, String updateat) {
        this.id = id;
        this.notes = notes;
        this.updateat = updateat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUpdateat() {
        return updateat;
    }

    public void setUpdateat(String updateat) {
        this.updateat = updateat;
    }
}
