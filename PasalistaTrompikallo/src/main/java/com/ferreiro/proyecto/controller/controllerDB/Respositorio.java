package com.ferreiro.proyecto.controller.controllerDB;

public interface Respositorio<T> {
    void guardar(T t);

    void actualizar(T t);

    void eliminar(T t);

    T recuperarPorId(int id) throws Exception;

    void cerrarSesion();


}
