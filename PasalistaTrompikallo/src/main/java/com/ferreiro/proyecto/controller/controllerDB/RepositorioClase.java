package com.ferreiro.proyecto.controller.controllerDB;

import com.ferreiro.proyecto.model.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.swing.*;
import java.util.*;

public class RepositorioClase implements Respositorio<Clase> {
    public RepositorioClase(Session sesion) {

        this.sesion = sesion;
    }

    public void guardar(Clase clase) {

        Transaction tx = sesion.beginTransaction();
        sesion.persist(clase);
        tx.commit();
        System.out.println("Clase guardada en la base de datos: " + clase.toString());
    }

    public void actualizar(Clase clase) {

        Transaction tx = sesion.beginTransaction();
        sesion.merge(clase);
        tx.commit();
        System.out.println("Clase actualizada correctamente");
    }

    public void eliminar(Clase clase) {

        tx = sesion.beginTransaction();
        sesion.remove(clase);
        tx.commit();
        System.out.println("Clase eliminada de la base de datos: " + clase.toString());
    }

    //Sin funcionalidad en este clase
    public Clase recuperarPorId(int id) throws EmpleadoNoexisteException {
        return null;
    }

    public Clase recuperarPorId(ClaseId claseId) throws ClaseNoExisteException {

        Clase claseDevuelta;
        Query<Clase> query = sesion.createQuery("FROM Clase WHERE idClase = :idClase", Clase.class);
        query.setParameter("idClase", claseId);
        claseDevuelta = query.uniqueResult();
        if(claseDevuelta == null){
            throw new ClaseNoExisteException();
        }
        else {
            System.out.println("Clase recuperado de la base de datos: " + claseDevuelta);
            return claseDevuelta;
        }
    }

    public Clase recuperarPorNombre(int curso, char clase, String nivel) throws ClaseNoExisteException {

        Clase claseDevuelta;

        return recuperarPorId(new ClaseId(curso, clase, nivel));
    }

    public Set<Clase> recuperarPorAsignatura(Asignatura asignatura){

        try {

            Set<ProfesorAsignaturaClase> setClases = new RepositorioPAC(sesion).recuperarPACasignatura(asignatura);

            Set<Clase> clases = new HashSet<Clase>();

            for (ProfesorAsignaturaClase pr : setClases) {
                clases.add(pr.getClase());
            }

            return clases;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Clase> recuperarTodos() {

        Query<Clase> query = sesion.createQuery("FROM Clase", Clase.class);
        List<Clase> lista = query.list();
        return lista;
    }

    public void asignarAsignaturaCursoProfesor(Asignatura asignatura, Profesor profesor, Clase clase) {
        try {
            ProfesorAsignaturaClase pac = new ProfesorAsignaturaClase(profesor, asignatura, clase);
            RepositorioPAC repositorioPAC = new RepositorioPAC(sesion);
            repositorioPAC.guardar(pac);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    public List<Alumno> recuperarAlumnos(Clase clase) {
        try {
            tx = sesion.beginTransaction();
            Query<Alumno> query = sesion.createQuery("FROM Alumno WHERE clase = :clase", Alumno.class);
            query.setParameter("clase", clase);
            List<Alumno> lista = query.list();
            Collections.sort(lista);
            tx.commit();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Esta clase no tiene alumnos asignados: " + clase.toString());
            }
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Set<ProfesorAsignaturaClase> recuperarPACs(Clase clase) {
        try {
            tx = sesion.beginTransaction();
            Query<ProfesorAsignaturaClase> query = sesion.createQuery("FROM ProfesorAsignaturaClase WHERE clase = :clase", ProfesorAsignaturaClase.class);
            query.setParameter("clase", clase);
            List<ProfesorAsignaturaClase> lista = query.list();
            pac = new HashSet<ProfesorAsignaturaClase>(lista);
            tx.commit();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Esta clase no tiene ni profesores ni asignaturas " + clase.toString());
            }
            return pac;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Recuperar pac por nombre de asignatura
    public ProfesorAsignaturaClase recuperarPAC(Clase clase, String nombreAsignatura) {
        try {

            RepositorioAsignatura repositorioAsignatura = new RepositorioAsignatura(sesion);
            Asignatura asignatura = repositorioAsignatura.recuperarPorNombre(nombreAsignatura);

            Query<ProfesorAsignaturaClase> query = sesion.createQuery("FROM ProfesorAsignaturaClase WHERE clase = :clase AND asignatura = :asignatura", ProfesorAsignaturaClase.class);
            query.setParameter("clase", clase);
            query.setParameter("asignatura", asignatura);

            ProfesorAsignaturaClase pac = query.uniqueResult();

            return pac;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //Recuperar pac por instancia de asignatura
    public ProfesorAsignaturaClase recuperarPAC(Clase clase, Asignatura asignatura) {
        try {
            RepositorioAsignatura repositorioAsignatura = new RepositorioAsignatura(sesion);

            Query<ProfesorAsignaturaClase> query = sesion.createQuery("FROM ProfesorAsignaturaClase WHERE clase = :clase AND asignatura = :asignatura", ProfesorAsignaturaClase.class);
            query.setParameter("clase", clase);
            query.setParameter("asignatura", asignatura);

            ProfesorAsignaturaClase pac = query.uniqueResult();

            return pac;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void cerrarSesion() {
        sesion.close();
    }

    private Clase clase;
    private Set<ProfesorAsignaturaClase> pac;
    private Set<Alumno> alumnos;
    private ProfesorAsignaturaClase profesorAsignaturaClase;
    private Session sesion;
    private Transaction tx;
}

