package com.ferreiro.proyecto.controller.controllerDB;

import com.ferreiro.proyecto.model.entities.Asignatura;

import javax.swing.*;

public class ClaseNoAsignaturaException extends Exception{

    public ClaseNoAsignaturaException(Asignatura asignatura) {
        super("En esta clase no se imparte esta asignatura: " + asignatura.getNombre());
        JOptionPane.showMessageDialog(null, "En esta clase no se imparte esta asignatura: " + asignatura.getNombre());
    }
}
