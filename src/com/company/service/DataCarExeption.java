package com.company.service;

public class DataCarExeption extends Exception{
    DataCarExeption(){}
    private TypeExeption typeExeption;
    public TypeExeption getTypeExeption() {
        return typeExeption;
    }
    public DataCarExeption(String e, TypeExeption typeExeption){
        super(e);
        this.typeExeption = typeExeption;
    }
}
