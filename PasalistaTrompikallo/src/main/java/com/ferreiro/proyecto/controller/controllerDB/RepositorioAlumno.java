package com.ferreiro.proyecto.controller.controllerDB;

import com.ferreiro.proyecto.model.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepositorioAlumno implements Respositorio<Alumno> {


    public RepositorioAlumno(Session sesion) {

        this.sesion = sesion;

    }

    public void guardar(Alumno alumno) {
    	
        tx = sesion.beginTransaction();
        sesion.persist(alumno);
        tx.commit();
        System.out.println("Alumno guardado en la base de datos: " + alumno.getIdAlumno() + " " + alumno.getNombre() + " " + alumno.getApellidos());
    }


    public void actualizar(Alumno alumno){

        tx = sesion.beginTransaction();
        sesion.merge(alumno);
        tx.commit();
        System.out.println("Alumno actualizado correctamente");
    }

    public void actualizar(Alumno alumno, String nombre, String apellidos) {

        tx = sesion.beginTransaction();
        alumno.setNombre(nombre);
        alumno.setApellidos(apellidos);
        sesion.merge(alumno);
        tx.commit();
        System.out.println("Alumno actualizado correctamente");
    }


    public void eliminar(Alumno alumno) {
    	
        Transaction tx = sesion.beginTransaction();
        sesion.remove(alumno);
        tx.commit();
        System.out.println("Alumno eliminado de la base de datos: " +  alumno.getNombre() + " " + alumno.getApellidos());
    }


    public Alumno recuperarPorId(int id) throws EmpleadoNoexisteException { //cambiar esta excepcion

        alumno = sesion.find(Alumno.class, id);
        return alumno;
    }

    public Alumno recuperarPorNombreApellidos(String nombre, String apellidos) throws AlumnoNoExisteException {
        try {
            Query<Alumno> query = sesion.createQuery("FROM Alumno WHERE nombre = :nombre AND apellidos = :apellidos", Alumno.class);
            query.setParameter("nombre", nombre);
            query.setParameter("apellidos", apellidos);
            alumno = query.uniqueResult();
            System.out.println("Alumno recuperado de la base de datos: " + alumno.toString());

        } catch (Exception e) {
            throw new AlumnoNoExisteException();
        }
        return alumno;
    }

    public List<Alumno> recuperarTodos() {
        Query<Alumno> query = sesion.createQuery("FROM Alumno", Alumno.class);
        List<Alumno> alumnos = query.getResultList();
        return alumnos;
    }

    public Set<Asignatura> recuperarAsignaturas(Alumno alumno) {
        try {

            //Obtenemos la clase del alumno
            Clase clase = alumno.getClase();

            // Cargamos Set desde BBDD!!--------------------------------------------------------------------------------------------------

            // Consulta para obtener las asignaturas de la clase del alumno
            Query<ProfesorAsignaturaClase> query = sesion.createQuery("FROM ProfesorAsignaturaClase WHERE clase = :claseAlumno", ProfesorAsignaturaClase.class);
            query.setParameter("claseAlumno", clase);

            List<ProfesorAsignaturaClase> profesorAsignaturaClasesLista = query.getResultList();

            Set<Asignatura> asignaturas = new HashSet<Asignatura>();

            for (ProfesorAsignaturaClase pr : profesorAsignaturaClasesLista) {
                asignaturas.add(pr.getAsignatura());
            }
            return asignaturas;
        }catch (Exception e) {
            System.out.println("Error al obtener asignaturas de " + alumno.getNombre() + " " + alumno.getApellidos() + ": " + e.getMessage());
            throw new RuntimeException();
        }
    }

    public Asignatura recuperarAsignaturaPorNombre(Alumno alumno, String nombreAsignatura) throws AsignaturaNoexisteException{

        Set<Asignatura> asignaturas = recuperarAsignaturas(alumno);

        Asignatura asignatura = null;
        for(Asignatura as : asignaturas){
            if(as.getNombre().equals(nombreAsignatura)){
                asignatura = as;
                break;
            }
        }
        if(asignatura == null){
            throw new AsignaturaNoexisteException();
        }
        else
        return asignatura;
    }

    public ProfesorAsignaturaClase recuperarPAC (Alumno alumno, String nombreAsignatura) {

        RepositorioClase repositorioClase = new RepositorioClase(sesion);
        Clase claseAlumno = alumno.getClase();

        return  repositorioClase.recuperarPAC(claseAlumno, nombreAsignatura);
    }

    public ProfesorAsignaturaClase recuperarPAC (Alumno alumno, Asignatura asignatura) {

        RepositorioClase repositorioClase = new RepositorioClase(sesion);
        Clase claseAlumno = alumno.getClase();

        return  repositorioClase.recuperarPAC(claseAlumno, asignatura);
    }

    public Profesor recuperarProfesorAsignatura(Alumno alumno, Asignatura asignatura) throws ClaseNoAsignaturaException{

        Profesor profesor = null;

        Clase claseAlumno = alumno.getClase();

        RepositorioClase repositorioClase = new RepositorioClase(sesion);
        Set<ProfesorAsignaturaClase> profesorAsignaturaClases = repositorioClase.recuperarPACs(claseAlumno);

        for(ProfesorAsignaturaClase pac : profesorAsignaturaClases){
            if(pac.getAsignatura().equals(asignatura)){
                profesor = pac.getProfesor();
                break;
            }
        }
        if(profesor == null){
            throw new ClaseNoAsignaturaException(asignatura);
        }
        else return profesor;
    }

    public void cerrarSesion() {
        sesion.close();
    }

    private Transaction tx;
    private Alumno alumno;
    private final Session sesion;

}
