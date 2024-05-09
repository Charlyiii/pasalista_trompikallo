package com.ferreiro.proyecto.controller.controllerDB;

import com.ferreiro.proyecto.model.entities.Gestor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class RepositorioGestor implements Respositorio<Gestor> {

    public RepositorioGestor(Session sesion) {

        this.sesion = sesion;
    }
    public void guardar(Gestor gestor) {
    	
        tx = sesion.beginTransaction();
        sesion.persist(gestor);
        tx.commit();
        System.out.println("Gestor guardado en la base de datos: " + gestor.getUsuario());
    }
    
    public void actualizar(Gestor gestor) {
    	
        Transaction tx = sesion.beginTransaction();
        sesion.merge(gestor);
        tx.commit();
        System.out.println("Gestor correctamente");
    }

    public void actualizar(Gestor gestor, String nombre, String apellidos, String email, String usuario, String password) {

        Transaction tx = sesion.beginTransaction();
        gestor.setNombre(nombre);
        gestor.setApellidos(apellidos);
        gestor.setEmail(email);
        gestor.setUsuario(usuario);
        gestor.setPassword(password);
        sesion.merge(gestor);
        tx.commit();
        System.out.println("Gestor actualizado: " + gestor.getUsuario());
    }

    public void eliminar(Gestor gestor) {
    	
        Transaction tx = sesion.beginTransaction();
        sesion.remove(gestor);
        tx.commit();
        System.out.println("Gestor eliminado de la base de datos: " + gestor.getUsuario());
    }

    public Gestor recuperarPorId(int id) throws EmpleadoNoexisteException {
        gestor = sesion.find(Gestor.class, id);
        return gestor;
    }

    
    public Gestor recuperarPorUsuario(String usuario) throws EmpleadoNoexisteException {
        try {
            tx = sesion.beginTransaction();
            Query<Gestor> query = sesion.createQuery("FROM Gestor WHERE usuario = :usuario", Gestor.class);
            query.setParameter("usuario", usuario);
            gestor = query.uniqueResult();
            tx.commit();
            System.out.println("Gestor recuperado de la base de datos: " + gestor.getUsuario());


        } catch (Exception e) {
            throw new EmpleadoNoexisteException();
        }
        return gestor;
    }

    public Gestor recuperarPorLogin(Gestor usuario, String password) throws PassworIncorrectaException {
        tx = sesion.beginTransaction();
        if (gestor.getPassword().equals(password)) {
            tx.commit();
            return gestor;

        } else {
            throw new PassworIncorrectaException();
        }
    }

    public List<Gestor> recuperarTodos() {
    	
        tx = sesion.beginTransaction();
        Query<Gestor> query = sesion.createQuery("FROM Gestor", Gestor.class);
        List<Gestor> gestores = query.list();
        tx.commit();
        return gestores;
    }

    public Transaction getTx() {
        return tx;
    }

    public void cerrarSesion() {
        sesion.close();
    }

    Transaction tx;
    private Gestor gestor;
    private Session sesion;
}

