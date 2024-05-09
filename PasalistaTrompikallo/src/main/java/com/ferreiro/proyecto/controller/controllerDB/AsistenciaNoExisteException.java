package com.ferreiro.proyecto.controller.controllerDB;

public class AsistenciaNoExisteException extends Exception {
	
    public AsistenciaNoExisteException() {
        super("No existe ese registro de asistencia");
    }
}
