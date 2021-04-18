package com.springboot.wine.store.common.exceptions;



public class BusinessCaseException extends RuntimeException{

        private String className;

    public BusinessCaseException(String message, String className) {
        super(message);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
