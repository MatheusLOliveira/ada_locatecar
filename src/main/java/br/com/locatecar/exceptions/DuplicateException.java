package br.com.locatecar.exceptions;

public class DuplicateException extends RuntimeException{

    public DuplicateException(String msg) {
        super(msg);
    }

}
