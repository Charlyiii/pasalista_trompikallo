package com.ferreiro.proyecto.model.auxiliar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "historico")
public class Historico {

    //No se puede instanciar, solo la usamos para crear la tabla y saber los cursos de los que existe una base de datos
    private Historico() {
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Id
    @Column(name = "curso", length = 5)
    private String curso;
}
