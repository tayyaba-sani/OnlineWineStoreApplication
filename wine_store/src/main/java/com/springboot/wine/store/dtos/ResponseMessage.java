package com.springboot.wine.store.dtos;

public class ResponseMessage {
    private String status;
    private String description;

    public ResponseMessage() {

    }
    public ResponseMessage(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
