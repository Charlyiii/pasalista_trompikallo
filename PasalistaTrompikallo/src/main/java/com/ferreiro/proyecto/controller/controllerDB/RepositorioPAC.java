package com.ferreiro.proyecto.controller.controllerDB;

import com.ferreiro.proyecto.model.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepositorioPAC implements Respositorio<ProfesorAsignaturaClase> {

    public RepositorioPAC(Session sesion) {

        this.sesion = sesion;
    }


    public void guardar(ProfesorAsignaturaClase pac) {

        try {
            //Cargamos el set desde BBDD

            List<ProfesorAsignaturaClase> profesorAsignaturaClasesLista = sesion.createQuery("FROM ProfesorAsignaturaClase").getResultList();
            Set<ProfesorAsignaturaClase> profesorAsignaturaClases = new HashSet<ProfesorAsignaturaClase>(profesorAsignaturaClasesLista);

            if (!profesorAsignaturaClases.contains(pac)) {
                tx = sesion.beginTransaction();
                sesion.persist(pac);
                tx.commit();

            } else {
                JOptionPane.showMessageDialog(null, "Esta asignatura ya está asignada en esta clase a un profesor (" + pac.getAsignatura() + " " + pac.getClase() + ")");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizar(ProfesorAsignaturaClase pac) {

        tx = sesion.beginTransaction();
        sesion.merge(pac);
        tx.commit();
        System.out.println("ProfesorAsignaturaClase actualizado: " + pac.toString());
    }

    public void eliminar(ProfesorAsignaturaClase pac) {

        tx = sesion.beginTransaction();
        sesion.remove(pac);
        tx.commit();
        System.out.println("ProfesorAsignaturaClase eliminado: " + pac.toString());
    }

    public ProfesorAsignaturaClase recuperarPorId(int id) throws EmpleadoNoexisteException {
    	
        pac = sesion.find(ProfesorAsignaturaClase.class, id);
        return pac;
    }

    //Recuperar pac por instancia de asignatura
    public ProfesorAsignaturaClase recuperarPAC(Alumno alumno, Asignatura asignatura) {
        try {

            Clase clase = alumno.getClase();

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

    public Set<ProfesorAsignaturaClase> recuperarPACs() {
        try {
            Query<ProfesorAsignaturaClase> query = sesion.createQuery("FROM ProfesorAsignaturaClase", ProfesorAsignaturaClase.class);
            lista = query.list();
            set = new HashSet<ProfesorAsignaturaClase>(lista);
            if (set.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay asignaturas asignadas a profesores");
            }
            return set;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Set<ProfesorAsignaturaClase> recuperarPACprofesor(Profesor profesor) {
        try {
            Query<ProfesorAsignaturaClase> query = sesion.createQuery("FROM ProfesorAsignaturaClase WHERE profesor = :profesor", ProfesorAsignaturaClase.class);
            query.setParameter("profesor", profesor);
            lista = query.list();
            set = new HashSet<ProfesorAsignaturaClase>(lista);
            if (set.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay asignaturas asignadas a este profesor");
            }
            return set;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Set<ProfesorAsignaturaClase> recuperarPACprofesorPorAsignatura(Profesor profesor, Asignatura asignatura){
        try {
            Query<ProfesorAsignaturaClase> query = sesion.createQuery("FROM ProfesorAsignaturaClase WHERE profesor = :profesor AND asignatura = :asignatura", ProfesorAsignaturaClase.class);
            query.setParameter("profesor", profesor);
            query.setParameter("asignatura", asignatura);
            lista = query.list();
            set = new HashSet<ProfesorAsignaturaClase>(lista);
            if (set.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay asignaturas asignadas a este profesor");
            }
            return set;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ProfesorAsignaturaClase recuperarPacClaseAsignatura(Clase clase, Asignatura asignatura) {
        try {
            Query<ProfesorAsignaturaClase> query = sesion.createQuery("FROM ProfesorAsignaturaClase WHERE clase = :clase AND asignatura = :asignatura", ProfesorAsignaturaClase.class);
            query.setParameter("clase", clase);
            query.setParameter("asignatura", asignatura);
            ProfesorAsignaturaClase pac = query.uniqueResult();
            return pac;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Set<ProfesorAsignaturaClase> recuperarPACsClase(Clase clase) {
        try {
            Query<ProfesorAsignaturaClase> query = sesion.createQuery("FROM ProfesorAsignaturaClase WHERE clase = :clase", ProfesorAsignaturaClase.class);
            query.setParameter("clase", clase);
            lista = query.list();
            set = new HashSet<ProfesorAsignaturaClase>(lista);
            if (set.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Esta clase no tiene asignaturas asignadas");
            }
            return set;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Set<ProfesorAsignaturaClase> recuperarPACasignatura(Asignatura asignatura) {
        try {
            Query<ProfesorAsignaturaClase> query = sesion.createQuery("FROM ProfesorAsignaturaClase WHERE asignatura = :asignatura", ProfesorAsignaturaClase.class);
            query.setParameter("asignatura", asignatura);
            lista = query.list();
            set = new HashSet<ProfesorAsignaturaClase>(lista);
            if (set.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Esta no está en ninguna clase");
            }
            return set;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProfesorAsignaturaClase> getLista() {
        try {
            Query<ProfesorAsignaturaClase> query = sesion.createQuery("FROM ProfesorAsignaturaClase", ProfesorAsignaturaClase.class);
            lista = query.list();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay asignaturas asignadas a profesores");
            }
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarAsignaturas() {
        Set<ProfesorAsignaturaClase> setAsignaturas = recuperarPACs();

        for (ProfesorAsignaturaClase pr : setAsignaturas) {
            System.out.println(pr.getAsignatura().getNombre());
        }

    }

    public void cerrarSesion() {
        sesion.close();
    }

    private List<ProfesorAsignaturaClase> lista;
    private Set<ProfesorAsignaturaClase> set;
    private ProfesorAsignaturaClase pac;
    private Session sesion;
    private Transaction tx;
}
