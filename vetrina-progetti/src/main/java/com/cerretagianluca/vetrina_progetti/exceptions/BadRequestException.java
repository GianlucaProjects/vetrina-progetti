package com.cerretagianluca.vetrina_progetti.exceptions;


public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) {
        super(msg);
    }
}
