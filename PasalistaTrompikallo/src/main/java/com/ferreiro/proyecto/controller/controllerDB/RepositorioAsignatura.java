package com.ferreiro.proyecto.controller.controllerDB;

import com.ferreiro.proyecto.model.entities.Asignatura;
import com.ferreiro.proyecto.model.entities.Clase;
import com.ferreiro.proyecto.model.entities.ProfesorAsignaturaClase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepositorioAsignatura implements Respositorio<Asignatura> {

    public RepositorioAsignatura(Session sesion) {

        this.sesion = sesion;
    }

    public void guardar(Asignatura asignatura) {

        tx = sesion.beginTransaction();
        sesion.persist(asignatura);
        tx.commit();
        System.out.println("Asignatura guardada en la base de datos: " + asignatura.getNombre());
    }

    public void actualizar(Asignatura asignatura) {

        tx = sesion.beginTransaction();
        sesion.merge(asignatura);
        tx.commit();
        System.out.println("Asignatura actualizada: " + asignatura.getNombre());
    }

    //Sobrecarga con parámetros
    public void actualizar(Asignatura asignatura, String nombre, String abreviatura, int horas) {

        tx = sesion.beginTransaction();
        asignatura.setNombre(nombre);
        asignatura.setAbreviatura(abreviatura);
        asignatura.setHoras(horas);
        sesion.merge(asignatura);
        tx.commit();
        System.out.println("Asignatura actualizada: " + asignatura.getNombre());

    }

    public void eliminar(Asignatura asignatura) {

        tx = sesion.beginTransaction();
        sesion.remove(asignatura);
        tx.commit();
        System.out.println("Asignatura eliminada de la base de datos: " + asignatura.getNombre());
    }


    public Asignatura recuperarPorId(int id) throws EmpleadoNoexisteException {
        asignatura = sesion.find(Asignatura.class, id);
        return asignatura;
    }

    public Asignatura recuperarPorNombre(String nombre) throws AsignaturaNoexisteException {
        try {

            Query<Asignatura> query = sesion.createQuery("FROM Asignatura WHERE nombre = :nombre", Asignatura.class);
            query.setParameter("nombre", nombre);
            asignatura = query.uniqueResult();
            System.out.println("Asignatura recuperado de la base de datos: " + asignatura.getNombre());


        } catch (Exception e) {
            e.printStackTrace();
            throw new AsignaturaNoexisteException();
        }
        return asignatura;
    }

    public List<Asignatura> recuperarTodos() {

        Query<Asignatura> query = sesion.createQuery("FROM Asignatura", Asignatura.class);
        List<Asignatura> lista = query.list();
        return lista;
    }

    public Set<Asignatura> recuperarPorClase(Clase clase) {
        repositorioPAC = new RepositorioPAC(sesion);
        Set<ProfesorAsignaturaClase> lista = repositorioPAC.recuperarPACsClase(clase);
        asignaturas = new HashSet<Asignatura>();

        for (ProfesorAsignaturaClase pac : lista) {
            asignaturas.add(pac.getAsignatura());
        }
        return asignaturas;
    }

    public void cerrarSesion() {
        sesion.close();
    }

    private Asignatura asignatura;
    private Session sesion;
    private Transaction tx;
    Set<Asignatura> asignaturas;
    RepositorioPAC repositorioPAC;
}
