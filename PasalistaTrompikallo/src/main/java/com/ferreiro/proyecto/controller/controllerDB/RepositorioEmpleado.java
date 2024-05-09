package com.ferreiro.proyecto.controller.controllerDB;

import com.ferreiro.proyecto.model.entities.Empleado;
import com.ferreiro.proyecto.model.entities.Profesor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RepositorioEmpleado implements Respositorio<Empleado> {

    public RepositorioEmpleado(Session sesion) {

        this.sesion = sesion;
    }

    
    public void guardar(Empleado empleado) {

        Transaction tx = sesion.beginTransaction();
        sesion.persist(empleado);
        tx.commit();
        System.out.println("Empleado guardado en la base de datos: " + empleado.getUsuario());
    }

    public void actualizar(Empleado empleado, String nombre, String apellidos, String email, String usuario, String password) {
    	
    	Transaction tx = sesion.beginTransaction();
    	empleado.setNombre(nombre);
    	empleado.setApellidos(apellidos);
    	empleado.setEmail(email);
    	empleado.setUsuario(usuario);
    	empleado.setPassword(password);
        sesion.merge(empleado);
        tx.commit();
        System.out.println("Empleado actualizado: " + empleado.getUsuario());

    }

    public void actualizarPassword(Empleado empleado, String nuevoPassword) {

    	Transaction tx = sesion.beginTransaction();
    	empleado.setPassword(nuevoPassword);
        sesion.merge(empleado);
        tx.commit();
        System.out.println("Empleado actualizado: " + empleado.getUsuario());

    }

    
    public void eliminar(Empleado empleado) {
    	
    	Transaction tx = sesion.beginTransaction();
    	sesion.remove(empleado);
    	tx.commit();
        System.out.println("Profesor eliminado de la base de datos: " + empleado.getUsuario());
    }

    
    public Empleado recuperarPorId(int id) throws EmpleadoNoexisteException {
    	
    	empleado = sesion.find(Profesor.class, id);
        return empleado;
    }

    
    public Empleado recuperarPorUsuario(String usuario) throws EmpleadoNoexisteException {

        try {

            Query<Empleado> query = sesion.createQuery("FROM Empleado WHERE usuario = :usuario", Empleado.class);
            query.setParameter("usuario", usuario);
            empleado = query.uniqueResult();
            System.out.println("Empleado recuperado de la base de datos: " + empleado.getUsuario());

        } catch (Exception e) {
            throw new EmpleadoNoexisteException();
        }
        return empleado;
    }


    public Empleado recuperarPorLogin(String password) throws PassworIncorrectaException {
        if (empleado.getPassword().equals(password)) {

            return empleado;
        }
        else throw new PassworIncorrectaException();
    }

    public void cerrarSesion() {
    	sesion.close();
    }

    private Empleado empleado;
    private final Session sesion;
	public void actualizar(Empleado t) {
		// TODO Auto-generated method stub
		
	}
}
