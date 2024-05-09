package com.ferreiro.proyecto.controller.controllerDB;

public class ClaseNoExisteException extends Exception {
    public ClaseNoExisteException() {
        super("La clase no existe");
    }
}
