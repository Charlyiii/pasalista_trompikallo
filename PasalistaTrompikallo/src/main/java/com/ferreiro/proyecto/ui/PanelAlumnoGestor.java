package com.ferreiro.proyecto.ui;

import com.ferreiro.proyecto.controller.controllerUI.EliminarAlumno;
import com.ferreiro.proyecto.model.entities.Alumno;
import com.ferreiro.proyecto.model.entities.Gestor;
import org.hibernate.Session;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAlumnoGestor extends PanelAlumnoParent {
    public PanelAlumnoGestor(final Gestor gestor, final Alumno alumno, Session sesion) {
        super(gestor, alumno, sesion);

        session = sesion;
        
        //Eventos
        botonEliminarAlumno.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EliminarAlumno eliminarAlumno = new EliminarAlumno(session, alumno, gestor, panelAlumnoGestor);			
			}
		});;
    }
      
    Session session;
    PanelAlumnoGestor panelAlumnoGestor = this;
    
}