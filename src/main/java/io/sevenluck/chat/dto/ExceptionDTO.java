/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.dto;

/**
 *
 * @author loki
 */
public class ExceptionDTO {
    
    private String message;
    private ErrorType type;
    
    public static enum ErrorType {
        ERROR, CONFLICT, VALIDATION, UNAUTHORIZED
    }
    
    private ExceptionDTO(ErrorType type, String message) {
        this.type = type;
        this.message = message;
    }
    
    public static ExceptionDTO newUnauthorizedInstance(final String message) {
        return new ExceptionDTO(ErrorType.UNAUTHORIZED, message);
    }
    
    public static ExceptionDTO newValidationInstance(final String message) {
        return new ExceptionDTO(ErrorType.CONFLICT, message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorType getType() {
        return type;
    }

    public void setType(ErrorType type) {
        this.type = type;
    }
    
}
