package com.ferreiro.proyecto.model.auxiliar;

import com.ferreiro.proyecto.controller.controllerDB.HibernateUtil;
import com.ferreiro.proyecto.model.entities.Asignatura;
import com.ferreiro.proyecto.model.entities.Clase;
import com.ferreiro.proyecto.model.entities.Profesor;
import com.ferreiro.proyecto.model.entities.ProfesorAsignaturaClase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class SetComunPAC {


    public static Set<ProfesorAsignaturaClase> getSetComun() {
        return setComun;
    }

    //Set comun para todos
    public static void agregarPAC(ProfesorAsignaturaClase pac) {
        setComun.add(pac);
    }

    public static void asignarProfesorAsignaturaClase(Profesor profesor, Asignatura asignatura, Clase clase) {
        ProfesorAsignaturaClase pac = new ProfesorAsignaturaClase();
        pac.setProfesor(profesor);
        pac.setAsignatura(asignatura);
        pac.setClase(clase);

        //Mirar si existe en el pricipal----Aunque el set ya no lo añadiría porque no admite duplicados, pero la bbdd sí porque el id sería difrerente
        if(!setComun.contains(pac)) {

            clase.getProfesoresAsignaturasClases().add(pac);
            profesor.getProfesorAsignaturaClases().add(pac);
            agregarPAC(pac);

            //Esto lo haremos en Repo de profes, es solo para probar ---------------------------------------------------------
            try {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();
                session.persist(pac);
                transaction.commit();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Esta asignatura ya está asignada en esta clase a un profesor (" + pac.getAsignatura() + " " + pac.getClase() + ")");
        }

    }
    public static void mostrarSetComun() {
        for (ProfesorAsignaturaClase pr : SetComunPAC.getSetComun()) {
            System.out.println(pr.getAsignatura().getNombre() + " " + pr.getClase().toString() + " " + pr.getProfesor().getNombre());
        }
    }

    public static void AsignaturasSetComun() {
        //Almacena las asignaturas (al ser un set, no admite repetidos)
        Set<Asignatura> asignaturas = new HashSet<Asignatura>();
        for (ProfesorAsignaturaClase pr : SetComunPAC.getSetComun()) {
            asignaturas.add(pr.getAsignatura());
        }
        //Mostramos las asignaturas
        for (Asignatura as : asignaturas) {
            System.out.println(as.getNombre());
        }

    }
        static Set<ProfesorAsignaturaClase> setComun = new HashSet<ProfesorAsignaturaClase>();



}
