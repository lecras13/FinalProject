package com.epam.web.exception;

public class ValidatorException extends Exception {
    public ValidatorException(){
        super();
    }

    public ValidatorException(String message){
        super(message);
    }

    public ValidatorException(Throwable cause){
        super(cause);
    }
}
