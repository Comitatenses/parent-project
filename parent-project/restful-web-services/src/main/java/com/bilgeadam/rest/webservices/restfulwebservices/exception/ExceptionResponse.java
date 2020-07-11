package com.bilgeadam.rest.webservices.restfulwebservices.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {

    private Date timeStamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timeStamp, String message, String details) {
        super();
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }
}