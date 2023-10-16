package com.luizreis.blogspring.services.exceptions;

public class IllegalOperation extends RuntimeException{

    public IllegalOperation(String msg){
        super(msg);
    }
}