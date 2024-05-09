package com.ferreiro.proyecto.ui;

import com.ferreiro.proyecto.model.entities.Alumno;
import com.ferreiro.proyecto.model.entities.Profesor;
import org.hibernate.Session;

public class PanelAlumnoProfesor extends PanelAlumnoParent {
	
    public PanelAlumnoProfesor(Profesor profesor, Alumno alumno, Session sesion) {
    
        super(profesor, alumno,sesion);
        botonEliminarAlumno.setEnabled(false);
    }
}
