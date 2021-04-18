package com.springboot.wine.store.dtos;

import com.springboot.wine.store.common.Utils.Constants;
import org.springframework.http.HttpStatus;

public class ResponseHandler<T> {

    private T data;
    private ResponseMessage message;

    public ResponseHandler() {
        this.message = new ResponseMessage(String.valueOf(HttpStatus.OK.value()),
                Constants.SUCCESS);
    }
    public ResponseHandler(T data) {
        this.message = new ResponseMessage(String.valueOf(HttpStatus.OK.value()),
                Constants.SUCCESS);
        this.data = data;
    }
    public ResponseHandler(ResponseMessage message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseMessage getMessage() {
        return message;
    }

    public void setMessage(ResponseMessage message) {
        this.message = message;
    }
}
