package com.ferreiro.proyecto.controller.controllerDB;

public class AlumnoNoExisteException extends Exception{
	
    public AlumnoNoExisteException() {
        super("El alumno no existe");
    }
}


