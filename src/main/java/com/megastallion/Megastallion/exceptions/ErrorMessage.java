package com.megastallion.Megastallion.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage {
    public HttpStatus status;
    public String message;
    public String debugMessage;
}
