package com.ferreiro.proyecto.controller.controllerDB;

import javax.swing.*;

public class NoHayDatosProfesor extends Exception {
	
    public NoHayDatosProfesor() {
        JOptionPane.showMessageDialog(null, "No hay datos asignados a este profesor");
    }
}
