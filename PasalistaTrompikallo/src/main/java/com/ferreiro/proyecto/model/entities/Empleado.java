package com.ferreiro.proyecto.model.entities;

import com.ferreiro.proyecto.model.auxiliar.TipoEmpleado;
import jakarta.persistence.*;

@Entity
@Table(name = "empleados", uniqueConstraints = @UniqueConstraint(name = "uk_empleado_usuario", columnNames = "usuario"))
@Inheritance(strategy = InheritanceType.JOINED)
public class Empleado implements Comparable<Empleado> {

    public Empleado() {
    }
    public Empleado(String nombre, String apellidos, String email, String usuario, String password, TipoEmpleado tipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
    }

    public Empleado(String nombre, String apellidos, String usuario, String password, TipoEmpleado tipo){
        this(nombre, apellidos, null, usuario, password, tipo);
    }

    public TipoEmpleado getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpleado tipo) {
        this.tipo = tipo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", tipo=" + tipo;
    }

    //Equals por usuario
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Empleado)) return false;

        Empleado empleado = (Empleado) o;

        return usuario.equals(empleado.usuario);
    }

    @Override
    public int hashCode() {
        return usuario.hashCode();
    }

    //Para que en las colecciones se puedan ordeanar por apellidos alfabéticamente
    public int compareTo(Empleado o) {
        return apellidos.compareTo(o.getApellidos());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private int idEmpleado;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;
    @Column(name = "apellidos", nullable = false, length = 40)
    private String apellidos;
    @Column(name = "email", length = 40)
    private String email;
    @Column(name = "usuario", nullable = false, length = 20)
    private String usuario;
    @Column(name = "password", nullable = false, length = 40)
    private String password;
    @Column(name = "tipo", nullable = false, length = 8)
    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipo;

}
