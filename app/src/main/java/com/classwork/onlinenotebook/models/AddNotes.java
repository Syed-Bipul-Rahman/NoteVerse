package com.classwork.onlinenotebook.models;

import java.util.Date;

public class AddNotes {

    String userNote;
    String todayDate;

    public AddNotes(String userNote, String todayDate) {
        this.userNote = userNote;
        this.todayDate = todayDate;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }
}
