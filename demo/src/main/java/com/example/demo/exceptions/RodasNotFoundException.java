package com.example.demo.exceptions;

public class RodasNotFoundException extends RuntimeException{

    public RodasNotFoundException(){
        super();
    }
    public String errorWarningMessage(){
        return "Roda nao encontrada";
    }
}
