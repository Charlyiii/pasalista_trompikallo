package com.ferreiro.proyecto.controller.controllerDB;

public class AsignaturaNoexisteException extends Exception {
	
    public AsignaturaNoexisteException() {
        super("La asignatura no existe");
    }
}
