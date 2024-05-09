package com.ferreiro.proyecto.controller.controllerDB;

import javax.swing.*;

public class EmpleadoNoexisteException extends Exception {
	
    public EmpleadoNoexisteException() {
        super("El empleado no existe");
        JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
    }
}
