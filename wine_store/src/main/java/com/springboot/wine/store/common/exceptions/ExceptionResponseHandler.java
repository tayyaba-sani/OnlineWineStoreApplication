package com.springboot.wine.store.common.exceptions;


import com.springboot.wine.store.dtos.ExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.util.Date;

@ControllerAdvice
public class ExceptionResponseHandler {
    Logger logger = LoggerFactory.getLogger(ExceptionResponseHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionDTO> HandleException(Exception ex,
                                                               WebRequest webRequest)
    {
        if(ex instanceof BusinessCaseException) {
            return exceptionHandler((BusinessCaseException) ex,webRequest);
        }
        else if(ex instanceof EmailException)
        {
            return exceptionHandler((EmailException) ex,webRequest);
        }
        else {
            logger.error("Exception Occured "+ ex);
            ExceptionDTO exceptionDTO = new ExceptionDTO(new Date(), ex.getMessage(), webRequest.getDescription(false));
            return new ResponseEntity<ExceptionDTO>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ExceptionDTO> exceptionHandler(BusinessCaseException exception,WebRequest webRequest) {
        logger.error("Business Case Exception Fired in "+exception.getClassName(), exception);
        ExceptionDTO exceptionDTO = new ExceptionDTO(new Date(), exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
    private ResponseEntity<ExceptionDTO> exceptionHandler(EmailException exception,WebRequest webRequest) {
        logger.error("EmailException Fired in "+exception.getClassName(), exception);
        ExceptionDTO exceptionDTO = new ExceptionDTO(new Date(), exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
