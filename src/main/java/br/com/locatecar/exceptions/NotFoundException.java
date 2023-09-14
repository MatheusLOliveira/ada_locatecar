package br.com.locatecar.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String msg) {
        super(msg);
    }

}
