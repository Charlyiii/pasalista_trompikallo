package com.ferreiro.proyecto.controller.controllerDB;

import com.ferreiro.proyecto.model.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.*;

public class RepositorioProfesor implements Respositorio<Profesor> {

    public RepositorioProfesor(Session sesion) {

        this.sesion = sesion;
    }

    public void guardar(Profesor profesor) {
    	
        tx = sesion.beginTransaction();
        sesion.persist(profesor);
        tx.commit();
        System.out.println("Profesor guardado en la base de datos: " + profesor.getUsuario());
    }

    public void actualizar(Profesor profesor) {
    	
        tx = sesion.beginTransaction();
        sesion.merge(profesor);
        tx.commit();
        System.out.println("Profesor actualizado correctamente");
    }

    public void actualizar(Profesor profesor, String nombre, String apellidos, String email, String usuario, String password ) {

        tx = sesion.beginTransaction();
        profesor.setNombre(nombre);
        profesor.setApellidos(apellidos);
        profesor.setEmail(email);
        profesor.setUsuario(usuario);
        profesor.setPassword(password);
        sesion.merge(profesor);
        tx.commit();
        System.out.println("Profesor actualizado: " + profesor.getUsuario());
    }

    public void eliminar(Profesor profesor) {

        tx = sesion.beginTransaction();
        sesion.remove(profesor);
        tx.commit();
        System.out.println("Profesor eliminado de la base de datos: " + profesor.getUsuario());
    }

    public Profesor recuperarPorId(int id) throws EmpleadoNoexisteException {
    	
        profesor = sesion.find(Profesor.class, id);
        return profesor;
    }


    public Profesor recuperarPorUsuario(String usuario) throws EmpleadoNoexisteException {
        try {
            Query<Profesor> query = sesion.createQuery("FROM Profesor WHERE usuario = :usuario", Profesor.class);
            query.setParameter("usuario", usuario);
            profesor = query.uniqueResult();
            System.out.println("Profesor recuperado de la base de datos: " + profesor.getUsuario());
        } catch (Exception e) {
            throw new EmpleadoNoexisteException();
        }
        return profesor;
    }
    public Profesor recuperarPorClaseAsignatura(Clase clase, Asignatura asignatura) throws EmpleadoNoexisteException {

        RepositorioPAC repositorioPAC = new RepositorioPAC(sesion);
        ProfesorAsignaturaClase pac = repositorioPAC.recuperarPacClaseAsignatura(clase, asignatura);
        Profesor profesor = pac.getProfesor();
        return profesor;
    }

    public List<Profesor> recuperarTodos() {
    	
        List<Profesor> profesores;
        Query<Profesor> query = sesion.createQuery("FROM Profesor", Profesor.class);
        profesores = query.getResultList();
        Collections.sort(profesores);
        return profesores;
    }

    public void asignarPAC(Profesor profesor, Asignatura asignatura, Clase clase) {
        try {
            ProfesorAsignaturaClase pac = new ProfesorAsignaturaClase(profesor, asignatura, clase);
            RepositorioPAC repositorioPAC = new RepositorioPAC(sesion);
            repositorioPAC.guardar(pac);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarTodoPAC() {

        RepositorioPAC repositorioPAC = new RepositorioPAC(sesion);
        Set<ProfesorAsignaturaClase> pacs = repositorioPAC.recuperarPACs();
        for (ProfesorAsignaturaClase pac : pacs) {
            System.out.println(pac);
        }
    }

    public Set<Asignatura> recuperarAsignaturasProfesor(Profesor profesor) throws NoHayDatosProfesor {
        //Almacena las asignaturas (al ser un set, no admite repetidos)

        try {
            tx = sesion.beginTransaction();

            // Cargamos Set desde BBDD!!--------------------------------------------------------------------------------------------------

            Set<ProfesorAsignaturaClase> setAsignaturas = new RepositorioPAC(sesion).recuperarPACprofesor(profesor);

            Set<Asignatura> asignaturas = new HashSet<Asignatura>();

            for (ProfesorAsignaturaClase pr : setAsignaturas) {
                asignaturas.add(pr.getAsignatura());
            }

            tx.commit();
            return asignaturas;

        } catch (
                Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Set<Clase> recuperarClasesProfesor(Profesor profesor) throws NoHayDatosProfesor {
        //Almacena las asignaturas (al ser un set, no admite repetidos)

        try {
            // Cargamos Set del profesor desde el repo de PAC

            Set<ProfesorAsignaturaClase> setClases = new RepositorioPAC(sesion).recuperarPACprofesor(profesor);

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

    public Set<Clase> recuperarClasesProfesorPorAsignatura(Profesor profesor, Asignatura asignatura) throws NoHayDatosProfesor {

        try {

            Set<ProfesorAsignaturaClase> setClases = new RepositorioPAC(sesion).recuperarPACprofesorPorAsignatura(profesor, asignatura);

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


    public List<Alumno> recuperarAlumnosProfesor(Profesor profesor) throws NoHayDatosProfesor {
        //Almacena las asignaturas (al ser un set, no admite repetidos)

        //Eliminamos cualquier contenido que pueda tener el set
        alumnos.clear();
        //Accedemos al repositorio de las clases para obtener los alumnos de cada clase
        RepositorioClase repositorioClase = new RepositorioClase(sesion);

        Set<Clase> clases = recuperarClasesProfesor(profesor);
        //vamos añadiendo los alumnos de cada clase al set de alumnos
        for (Clase clase : clases) {
            List<Alumno> alumnosClase = repositorioClase.recuperarAlumnos(clase);
            alumnos.addAll(alumnosClase);
        }
        //Devolvemos una lista con los alumnos porque vamos a querer ordenarlos alfabeticamente y no se puede con un set
        return alumnos;
    }

    public List<Asignatura> asignaturasAlumno(Profesor profesor, Alumno alumno) {

        //Recuperamos la clase del alumno para saber que ProfesorAsignaturaClases tiene y cuales son del profesor
        Clase claseAlumno = alumno.getClase();
        RepositorioClase repositorioClase = new RepositorioClase(sesion);
        Set<ProfesorAsignaturaClase> profesorAsignaturaClases = repositorioClase.recuperarPACs(claseAlumno);

        //Creamos una lista para almacenar las asignaturas que tiene el alumno con el profesor
        List<Asignatura> asignaturasAlumno = new ArrayList<Asignatura>();

        //Recorremos el set de ProfesorAsignaturaClases para ir añadiendo las asignaturas a la lista
        for (ProfesorAsignaturaClase profesorAsignaturaClase : profesorAsignaturaClases) {
            if (profesorAsignaturaClase.getProfesor().equals(profesor)) {
                asignaturasAlumno.add(profesorAsignaturaClase.getAsignatura());
            }
        }
        return asignaturasAlumno;
    }

    public Session getSesion() {
        return sesion;
    }

    public Transaction getTx() {
        return tx;
    }

    public void cerrarSesion() {
        sesion.close();
    }

    private List<Alumno> alumnos = new ArrayList<Alumno>();
    private Profesor profesor;
    private Session sesion;
    private Transaction tx;


}
