package com.ferreiro.proyecto.controller.controllerDB;

import com.ferreiro.proyecto.model.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.swing.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


public class RepositorioAsistencia implements Respositorio<Asistencia> {

    public RepositorioAsistencia(Session sesion) {

        this.sesion = sesion;
    }

    public void guardar(Asistencia asistencia) {

        //Comprobamos si ya existe antes de añadir
        try {
            List<Asistencia> listaAsistencia = sesion.createQuery("FROM Asistencia ", Asistencia.class).getResultList();

            if (!listaAsistencia.contains(asistencia)) {
                tx = sesion.beginTransaction();
                sesion.persist(asistencia);
                tx.commit();

            } else {
                JOptionPane.showMessageDialog(null, "Ya está registrada una asistencia con esos datos: " + asistencia.toString());
            }
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizar(Asistencia asistencia) {

        tx = sesion.beginTransaction();
        List<Asistencia> asistencias = recuperarTodos();

        if(!asistencias.contains(asistencia)){
            JOptionPane.showMessageDialog(null, "No existe esa asistencia en la base de datos: " + asistencia.toString());
        }
        else {
            sesion.merge(asistencia);
            tx.commit();
            System.out.println("Asistencia actualizada correctamente");
        }
    }

    public void actualizar(Asistencia asistencia, boolean presente) {

            tx = sesion.beginTransaction();

            List<Asistencia> asistencias = recuperarTodos();
            System.out.println("La lista de asistencias contiene la asignatura: " + asistencias.contains(asistencia));

            if (!asistencias.contains(asistencia)) {
                JOptionPane.showMessageDialog(null, "Asistencia desconocida: " + asistencia.toString());
            } else {

                asistencia.setPresente(presente);
                sesion.merge(asistencia);
                tx.commit();
                System.out.println("Asistencia actualizada correctamente");
            }
        }

    public void eliminar(Asistencia asistencia) {

        tx = sesion.beginTransaction();
        sesion.remove(asistencia);
        tx.commit();
        System.out.println("Asistencia eliminada de la base de datos. Alumno:  " + asistencia.getAlumno().toString());
    }

    public Asistencia recuperarPorId(int id) throws AsistenciaNoExisteException {
        asistencia = sesion.find(Asistencia.class, id);
        return asistencia;
    }

    public Asistencia recuperarPorInstancia (Asistencia asistencia) throws AsistenciaNoExisteException{
        Asistencia asistenciaBBDD = sesion.find(Asistencia.class, asistencia.getIdAsistencia());
        if (asistenciaBBDD == null) {
            JOptionPane.showMessageDialog(null, "No existe asistencia con esos datos:" + asistencia.toString());
            throw new AsistenciaNoExisteException();
        }
        else return asistenciaBBDD;
    }

    public Asistencia recuperarPorAlumnoPacFecha(Alumno alumno, ProfesorAsignaturaClase pac, LocalDate fecha) throws AsistenciaNoExisteException {
        try {
            Query<Asistencia> query = sesion.createQuery("FROM Asistencia WHERE alumno = :alumno AND pac = :pac AND fecha = :fecha", Asistencia.class);
            query.setParameter("alumno", alumno);
            query.setParameter("pac", pac);
            query.setParameter("fecha", fecha);
            asistencia = query.uniqueResult();
            System.out.println("Asistencia recuperado de la base de datos: " + asistencia.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No existe asistencia con esos datos:" + alumno.getNombre() +  " " + alumno.getApellidos() + pac.getAsignatura().getNombre() + " " + fecha.toString());
            throw new AsistenciaNoExisteException();
        }
        return asistencia;
    }


    public List<Asistencia> recuperarTodos() {
        List<Asistencia> asistencias;
        Query<Asistencia> query = sesion.createQuery("FROM Asistencia ", Asistencia.class);
        asistencias = query.getResultList();
        return asistencias;
    }

    public List<Asistencia> recuperarAsistenciasAlumno(Alumno alumno) {
        try {
            Query<Asistencia> query = sesion.createQuery("FROM Asistencia WHERE alumno = :alumno", Asistencia.class);
            query.setParameter("alumno", alumno);
            lista = query.list();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Este alumno no tiene asistencias registradas");
            }
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Recuperar asistencias de un alumno en una asignatura por nombre
    public List<Asistencia> recuperarAsistenciasAlumnoAsignatura(Alumno alumno, String nombreAsignatura) {
        try {
            ProfesorAsignaturaClase pac = new RepositorioAlumno(sesion).recuperarPAC(alumno, nombreAsignatura);

            Query<Asistencia> query = sesion.createQuery("FROM Asistencia WHERE alumno = :alumno AND pac = :pac", Asistencia.class);
            query.setParameter("alumno", alumno);
            query.setParameter("pac", pac);
            lista = query.list();

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Este alumno no tiene asistencias registradas para esta asignatura");
            }
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Recuperar asistencias de un alumno en una asignatura por instancia de asignatura
    public List<Asistencia> recuperarAsistenciasAlumnoAsignatura(Alumno alumno, Asignatura asignatura) {
        try {
            ProfesorAsignaturaClase pac = new RepositorioAlumno(sesion).recuperarPAC(alumno, asignatura);

            Query<Asistencia> query = sesion.createQuery("FROM Asistencia WHERE alumno = :alumno AND pac = :pac", Asistencia.class);
            query.setParameter("alumno", alumno);
            query.setParameter("pac", pac);
            lista = query.list();

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Este alumno no tiene asistencias registradas para esta asignatura");
            }
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Datos de asistencia por nombre de asignatura
    public String datosAsistenciaAlumnoAsigantura(Alumno alumno, String nombreAsignatura) {

        String resumen;
        List<Asistencia> asistencias = recuperarAsistenciasAlumnoAsignatura(alumno, nombreAsignatura);
        if (asistencias.isEmpty()) {
            return "No hay asistencias registradas para este alumno en esta asignatura";
        } else {
            //Variables locales del método
            Asignatura asignatura = asistencias.get(0).getPac().getAsignatura();
            int horasTotales = asignatura.getHoras();
            int horasFaltables = (horasTotales * PORCENTAJE_FALTABLE) / 100;
            ;
            int horasAsistidas = 0;
            int horasNoAsistidas = 0;
            //Porcentaje en función de las clases que van
            DecimalFormat df = new DecimalFormat("#.##");
            double porcentajeAbsentismo;
            //Porcentaje en función de las horas totales del curso
            double porcentajeAbsentismoRelativo;
            int faltasRestantes;

            for (Asistencia asistencia : asistencias) {
                System.out.println(asistencia.toString());
                if (asistencia.isPresente()) {
                    horasAsistidas++;
                } else {
                    horasNoAsistidas++;
                }
            }
            faltasRestantes = (int) (horasFaltables - horasNoAsistidas);
            porcentajeAbsentismo = (horasNoAsistidas * 100) / (horasAsistidas + horasNoAsistidas);
            porcentajeAbsentismoRelativo = (horasNoAsistidas * 100) / (double) horasTotales;

            if (faltasRestantes == 0) {
                resumen = "Datos de asistencia del alumno " + alumno.getNombre() + " en la asignatura " + asignatura.getNombre() + "\n" +
                        "---------------\n" +
                        "Horas totales asignatura: " + horasTotales + " (Se puede faltar un 10%: " + horasFaltables + " horas)\n" +
                        "Horas asistidas: " + horasAsistidas + "\n" +
                        "Horas no asistidas: " + horasNoAsistidas + "\n" +
                        "Porcentaje de absentismo en teimpo real: " + df.format(porcentajeAbsentismo) + "%\n" +
                        "Porcentaje de absentismo en función de las horas totales: " + df.format(porcentajeAbsentismoRelativo) + "%\n" +
                        "Solo puede faltar una hora más!!!!";
            } else if (faltasRestantes > 0) {
                resumen = "Datos de asistencia del alumno " + alumno.getNombre() + " en la asignatura " + asignatura.getNombre() + "\n" +
                        "---------------\n" +
                        "Horas totales asignatura: " + horasTotales + " (Se puede faltar un 10%: " + horasFaltables + " horas)\n" +
                        "Horas asistidas: " + horasAsistidas + "\n" +
                        "Horas no asistidas: " + horasNoAsistidas + "\n" +
                        "Porcentaje de absentismo en teimpo real: " + df.format(porcentajeAbsentismo) + "%\n" +
                        "Porcentaje de absentismo en función de las horas totales: " + df.format(porcentajeAbsentismoRelativo) + "%\n" +
                        "Faltan " + faltasRestantes + "h para llegar al 10% de absentismo";
            } else
                resumen = "El alumno " + alumno.getNombre() + " ha superado el 10% de absentismo en la asignatura " + asignatura.getNombre() + "\n" +
                        "---------------\n" +
                        "SUSPENDIDO";
        }

        return resumen;
    }

    //Datos de asistencia por instancia de asignatura
    public String datosAsistenciaAlumnoAsigantura(Alumno alumno, Asignatura asignatura) {

        return datosAsistenciaAlumnoAsigantura(alumno, asignatura.getNombre());
    }

    public List<Asistencia> recuperarAusencias(Alumno alumno, Asignatura asignatura){

        try {
            ProfesorAsignaturaClase pac = new RepositorioAlumno(sesion).recuperarPAC(alumno, asignatura);

            Query<Asistencia> query = sesion.createQuery("FROM Asistencia WHERE alumno = :alumno AND pac = :pac AND presente = false", Asistencia.class);
            query.setParameter("alumno", alumno);
            query.setParameter("pac", pac);
            ausencias = query.list();
            Collections.sort(ausencias);
            return ausencias;

        }catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void cerrarSesion() {
        sesion.close();
    }


    private final int PORCENTAJE_FALTABLE = 10;
    private List<Asistencia> lista;
    private List<Asistencia> ausencias;
    private Transaction tx;
    private Session sesion;
    private Asistencia asistencia;
}
