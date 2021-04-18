package com.springboot.wine.store.dtos;

import java.util.Date;

public class ExceptionDTO {
    private Date timestamp;
    private String errorMessage;
    private String errorDescription;

    public ExceptionDTO(Date timestamp, String errorMessage, String errorDescription) {
        this.timestamp = timestamp;
        this.errorMessage = errorMessage;
        this.errorDescription = errorDescription;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
