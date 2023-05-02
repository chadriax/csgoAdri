package com.csgo.exceptions;

public class NotEnoughBalanceException extends RuntimeException{

    public NotEnoughBalanceException(String message){
        super(message);
    }

}
