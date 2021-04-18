package com.springboot.wine.store.dtos;


import com.springboot.wine.store.common.Utils.CommonUtils;
import com.springboot.wine.store.common.Utils.Constants;
import org.springframework.http.HttpStatus;

public class ResponseDTO {

    public static ResponseHandler responseSuccessful() {
        return new ResponseHandler(new ResponseMessage(String.valueOf(HttpStatus.OK.value()),
                Constants.SUCCESS));
    }

    public static ResponseHandler responseSuccessful(Object data) {

        if (CommonUtils.isNullOrEmpty(data))
        {
            return new ResponseHandler(new ResponseMessage((String.valueOf(HttpStatus.NO_CONTENT.value())),
                    Constants.RECORD_NOT_FOUND));
        }
        else {
            return new ResponseHandler(data);
        }
    }
}
