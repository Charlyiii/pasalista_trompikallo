package com.ferreiro.proyecto.controller.controllerDB;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class HibernateUtil {
    private HibernateUtil() {
    }

    //En este caso no usamos el patrón singleton porque necesitamos conectarnos a diferentes bases de datos para los históricos
    public static SessionFactory getSessionFactory(String nombreBD) throws Exception {

        SessionFactory sessionFactory = null;

            if (nombreBD.equals("actual")) {

                try {
                    sessionFactory = getSessionFactory();

                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No se ha podido establecer la conexión con la base de datos o no exite (actual)");

                    }

            }
            //Crea la bbdd para la demo
            else if (nombreBD.equals("demo")) {

                try {
                    //Para que no intente borrar tablas que no exiten la primera vez que se ejecuta

                    // Intenta establecer una conexión con la base de datos
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");

                    // La conexión fue exitosa, lo que significa que la base de datos ya existe. La sobreescribimos.
                    sessionFactory = new Configuration()
                            .configure("hibernate.cfg.xml")
                            .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/demo" )
                            .setProperty("hibernate.hbm2ddl.auto", "update")
                            .buildSessionFactory();
                } catch (SQLException e) {
                    // La conexión falló, lo que significa que la base de datos no existe. La creamos y cargamos datos
                    sessionFactory = new Configuration()
                            .configure("hibernate.cfg.xml")
                            .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/demo?createDatabaseIfNotExist=true" )
                            .setProperty("hibernate.hbm2ddl.auto", "create-only")
                            .buildSessionFactory();
                    //Cargamos los datos de la demo
                    Demo demo = new Demo();
                    demo.cargarDatosDemo();

                } catch (HibernateException e) {
                    JOptionPane.showMessageDialog(null, "No se ha podido establecer la conexión con la base de datos o no exite (demo)");
                }
            }
            //Accede a las bbdd de los históricos
            else {
                try {
                    sessionFactory = new Configuration()
                            .configure("hibernate.cfg.xml")
                            .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + nombreBD)
                            .setProperty("hibernate.hbm2ddl.auto", "none") //Para que no cree ni actualice las tablas de historico
                            .buildSessionFactory();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No se ha podido establecer la conexión con la base de datos o no existe");

                }
            }

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() throws Exception {

            SessionFactory sessionFactory = null;

            try {

                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/actual?createDatabaseIfNotExist=true")
                        .setProperty("hibernate.hbm2ddl.auto", "update")
                        .buildSessionFactory();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se ha podido establecer la conexión con la base de datos o no exite (actual)");
                ex.printStackTrace();
            }

            return sessionFactory;
    }

}
