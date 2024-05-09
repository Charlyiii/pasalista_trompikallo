package com.ferreiro.proyecto.controller.controllerDB;

public class PassworIncorrectaException extends Exception{
	
    public PassworIncorrectaException() {
        super("La contraseña es incorrecta");
    }
}
