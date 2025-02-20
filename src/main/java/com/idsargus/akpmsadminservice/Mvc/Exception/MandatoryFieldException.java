package com.idsargus.akpmsadminservice.Mvc.Exception;

public class MandatoryFieldException extends RuntimeException {
    public MandatoryFieldException(String message) {
        super(message);
    }
}
