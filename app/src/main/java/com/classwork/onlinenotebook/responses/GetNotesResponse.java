package com.classwork.onlinenotebook.responses;

import com.classwork.onlinenotebook.models.AllNotes;

import java.util.List;

public class GetNotesResponse {
    String status;
    String message;
    List<AllNotes>data;

    public GetNotesResponse(String status, String message, List<AllNotes> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AllNotes> getData() {
        return data;
    }

    public void setData(List<AllNotes> data) {
        this.data = data;
    }
}
