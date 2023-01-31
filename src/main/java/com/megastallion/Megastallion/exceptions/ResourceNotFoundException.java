package com.megastallion.Megastallion.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String debugMessage;
    private String resourceName;
    private String fieldName;
    private String fieldValue;


    public ResourceNotFoundException(String debugMessage, String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s:'%s'",resourceName,fieldName,fieldValue));
        this.debugMessage = debugMessage;
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
