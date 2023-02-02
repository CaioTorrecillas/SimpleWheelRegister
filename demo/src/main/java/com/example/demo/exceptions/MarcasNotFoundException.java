package com.example.demo.exceptions;

public class MarcasNotFoundException extends RuntimeException {

    public MarcasNotFoundException(){
        super();
    }
    public String marcaErrorWarningMessage(){
        return "Roda nao encontrada";
    }
}
