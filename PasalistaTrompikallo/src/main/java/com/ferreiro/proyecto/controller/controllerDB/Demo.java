package com.ferreiro.proyecto.controller.controllerDB;

import com.ferreiro.proyecto.model.entities.*;
import org.hibernate.Session;

import java.time.LocalDate;

public class Demo {

    //La primera vez que se ejecute el programa se creará la base de datos demo y se cargarán los datos
    //El resto de las veces que se entre estarán guardados los datos anteriores, solo el admin podrá restablecer la demo manualmente
    /*Se podría implementar un método para borrar la base de datos demo y volver a cargar los datos, pero no queremos que
        un invitado pueda manipular la base de datos
    */
    public static Session arrancarDemo() {
        try {
            sesionDemo = HibernateUtil.getSessionFactory("demo").openSession();
            System.out.println("Base de datos demo creada y abierta");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sesionDemo;
    }

    public void cargarDatosDemo() {

        try {
            sesionDemo = HibernateUtil.getSessionFactory("demo").openSession();

            //Añadimos todos los datos a la base de datos
            RepositorioProfesor repositorioProfesor = new RepositorioProfesor(sesionDemo);
            //Profesor
            Profesor profesor = new Profesor("Carlos", "Ferreiro Barros", "cferrierobarros@gmail.com", "Charly", "");
            repositorioProfesor.guardar(profesor);

            //Clases
            RepositorioClase repositorioClase = new RepositorioClase(sesionDemo);
            //4ªA ESO
            Clase clase4A = new Clase(4, 'A', "ESO");
            repositorioClase.guardar(clase4A);
            //4ªB ESO
            Clase clase4B = new Clase(4, 'B', "ESO");
            repositorioClase.guardar(clase4B);
            //1ºA Bachillerato
            Clase clase1A = new Clase(1, 'A', "Bachillerato");
            repositorioClase.guardar(clase1A);
            //1ºB Bachillerato
            Clase clase1B = new Clase(1, 'B', "Bachillerato");
            repositorioClase.guardar(clase1B);
            //2ºA Bachillerato
            Clase clase2A = new Clase(2, 'A', "Bachillerato");
            repositorioClase.guardar(clase2A);
            //2ºB Bachillerato
            Clase clase2B = new Clase(2, 'B', "Bachillerato");
            repositorioClase.guardar(clase2B);

            //Asignaturas
            RepositorioAsignatura repositorioAsignatura = new RepositorioAsignatura(sesionDemo);
            //Matemáticas
            Asignatura asignatura1 = new Asignatura("Matemáticas", "Mts", 150);
            repositorioAsignatura.guardar(asignatura1);
            //Física
            Asignatura asignatura2 = new Asignatura("Física", "Fsc", 100);
            repositorioAsignatura.guardar(asignatura2);
            //Química
            Asignatura asignatura3 = new Asignatura("Química", "Qmc", 100);
            repositorioAsignatura.guardar(asignatura3);
            //Biología
            Asignatura asignatura4 = new Asignatura("Biología", "Bio", 125);
            repositorioAsignatura.guardar(asignatura4);
            //Programación
            Asignatura asignatura5 = new Asignatura("Programación", "Prg", 150);
            repositorioAsignatura.guardar(asignatura5);

            //Alumnos con constructor que incluye la clase
            RepositorioAlumno repositorioAlumno = new RepositorioAlumno(sesionDemo);

            //4ªA ESO
            Alumno alumno1_4A = new Alumno("Juan Carlos", "Valerón Santana", clase4A);
            Alumno alumno2_4A = new Alumno("Djalma", "Feitosa Dias", clase4A);
            Alumno alumno3_4A = new Alumno("Jacques", "Songo'o", clase4A);
            Alumno alumno4_4A = new Alumno("Diego", "Tristán", clase4A);
            Alumno alumno5_4A = new Alumno("Walter", "Pandiani", clase4A);
            Alumno alumno6_4A = new Alumno("Sergio", "González", clase4A);
            Alumno alumno7_4A = new Alumno("Roy", "Makaay", clase4A);
            Alumno alumno8_4A = new Alumno("Mauro", "Silva", clase4A);
            Alumno alumno9_4A = new Alumno("Donato", "Gama", clase4A);
            Alumno alumno10_4A = new Alumno("Jorge", "Andrade", clase4A);
            Alumno alumno11_4A = new Alumno("Nourredine", "Naybet", clase4A);
            Alumno alumno12_4A = new Alumno("Manuel", "Pablo", clase4A);
            Alumno alumno13_4A = new Alumno("Lionel", "Scaloni", clase4A);
            Alumno alumno14_4A = new Alumno("Víctor", "Sánchez del Amo", clase4A);
            Alumno alumno15_4A = new Alumno("Turu", "Flores", clase4A);
            Alumno alumno16_4A = new Alumno("Albert", "Luque", clase4A);
            Alumno alumno17_4A = new Alumno("Lucas", "Pérez", clase4A);
            Alumno alumno18_4A = new Alumno("José Roberto", "Gama de Oliveira", clase4A);
            Alumno alumno19_4A = new Alumno("Francisco", "González", clase4A);
            Alumno alumno20_4A = new Alumno("Joan", "Capdevila", clase4A);

            repositorioAlumno.guardar(alumno1_4A);
            repositorioAlumno.guardar(alumno2_4A);
            repositorioAlumno.guardar(alumno3_4A);
            repositorioAlumno.guardar(alumno4_4A);
            repositorioAlumno.guardar(alumno5_4A);
            repositorioAlumno.guardar(alumno6_4A);
            repositorioAlumno.guardar(alumno7_4A);
            repositorioAlumno.guardar(alumno8_4A);
            repositorioAlumno.guardar(alumno9_4A);
            repositorioAlumno.guardar(alumno10_4A);
            repositorioAlumno.guardar(alumno11_4A);
            repositorioAlumno.guardar(alumno12_4A);
            repositorioAlumno.guardar(alumno13_4A);
            repositorioAlumno.guardar(alumno14_4A);
            repositorioAlumno.guardar(alumno15_4A);
            repositorioAlumno.guardar(alumno16_4A);
            repositorioAlumno.guardar(alumno17_4A);
            repositorioAlumno.guardar(alumno18_4A);
            repositorioAlumno.guardar(alumno19_4A);
            repositorioAlumno.guardar(alumno20_4A);

            //4ªB ESO
            Alumno alumno1_4B = new Alumno("José María", "Gutiérrez", clase4B);
            Alumno alumno2_4B = new Alumno("Iker", "Casillas", clase4B);
            Alumno alumno3_4B = new Alumno("Raúl", "González", clase4B);
            Alumno alumno4_4B = new Alumno("Fernando", "Hierro", clase4B);
            Alumno alumno5_4B = new Alumno("Roberto", "Carlos", clase4B);
            Alumno alumno6_4B = new Alumno("Fernando", "Morientes", clase4B);
            Alumno alumno7_4B = new Alumno("Santiago", "Cañizares", clase4B);
            Alumno alumno8_4B = new Alumno("Míchel", "Salgado", clase4B);
            Alumno alumno9_4B = new Alumno("Pedro", "Contreras", clase4B);
            Alumno alumno10_4B = new Alumno("Javier", "Portillo", clase4B);
            Alumno alumno11_4B = new Alumno("Zinedine", "Zidane", clase4B);
            Alumno alumno12_4B = new Alumno("Luis", "Figo", clase4B);
            Alumno alumno13_4B = new Alumno("Iván", "Zamorano", clase4B);
            Alumno alumno14_4B = new Alumno("Roberto", "Soldado", clase4B);
            Alumno alumno15_4B = new Alumno("Raúl", "Bravo", clase4B);
            Alumno alumno16_4B = new Alumno("Flavio", "Conceiçao", clase4B);
            Alumno alumno17_4B = new Alumno("Claude", "Makelele", clase4B);
            Alumno alumno18_4B = new Alumno("David", "Beckham", clase4B);
            Alumno alumno19_4B = new Alumno("Sergio", "Ramos", clase4B);
            Alumno alumno20_4B = new Alumno("Cristiano", "Ronaldo", clase4B);

            repositorioAlumno.guardar(alumno1_4B);
            repositorioAlumno.guardar(alumno2_4B);
            repositorioAlumno.guardar(alumno3_4B);
            repositorioAlumno.guardar(alumno4_4B);
            repositorioAlumno.guardar(alumno5_4B);
            repositorioAlumno.guardar(alumno6_4B);
            repositorioAlumno.guardar(alumno7_4B);
            repositorioAlumno.guardar(alumno8_4B);
            repositorioAlumno.guardar(alumno9_4B);
            repositorioAlumno.guardar(alumno10_4B);
            repositorioAlumno.guardar(alumno11_4B);
            repositorioAlumno.guardar(alumno12_4B);
            repositorioAlumno.guardar(alumno13_4B);
            repositorioAlumno.guardar(alumno14_4B);
            repositorioAlumno.guardar(alumno15_4B);
            repositorioAlumno.guardar(alumno16_4B);
            repositorioAlumno.guardar(alumno17_4B);
            repositorioAlumno.guardar(alumno18_4B);
            repositorioAlumno.guardar(alumno19_4B);
            repositorioAlumno.guardar(alumno20_4B);

            //1ºA Bachillerato
            Alumno alumno1_1A = new Alumno("Juancho", "Hernangómez", clase1A);
            Alumno alumno2_1A = new Alumno("Willy", "Hernangómez", clase1A);
            Alumno alumno3_1A = new Alumno("Juan Carlos", "Navarro", clase1A);
            Alumno alumno4_1A = new Alumno("Pau", "Gasol", clase1A);
            Alumno alumno5_1A = new Alumno("Felipe", "Reyes", clase1A);
            Alumno alumno6_1A = new Alumno("Rudy", "Fernández", clase1A);
            Alumno alumno7_1A = new Alumno("Sergio", "Rodríguez", clase1A);
            Alumno alumno8_1A = new Alumno("José Manuel", "Calderón", clase1A);
            Alumno alumno9_1A = new Alumno("Marc", "Gasol", clase1A);
            Alumno alumno10_1A = new Alumno("Sergio", "Llull", clase1A);
            Alumno alumno11_1A = new Alumno("Víctor", "Claver", clase1A);
            Alumno alumno12_1A = new Alumno("Ricky", "Rubio", clase1A);
            Alumno alumno13_1A = new Alumno("Alex", "Abrines", clase1A);
            Alumno alumno14_1A = new Alumno("Fernando", "San Emeterio", clase1A);
            Alumno alumno15_1A = new Alumno("Pablo", "Aguilar", clase1A);
            Alumno alumno16_1A = new Alumno("Guillem", "Vives", clase1A);
            Alumno alumno17_1A = new Alumno("Pau", "Ribas", clase1A);
            Alumno alumno18_1A = new Alumno("Nikola", "Mirotic", clase1A);
            Alumno alumno19_1A = new Alumno("Víctor", "Sada", clase1A);
            Alumno alumno20_1A = new Alumno("Sergio", "Scariolo", clase1A);

            repositorioAlumno.guardar(alumno1_1A);
            repositorioAlumno.guardar(alumno2_1A);
            repositorioAlumno.guardar(alumno3_1A);
            repositorioAlumno.guardar(alumno4_1A);
            repositorioAlumno.guardar(alumno5_1A);
            repositorioAlumno.guardar(alumno6_1A);
            repositorioAlumno.guardar(alumno7_1A);
            repositorioAlumno.guardar(alumno8_1A);
            repositorioAlumno.guardar(alumno9_1A);
            repositorioAlumno.guardar(alumno10_1A);
            repositorioAlumno.guardar(alumno11_1A);
            repositorioAlumno.guardar(alumno12_1A);
            repositorioAlumno.guardar(alumno13_1A);
            repositorioAlumno.guardar(alumno14_1A);
            repositorioAlumno.guardar(alumno15_1A);
            repositorioAlumno.guardar(alumno16_1A);
            repositorioAlumno.guardar(alumno17_1A);
            repositorioAlumno.guardar(alumno18_1A);
            repositorioAlumno.guardar(alumno19_1A);
            repositorioAlumno.guardar(alumno20_1A);

            //1ºB Bachillerato
            Alumno alumno1_1B = new Alumno("Jennifer", "Hermoso", clase1B);
            Alumno alumno2_1B = new Alumno("Alexia", "Putellas", clase1B);
            Alumno alumno3_1B = new Alumno("Athenea", "Del Castillo", clase1B);
            Alumno alumno4_1B = new Alumno("Olga", "Carmona", clase1B);
            Alumno alumno5_1B = new Alumno("Aitana", "Bonmatí", clase1B);
            Alumno alumno6_1B = new Alumno("Salma", "Paralluelo", clase1B);
            Alumno alumno7_1B = new Alumno("Irene", "Paredes", clase1B);
            Alumno alumno8_1B = new Alumno("Alba", "Redondo", clase1B);
            Alumno alumno9_1B = new Alumno("Ivana", "Andrés", clase1B);
            Alumno alumno10_1B = new Alumno("Ona", "Batlle", clase1B);
            Alumno alumno11_1B = new Alumno("Catalina", "Coll", clase1B);
            Alumno alumno12_1B = new Alumno("Misa", "Rodríguez", clase1B);
            Alumno alumno13_1B = new Alumno("Mariona", "Caldentey", clase1B);
            Alumno alumno14_1B = new Alumno("Teresa", "Abelleira", clase1B);
            Alumno alumno15_1B = new Alumno("Esther", "González", clase1B);
            Alumno alumno16_1B = new Alumno("Laia", "Codina", clase1B);
            Alumno alumno17_1B = new Alumno("Irene", "Guerrero", clase1B);
            Alumno alumno18_1B = new Alumno("Oihane", "Hernández", clase1B);
            Alumno alumno19_1B = new Alumno("Rocío", "Gálvez", clase1B);
            Alumno alumno20_1B = new Alumno("Eva", "Navarro", clase1B);

            repositorioAlumno.guardar(alumno1_1B);
            repositorioAlumno.guardar(alumno2_1B);
            repositorioAlumno.guardar(alumno3_1B);
            repositorioAlumno.guardar(alumno4_1B);
            repositorioAlumno.guardar(alumno5_1B);
            repositorioAlumno.guardar(alumno6_1B);
            repositorioAlumno.guardar(alumno7_1B);
            repositorioAlumno.guardar(alumno8_1B);
            repositorioAlumno.guardar(alumno9_1B);
            repositorioAlumno.guardar(alumno10_1B);
            repositorioAlumno.guardar(alumno11_1B);
            repositorioAlumno.guardar(alumno12_1B);
            repositorioAlumno.guardar(alumno13_1B);
            repositorioAlumno.guardar(alumno14_1B);
            repositorioAlumno.guardar(alumno15_1B);
            repositorioAlumno.guardar(alumno16_1B);
            repositorioAlumno.guardar(alumno17_1B);
            repositorioAlumno.guardar(alumno18_1B);
            repositorioAlumno.guardar(alumno19_1B);
            repositorioAlumno.guardar(alumno20_1B);

            //2ºA Bachillerato
            Alumno alumno1_2A = new Alumno("María", "Pérez", clase2A);
            Alumno alumno2_2A = new Alumno("Lucía", "García", clase2A);
            Alumno alumno3_2A = new Alumno("Paula", "Fernández", clase2A);
            Alumno alumno4_2A = new Alumno("Sofía", "Rodríguez", clase2A);
            Alumno alumno5_2A = new Alumno("Martina", "González", clase2A);
            Alumno alumno6_2A = new Alumno("Julio", "Hernández", clase2A);
            Alumno alumno7_2A = new Alumno("Daniel", "Martínez", clase2A);
            Alumno alumno8_2A = new Alumno("Hugo", "López", clase2A);
            Alumno alumno9_2A = new Alumno("Alejandro", "García", clase2A);
            Alumno alumno10_2A = new Alumno("Pablo", "Sánchez", clase2A);
            Alumno alumno11_2A = new Alumno("Álvaro", "González", clase2A);
            Alumno alumno12_2A = new Alumno("Adriana", "Veiga", clase2A);
            Alumno alumno13_2A = new Alumno("Alba", "Oubiña", clase2A);
            Alumno alumno14_2A = new Alumno("Alicia", "Ferreiro", clase2A);
            Alumno alumno15_2A = new Alumno("Daniel", "Mayo", clase2A);
            Alumno alumno16_2A = new Alumno("David", "Pereira", clase2A);
            Alumno alumno17_2A = new Alumno("Diego", "Blanco", clase2A);
            Alumno alumno18_2A = new Alumno("Elena", "González", clase2A);
            Alumno alumno19_2A = new Alumno("Pedro", "Rodríguez", clase2A);
            Alumno alumno20_2A = new Alumno("Sara", "Huerta", clase2A);

            repositorioAlumno.guardar(alumno1_2A);
            repositorioAlumno.guardar(alumno2_2A);
            repositorioAlumno.guardar(alumno3_2A);
            repositorioAlumno.guardar(alumno4_2A);
            repositorioAlumno.guardar(alumno5_2A);
            repositorioAlumno.guardar(alumno6_2A);
            repositorioAlumno.guardar(alumno7_2A);
            repositorioAlumno.guardar(alumno8_2A);
            repositorioAlumno.guardar(alumno9_2A);
            repositorioAlumno.guardar(alumno10_2A);
            repositorioAlumno.guardar(alumno11_2A);
            repositorioAlumno.guardar(alumno12_2A);
            repositorioAlumno.guardar(alumno13_2A);
            repositorioAlumno.guardar(alumno14_2A);
            repositorioAlumno.guardar(alumno15_2A);
            repositorioAlumno.guardar(alumno16_2A);
            repositorioAlumno.guardar(alumno17_2A);
            repositorioAlumno.guardar(alumno18_2A);
            repositorioAlumno.guardar(alumno19_2A);
            repositorioAlumno.guardar(alumno20_2A);

            //2ºB Bachillerato
            Alumno alumno1_2B = new Alumno("Michael", "Jackson", clase2B);
            Alumno alumno2_2B = new Alumno("Freddie", "Mercury", clase2B);
            Alumno alumno3_2B = new Alumno("Elvis", "Presley", clase2B);
            Alumno alumno4_2B = new Alumno("John", "Lennon", clase2B);
            Alumno alumno5_2B = new Alumno("Bob", "Marley", clase2B);
            Alumno alumno6_2B = new Alumno("Jimi", "Hendrix", clase2B);
            Alumno alumno7_2B = new Alumno("David", "Bowie", clase2B);
            Alumno alumno8_2B = new Alumno("Kurt", "Cobain", clase2B);
            Alumno alumno9_2B = new Alumno("Amy", "Winehouse", clase2B);
            Alumno alumno10_2B = new Alumno("Janis", "Joplin", clase2B);
            Alumno alumno11_2B = new Alumno("Jim", "Morrison", clase2B);
            Alumno alumno12_2B = new Alumno("Sid", "Vicious", clase2B);
            Alumno alumno13_2B = new Alumno("Bon", "Scott", clase2B);
            Alumno alumno14_2B = new Alumno("Brian", "Jones", clase2B);
            Alumno alumno15_2B = new Alumno("Keith", "Moon", clase2B);
            Alumno alumno16_2B = new Alumno("Cliff", "Burton", clase2B);
            Alumno alumno17_2B = new Alumno("John", "Bonham", clase2B);
            Alumno alumno18_2B = new Alumno("George", "Harrison", clase2B);
            Alumno alumno19_2B = new Alumno("Ritchie", "Blackmore", clase2B);
            Alumno alumno20_2B = new Alumno("Ronnie", "James Dio", clase2B);

            repositorioAlumno.guardar(alumno1_2B);
            repositorioAlumno.guardar(alumno2_2B);
            repositorioAlumno.guardar(alumno3_2B);
            repositorioAlumno.guardar(alumno4_2B);
            repositorioAlumno.guardar(alumno5_2B);
            repositorioAlumno.guardar(alumno6_2B);
            repositorioAlumno.guardar(alumno7_2B);
            repositorioAlumno.guardar(alumno8_2B);
            repositorioAlumno.guardar(alumno9_2B);
            repositorioAlumno.guardar(alumno10_2B);
            repositorioAlumno.guardar(alumno11_2B);
            repositorioAlumno.guardar(alumno12_2B);
            repositorioAlumno.guardar(alumno13_2B);
            repositorioAlumno.guardar(alumno14_2B);
            repositorioAlumno.guardar(alumno15_2B);
            repositorioAlumno.guardar(alumno16_2B);
            repositorioAlumno.guardar(alumno17_2B);
            repositorioAlumno.guardar(alumno18_2B);
            repositorioAlumno.guardar(alumno19_2B);
            repositorioAlumno.guardar(alumno20_2B);


            //Asignamos asignaturas al profesor y a las clases

            //4ºA
            //Matemáticas
            repositorioProfesor.asignarPAC(profesor, asignatura1, clase4A);
            //Biología
            repositorioProfesor.asignarPAC(profesor, asignatura4, clase4A);
            //Programación
            repositorioProfesor.asignarPAC(profesor, asignatura5, clase4A);

            //4ºB
            //Matemáticas
            repositorioProfesor.asignarPAC(profesor, asignatura1, clase4B);
            //Biología
            repositorioProfesor.asignarPAC(profesor, asignatura4, clase4B);
            //Programación
            repositorioProfesor.asignarPAC(profesor, asignatura5, clase4B);

            //1ºA
            //Física
            repositorioProfesor.asignarPAC(profesor, asignatura2, clase1A);
            //Química
            repositorioProfesor.asignarPAC(profesor, asignatura3, clase1A);

            //1ºB
            //Física
            repositorioProfesor.asignarPAC(profesor, asignatura2, clase1B);
            //Química
            repositorioProfesor.asignarPAC(profesor, asignatura3, clase1B);

            //2ºA
            //Biología
            repositorioProfesor.asignarPAC(profesor, asignatura4, clase2A);

            //2ºB
            //Biología
            repositorioProfesor.asignarPAC(profesor, asignatura4, clase2B);

            //Añadimos registros de asistencia
            //Necesitamos las pacs de cada asignatura para cada clase
            RepositorioPAC repositorioPAC = new RepositorioPAC(sesionDemo);
            //4A
            ProfesorAsignaturaClase ESO4A_mts = repositorioPAC.recuperarPacClaseAsignatura(clase4A, asignatura1);
            ProfesorAsignaturaClase ESO4A_bio = repositorioPAC.recuperarPacClaseAsignatura(clase4A, asignatura4);
            ProfesorAsignaturaClase ESO4A_prog = repositorioPAC.recuperarPacClaseAsignatura(clase4A, asignatura5);


            //4B
            ProfesorAsignaturaClase ESO4B_mts = repositorioPAC.recuperarPacClaseAsignatura(clase4B, asignatura1);
            ProfesorAsignaturaClase ESO4B_bio = repositorioPAC.recuperarPacClaseAsignatura(clase4B, asignatura4);
            ProfesorAsignaturaClase ESO4B_prog = repositorioPAC.recuperarPacClaseAsignatura(clase4B, asignatura5);

            //1A
            ProfesorAsignaturaClase ESO1A_fis = repositorioPAC.recuperarPacClaseAsignatura(clase1A, asignatura2);
            ProfesorAsignaturaClase ESO1A_qui = repositorioPAC.recuperarPacClaseAsignatura(clase1A, asignatura3);

            //1B
            ProfesorAsignaturaClase ESO1B_fis = repositorioPAC.recuperarPacClaseAsignatura(clase1B, asignatura2);
            ProfesorAsignaturaClase ESO1B_qui = repositorioPAC.recuperarPacClaseAsignatura(clase1B, asignatura3);

            //2A
            ProfesorAsignaturaClase ESO2A_bio = repositorioPAC.recuperarPacClaseAsignatura(clase2A, asignatura4);

            //2B
            ProfesorAsignaturaClase ESO2B_bio = repositorioPAC.recuperarPacClaseAsignatura(clase2B, asignatura4);

            //Creamos los registros de asistencia -- Solo lo haremos para una clase durante una semana
            RepositorioAsistencia repositorioAsistencia = new RepositorioAsistencia(sesionDemo);

            //4A
            //MTS-DÍA 1
            Asistencia asistencia1_4A = new Asistencia(alumno1_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia1_4A);
            Asistencia asistencia2_4A = new Asistencia(alumno2_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia2_4A);
            Asistencia asistencia3_4A = new Asistencia(alumno3_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia3_4A);
            Asistencia asistencia4_4A = new Asistencia(alumno4_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), false);
            repositorioAsistencia.guardar(asistencia4_4A);
            Asistencia asistencia5_4A = new Asistencia(alumno5_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia5_4A);
            Asistencia asistencia6_4A = new Asistencia(alumno6_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia6_4A);
            Asistencia asistencia7_4A = new Asistencia(alumno7_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia7_4A);
            Asistencia asistencia8_4A = new Asistencia(alumno8_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia8_4A);
            Asistencia asistencia9_4A = new Asistencia(alumno9_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia9_4A);
            Asistencia asistencia10_4A = new Asistencia(alumno10_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia10_4A);
            Asistencia asistencia11_4A = new Asistencia(alumno11_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), false);
            repositorioAsistencia.guardar(asistencia11_4A);
            Asistencia asistencia12_4A = new Asistencia(alumno12_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia12_4A);
            Asistencia asistencia13_4A = new Asistencia(alumno13_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), false);
            repositorioAsistencia.guardar(asistencia13_4A);
            Asistencia asistencia14_4A = new Asistencia(alumno14_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia14_4A);
            Asistencia asistencia15_4A = new Asistencia(alumno15_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia15_4A);
            Asistencia asistencia16_4A = new Asistencia(alumno16_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia16_4A);
            Asistencia asistencia17_4A = new Asistencia(alumno17_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia17_4A);
            Asistencia asistencia18_4A = new Asistencia(alumno18_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), false);
            repositorioAsistencia.guardar(asistencia18_4A);
            Asistencia asistencia19_4A = new Asistencia(alumno19_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia19_4A);
            Asistencia asistencia20_4A = new Asistencia(alumno20_4A, ESO4A_mts, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia20_4A);

            //MTS-DÍA 2
            Asistencia asistencia21_4A = new Asistencia(alumno1_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia21_4A);
            Asistencia asistencia22_4A = new Asistencia(alumno2_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia22_4A);
            Asistencia asistencia23_4A = new Asistencia(alumno3_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia23_4A);
            Asistencia asistencia24_4A = new Asistencia(alumno4_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), false);
            repositorioAsistencia.guardar(asistencia24_4A);
            Asistencia asistencia25_4A = new Asistencia(alumno5_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia25_4A);
            Asistencia asistencia26_4A = new Asistencia(alumno6_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia26_4A);
            Asistencia asistencia27_4A = new Asistencia(alumno7_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia27_4A);
            Asistencia asistencia28_4A = new Asistencia(alumno8_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia28_4A);
            Asistencia asistencia29_4A = new Asistencia(alumno9_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), false);
            repositorioAsistencia.guardar(asistencia29_4A);
            Asistencia asistencia30_4A = new Asistencia(alumno10_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia30_4A);
            Asistencia asistencia31_4A = new Asistencia(alumno11_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), false);
            repositorioAsistencia.guardar(asistencia31_4A);
            Asistencia asistencia32_4A = new Asistencia(alumno12_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia32_4A);
            Asistencia asistencia33_4A = new Asistencia(alumno13_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia33_4A);
            Asistencia asistencia34_4A = new Asistencia(alumno14_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia34_4A);
            Asistencia asistencia35_4A = new Asistencia(alumno15_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), false);
            repositorioAsistencia.guardar(asistencia35_4A);
            Asistencia asistencia36_4A = new Asistencia(alumno16_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia36_4A);
            Asistencia asistencia37_4A = new Asistencia(alumno17_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia37_4A);
            Asistencia asistencia38_4A = new Asistencia(alumno18_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia38_4A);
            Asistencia asistencia39_4A = new Asistencia(alumno19_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia39_4A);
            Asistencia asistencia40_4A = new Asistencia(alumno20_4A, ESO4A_mts, LocalDate.of(2023, 9, 26), true);
            repositorioAsistencia.guardar(asistencia40_4A);

            //MTS-DÍA 3
            Asistencia asistencia41_4A = new Asistencia(alumno1_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia41_4A);
            Asistencia asistencia42_4A = new Asistencia(alumno2_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia42_4A);
            Asistencia asistencia43_4A = new Asistencia(alumno3_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia43_4A);
            Asistencia asistencia44_4A = new Asistencia(alumno4_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), false);
            repositorioAsistencia.guardar(asistencia44_4A);
            Asistencia asistencia45_4A = new Asistencia(alumno5_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia45_4A);
            Asistencia asistencia46_4A = new Asistencia(alumno6_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia46_4A);
            Asistencia asistencia47_4A = new Asistencia(alumno7_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia47_4A);
            Asistencia asistencia48_4A = new Asistencia(alumno8_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia48_4A);
            Asistencia asistencia49_4A = new Asistencia(alumno9_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia49_4A);
            Asistencia asistencia50_4A = new Asistencia(alumno10_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia50_4A);
            Asistencia asistencia51_4A = new Asistencia(alumno11_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia51_4A);
            Asistencia asistencia52_4A = new Asistencia(alumno12_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia52_4A);
            Asistencia asistencia53_4A = new Asistencia(alumno13_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia53_4A);
            Asistencia asistencia54_4A = new Asistencia(alumno14_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), false);
            repositorioAsistencia.guardar(asistencia54_4A);
            Asistencia asistencia55_4A = new Asistencia(alumno15_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia55_4A);
            Asistencia asistencia56_4A = new Asistencia(alumno16_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), false);
            repositorioAsistencia.guardar(asistencia56_4A);
            Asistencia asistencia57_4A = new Asistencia(alumno17_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia57_4A);
            Asistencia asistencia58_4A = new Asistencia(alumno18_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia58_4A);
            Asistencia asistencia59_4A = new Asistencia(alumno19_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia59_4A);
            Asistencia asistencia60_4A = new Asistencia(alumno20_4A, ESO4A_mts, LocalDate.of(2023, 9, 27), false);
            repositorioAsistencia.guardar(asistencia60_4A);

            //MTS-DÍA 4
            Asistencia asistencia61_4A = new Asistencia(alumno1_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia61_4A);
            Asistencia asistencia62_4A = new Asistencia(alumno2_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia62_4A);
            Asistencia asistencia63_4A = new Asistencia(alumno3_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), false);
            repositorioAsistencia.guardar(asistencia63_4A);
            Asistencia asistencia64_4A = new Asistencia(alumno4_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia64_4A);
            Asistencia asistencia65_4A = new Asistencia(alumno5_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia65_4A);
            Asistencia asistencia66_4A = new Asistencia(alumno6_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia66_4A);
            Asistencia asistencia67_4A = new Asistencia(alumno7_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia67_4A);
            Asistencia asistencia68_4A = new Asistencia(alumno8_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia68_4A);
            Asistencia asistencia69_4A = new Asistencia(alumno9_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia69_4A);
            Asistencia asistencia70_4A = new Asistencia(alumno10_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), false);
            repositorioAsistencia.guardar(asistencia70_4A);
            Asistencia asistencia71_4A = new Asistencia(alumno11_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia71_4A);
            Asistencia asistencia72_4A = new Asistencia(alumno12_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia72_4A);
            Asistencia asistencia73_4A = new Asistencia(alumno13_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia73_4A);
            Asistencia asistencia74_4A = new Asistencia(alumno14_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia74_4A);
            Asistencia asistencia75_4A = new Asistencia(alumno15_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia75_4A);
            Asistencia asistencia76_4A = new Asistencia(alumno16_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia76_4A);
            Asistencia asistencia77_4A = new Asistencia(alumno17_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia77_4A);
            Asistencia asistencia78_4A = new Asistencia(alumno18_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia78_4A);
            Asistencia asistencia79_4A = new Asistencia(alumno19_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), true);
            repositorioAsistencia.guardar(asistencia79_4A);
            Asistencia asistencia80_4A = new Asistencia(alumno20_4A, ESO4A_mts, LocalDate.of(2023, 9, 28), false);
            repositorioAsistencia.guardar(asistencia80_4A);

            //MTS-DÍA 5
            Asistencia asistencia81_4A = new Asistencia(alumno1_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia81_4A);
            Asistencia asistencia82_4A = new Asistencia(alumno2_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia82_4A);
            Asistencia asistencia83_4A = new Asistencia(alumno3_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia83_4A);
            Asistencia asistencia84_4A = new Asistencia(alumno4_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia84_4A);
            Asistencia asistencia85_4A = new Asistencia(alumno5_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia85_4A);
            Asistencia asistencia86_4A = new Asistencia(alumno6_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia86_4A);
            Asistencia asistencia87_4A = new Asistencia(alumno7_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia87_4A);
            Asistencia asistencia88_4A = new Asistencia(alumno8_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia88_4A);
            Asistencia asistencia89_4A = new Asistencia(alumno9_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia89_4A);
            Asistencia asistencia90_4A = new Asistencia(alumno10_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia90_4A);
            Asistencia asistencia91_4A = new Asistencia(alumno11_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia91_4A);
            Asistencia asistencia92_4A = new Asistencia(alumno12_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia92_4A);
            Asistencia asistencia93_4A = new Asistencia(alumno13_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia93_4A);
            Asistencia asistencia94_4A = new Asistencia(alumno14_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia94_4A);
            Asistencia asistencia95_4A = new Asistencia(alumno15_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia95_4A);
            Asistencia asistencia96_4A = new Asistencia(alumno16_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia96_4A);
            Asistencia asistencia97_4A = new Asistencia(alumno17_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia97_4A);
            Asistencia asistencia98_4A = new Asistencia(alumno18_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), false);
            repositorioAsistencia.guardar(asistencia98_4A);
            Asistencia asistencia99_4A = new Asistencia(alumno19_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia99_4A);
            Asistencia asistencia100_4A = new Asistencia(alumno20_4A, ESO4A_mts, LocalDate.of(2023, 9, 29), false);
            repositorioAsistencia.guardar(asistencia100_4A);

            //BIOLOGÍA-DÍA 1
            Asistencia asistencia101_4A = new Asistencia(alumno1_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia101_4A);
            Asistencia asistencia102_4A = new Asistencia(alumno2_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia102_4A);
            Asistencia asistencia103_4A = new Asistencia(alumno3_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), false);
            repositorioAsistencia.guardar(asistencia103_4A);
            Asistencia asistencia104_4A = new Asistencia(alumno4_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia104_4A);
            Asistencia asistencia105_4A = new Asistencia(alumno5_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia105_4A);
            Asistencia asistencia106_4A = new Asistencia(alumno6_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia106_4A);
            Asistencia asistencia107_4A = new Asistencia(alumno7_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia107_4A);
            Asistencia asistencia108_4A = new Asistencia(alumno8_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia108_4A);
            Asistencia asistencia109_4A = new Asistencia(alumno9_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), false);
            repositorioAsistencia.guardar(asistencia109_4A);
            Asistencia asistencia110_4A = new Asistencia(alumno10_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia110_4A);
            Asistencia asistencia111_4A = new Asistencia(alumno11_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia111_4A);
            Asistencia asistencia112_4A = new Asistencia(alumno12_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia112_4A);
            Asistencia asistencia113_4A = new Asistencia(alumno13_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia113_4A);
            Asistencia asistencia114_4A = new Asistencia(alumno14_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia114_4A);
            Asistencia asistencia115_4A = new Asistencia(alumno15_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia115_4A);
            Asistencia asistencia116_4A = new Asistencia(alumno16_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), false);
            repositorioAsistencia.guardar(asistencia116_4A);
            Asistencia asistencia117_4A = new Asistencia(alumno17_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia117_4A);
            Asistencia asistencia118_4A = new Asistencia(alumno18_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia118_4A);
            Asistencia asistencia119_4A = new Asistencia(alumno19_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), false);
            repositorioAsistencia.guardar(asistencia119_4A);
            Asistencia asistencia120_4A = new Asistencia(alumno20_4A, ESO4A_bio, LocalDate.of(2023, 9, 25), true);
            repositorioAsistencia.guardar(asistencia120_4A);

            //BIOLOGÍA-DÍA 2
            Asistencia asistencia121_4A = new Asistencia(alumno1_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), false);
            repositorioAsistencia.guardar(asistencia121_4A);
            Asistencia asistencia122_4A = new Asistencia(alumno2_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia122_4A);
            Asistencia asistencia123_4A = new Asistencia(alumno3_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia123_4A);
            Asistencia asistencia124_4A = new Asistencia(alumno4_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia124_4A);
            Asistencia asistencia125_4A = new Asistencia(alumno5_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia125_4A);
            Asistencia asistencia126_4A = new Asistencia(alumno6_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia126_4A);
            Asistencia asistencia127_4A = new Asistencia(alumno7_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia127_4A);
            Asistencia asistencia128_4A = new Asistencia(alumno8_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia128_4A);
            Asistencia asistencia129_4A = new Asistencia(alumno9_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia129_4A);
            Asistencia asistencia130_4A = new Asistencia(alumno10_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia130_4A);
            Asistencia asistencia131_4A = new Asistencia(alumno11_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia131_4A);
            Asistencia asistencia132_4A = new Asistencia(alumno12_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia132_4A);
            Asistencia asistencia133_4A = new Asistencia(alumno13_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), false);
            repositorioAsistencia.guardar(asistencia133_4A);
            Asistencia asistencia134_4A = new Asistencia(alumno14_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia134_4A);
            Asistencia asistencia135_4A = new Asistencia(alumno15_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia135_4A);
            Asistencia asistencia136_4A = new Asistencia(alumno16_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia136_4A);
            Asistencia asistencia137_4A = new Asistencia(alumno17_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia137_4A);
            Asistencia asistencia138_4A = new Asistencia(alumno18_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia138_4A);
            Asistencia asistencia139_4A = new Asistencia(alumno19_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), true);
            repositorioAsistencia.guardar(asistencia139_4A);
            Asistencia asistencia140_4A = new Asistencia(alumno20_4A, ESO4A_bio, LocalDate.of(2023, 9, 27), false);
            repositorioAsistencia.guardar(asistencia140_4A);

            //BIOLOGÍA-DÍA 3
            Asistencia asistencia141_4A = new Asistencia(alumno1_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia141_4A);
            Asistencia asistencia142_4A = new Asistencia(alumno2_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia142_4A);
            Asistencia asistencia143_4A = new Asistencia(alumno3_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia143_4A);
            Asistencia asistencia144_4A = new Asistencia(alumno4_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia144_4A);
            Asistencia asistencia145_4A = new Asistencia(alumno5_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia145_4A);
            Asistencia asistencia146_4A = new Asistencia(alumno6_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia146_4A);
            Asistencia asistencia147_4A = new Asistencia(alumno7_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia147_4A);
            Asistencia asistencia148_4A = new Asistencia(alumno8_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia148_4A);
            Asistencia asistencia149_4A = new Asistencia(alumno9_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), false);
            repositorioAsistencia.guardar(asistencia149_4A);
            Asistencia asistencia150_4A = new Asistencia(alumno10_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), false);
            repositorioAsistencia.guardar(asistencia150_4A);
            Asistencia asistencia151_4A = new Asistencia(alumno11_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia151_4A);
            Asistencia asistencia152_4A = new Asistencia(alumno12_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia152_4A);
            Asistencia asistencia153_4A = new Asistencia(alumno13_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia153_4A);
            Asistencia asistencia154_4A = new Asistencia(alumno14_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia154_4A);
            Asistencia asistencia155_4A = new Asistencia(alumno15_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia155_4A);
            Asistencia asistencia156_4A = new Asistencia(alumno16_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia156_4A);
            Asistencia asistencia157_4A = new Asistencia(alumno17_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), false);
            repositorioAsistencia.guardar(asistencia157_4A);
            Asistencia asistencia158_4A = new Asistencia(alumno18_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia158_4A);
            Asistencia asistencia159_4A = new Asistencia(alumno19_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia159_4A);
            Asistencia asistencia160_4A = new Asistencia(alumno20_4A, ESO4A_bio, LocalDate.of(2023, 9, 29), true);
            repositorioAsistencia.guardar(asistencia160_4A);

            //BIOLOGÍA-DÍA 4
            Asistencia asistencia161_4A = new Asistencia(alumno1_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia161_4A);
            Asistencia asistencia162_4A = new Asistencia(alumno2_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia162_4A);
            Asistencia asistencia163_4A = new Asistencia(alumno3_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia163_4A);
            Asistencia asistencia164_4A = new Asistencia(alumno4_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia164_4A);
            Asistencia asistencia165_4A = new Asistencia(alumno5_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia165_4A);
            Asistencia asistencia166_4A = new Asistencia(alumno6_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), false);
            repositorioAsistencia.guardar(asistencia166_4A);
            Asistencia asistencia167_4A = new Asistencia(alumno7_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), false);
            repositorioAsistencia.guardar(asistencia167_4A);
            Asistencia asistencia168_4A = new Asistencia(alumno8_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia168_4A);
            Asistencia asistencia169_4A = new Asistencia(alumno9_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia169_4A);
            Asistencia asistencia170_4A = new Asistencia(alumno10_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia170_4A);
            Asistencia asistencia171_4A = new Asistencia(alumno11_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia171_4A);
            Asistencia asistencia172_4A = new Asistencia(alumno12_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia172_4A);
            Asistencia asistencia173_4A = new Asistencia(alumno13_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), false);
            repositorioAsistencia.guardar(asistencia173_4A);
            Asistencia asistencia174_4A = new Asistencia(alumno14_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia174_4A);
            Asistencia asistencia175_4A = new Asistencia(alumno15_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia175_4A);
            Asistencia asistencia176_4A = new Asistencia(alumno16_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia176_4A);
            Asistencia asistencia177_4A = new Asistencia(alumno17_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia177_4A);
            Asistencia asistencia178_4A = new Asistencia(alumno18_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia178_4A);
            Asistencia asistencia179_4A = new Asistencia(alumno19_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia179_4A);
            Asistencia asistencia180_4A = new Asistencia(alumno20_4A, ESO4A_bio, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia180_4A);

            //BIOLOGÍA-DÍA 5
            Asistencia asistencia181_4A = new Asistencia(alumno1_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia181_4A);
            Asistencia asistencia182_4A = new Asistencia(alumno2_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia182_4A);
            Asistencia asistencia183_4A = new Asistencia(alumno3_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), false);
            repositorioAsistencia.guardar(asistencia183_4A);
            Asistencia asistencia184_4A = new Asistencia(alumno4_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia184_4A);
            Asistencia asistencia185_4A = new Asistencia(alumno5_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia185_4A);
            Asistencia asistencia186_4A = new Asistencia(alumno6_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia186_4A);
            Asistencia asistencia187_4A = new Asistencia(alumno7_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia187_4A);
            Asistencia asistencia188_4A = new Asistencia(alumno8_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia188_4A);
            Asistencia asistencia189_4A = new Asistencia(alumno9_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia189_4A);
            Asistencia asistencia190_4A = new Asistencia(alumno10_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia190_4A);
            Asistencia asistencia191_4A = new Asistencia(alumno11_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia191_4A);
            Asistencia asistencia192_4A = new Asistencia(alumno12_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia192_4A);
            Asistencia asistencia193_4A = new Asistencia(alumno13_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), false);
            repositorioAsistencia.guardar(asistencia193_4A);
            Asistencia asistencia194_4A = new Asistencia(alumno14_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia194_4A);
            Asistencia asistencia195_4A = new Asistencia(alumno15_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia195_4A);
            Asistencia asistencia196_4A = new Asistencia(alumno16_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia196_4A);
            Asistencia asistencia197_4A = new Asistencia(alumno17_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia197_4A);
            Asistencia asistencia198_4A = new Asistencia(alumno18_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia198_4A);
            Asistencia asistencia199_4A = new Asistencia(alumno19_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), false);
            repositorioAsistencia.guardar(asistencia199_4A);
            Asistencia asistencia200_4A = new Asistencia(alumno20_4A, ESO4A_bio, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia200_4A);

            //PROG-DÍA 1
            Asistencia asistencia201_4A = new Asistencia(alumno1_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), false);
            repositorioAsistencia.guardar(asistencia201_4A);
            Asistencia asistencia202_4A = new Asistencia(alumno2_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia202_4A);
            Asistencia asistencia203_4A = new Asistencia(alumno3_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia203_4A);
            Asistencia asistencia204_4A = new Asistencia(alumno4_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia204_4A);
            Asistencia asistencia205_4A = new Asistencia(alumno5_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia205_4A);
            Asistencia asistencia206_4A = new Asistencia(alumno6_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia206_4A);
            Asistencia asistencia207_4A = new Asistencia(alumno7_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia207_4A);
            Asistencia asistencia208_4A = new Asistencia(alumno8_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), false);
            repositorioAsistencia.guardar(asistencia208_4A);
            Asistencia asistencia209_4A = new Asistencia(alumno9_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), false);
            repositorioAsistencia.guardar(asistencia209_4A);
            Asistencia asistencia210_4A = new Asistencia(alumno10_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia210_4A);
            Asistencia asistencia211_4A = new Asistencia(alumno11_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia211_4A);
            Asistencia asistencia212_4A = new Asistencia(alumno12_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia212_4A);
            Asistencia asistencia213_4A = new Asistencia(alumno13_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia213_4A);
            Asistencia asistencia214_4A = new Asistencia(alumno14_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia214_4A);
            Asistencia asistencia215_4A = new Asistencia(alumno15_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia215_4A);
            Asistencia asistencia216_4A = new Asistencia(alumno16_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia216_4A);
            Asistencia asistencia217_4A = new Asistencia(alumno17_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia217_4A);
            Asistencia asistencia218_4A = new Asistencia(alumno18_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), true);
            repositorioAsistencia.guardar(asistencia218_4A);
            Asistencia asistencia219_4A = new Asistencia(alumno19_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), false);
            repositorioAsistencia.guardar(asistencia219_4A);
            Asistencia asistencia220_4A = new Asistencia(alumno20_4A, ESO4A_prog, LocalDate.of(2023, 10, 2), false);

            //PROG-DÍA 2
            Asistencia asistencia221_4A = new Asistencia(alumno1_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia221_4A);
            Asistencia asistencia222_4A = new Asistencia(alumno2_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia222_4A);
            Asistencia asistencia223_4A = new Asistencia(alumno3_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia223_4A);
            Asistencia asistencia224_4A = new Asistencia(alumno4_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia224_4A);
            Asistencia asistencia225_4A = new Asistencia(alumno5_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia225_4A);
            Asistencia asistencia226_4A = new Asistencia(alumno6_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia226_4A);
            Asistencia asistencia227_4A = new Asistencia(alumno7_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia227_4A);
            Asistencia asistencia228_4A = new Asistencia(alumno8_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia228_4A);
            Asistencia asistencia229_4A = new Asistencia(alumno9_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), false);
            repositorioAsistencia.guardar(asistencia229_4A);
            Asistencia asistencia230_4A = new Asistencia(alumno10_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), false);
            repositorioAsistencia.guardar(asistencia230_4A);
            Asistencia asistencia231_4A = new Asistencia(alumno11_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), false);
            repositorioAsistencia.guardar(asistencia231_4A);
            Asistencia asistencia232_4A = new Asistencia(alumno12_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia232_4A);
            Asistencia asistencia233_4A = new Asistencia(alumno13_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia233_4A);
            Asistencia asistencia234_4A = new Asistencia(alumno14_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia234_4A);
            Asistencia asistencia235_4A = new Asistencia(alumno15_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia235_4A);
            Asistencia asistencia236_4A = new Asistencia(alumno16_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia236_4A);
            Asistencia asistencia237_4A = new Asistencia(alumno17_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia237_4A);
            Asistencia asistencia238_4A = new Asistencia(alumno18_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia238_4A);
            Asistencia asistencia239_4A = new Asistencia(alumno19_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia239_4A);
            Asistencia asistencia240_4A = new Asistencia(alumno20_4A, ESO4A_prog, LocalDate.of(2023, 10, 4), true);
            repositorioAsistencia.guardar(asistencia240_4A);

            //PROG-DÍA 3
            Asistencia asistencia241_4A = new Asistencia(alumno1_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia241_4A);
            Asistencia asistencia242_4A = new Asistencia(alumno2_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia242_4A);
            Asistencia asistencia243_4A = new Asistencia(alumno3_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia243_4A);
            Asistencia asistencia244_4A = new Asistencia(alumno4_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), false);
            repositorioAsistencia.guardar(asistencia244_4A);
            Asistencia asistencia245_4A = new Asistencia(alumno5_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), false);
            repositorioAsistencia.guardar(asistencia245_4A);
            Asistencia asistencia246_4A = new Asistencia(alumno6_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia246_4A);
            Asistencia asistencia247_4A = new Asistencia(alumno7_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia247_4A);
            Asistencia asistencia248_4A = new Asistencia(alumno8_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia248_4A);
            Asistencia asistencia249_4A = new Asistencia(alumno9_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia249_4A);
            Asistencia asistencia250_4A = new Asistencia(alumno10_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia250_4A);
            Asistencia asistencia251_4A = new Asistencia(alumno11_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia251_4A);
            Asistencia asistencia252_4A = new Asistencia(alumno12_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia252_4A);
            Asistencia asistencia253_4A = new Asistencia(alumno13_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia253_4A);
            Asistencia asistencia254_4A = new Asistencia(alumno14_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), false);
            repositorioAsistencia.guardar(asistencia254_4A);
            Asistencia asistencia255_4A = new Asistencia(alumno15_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), false);
            repositorioAsistencia.guardar(asistencia255_4A);
            Asistencia asistencia256_4A = new Asistencia(alumno16_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia256_4A);
            Asistencia asistencia257_4A = new Asistencia(alumno17_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia257_4A);
            Asistencia asistencia258_4A = new Asistencia(alumno18_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), false);
            repositorioAsistencia.guardar(asistencia258_4A);
            Asistencia asistencia259_4A = new Asistencia(alumno19_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia259_4A);
            Asistencia asistencia260_4A = new Asistencia(alumno20_4A, ESO4A_prog, LocalDate.of(2023, 10, 6), true);
            repositorioAsistencia.guardar(asistencia260_4A);

            //PROG-DÍA 4
            Asistencia asistencia261_4A = new Asistencia(alumno1_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), false);
            repositorioAsistencia.guardar(asistencia261_4A);
            Asistencia asistencia262_4A = new Asistencia(alumno2_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia262_4A);
            Asistencia asistencia263_4A = new Asistencia(alumno3_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia263_4A);
            Asistencia asistencia264_4A = new Asistencia(alumno4_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia264_4A);
            Asistencia asistencia265_4A = new Asistencia(alumno5_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia265_4A);
            Asistencia asistencia266_4A = new Asistencia(alumno6_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia266_4A);
            Asistencia asistencia267_4A = new Asistencia(alumno7_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia267_4A);
            Asistencia asistencia268_4A = new Asistencia(alumno8_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia268_4A);
            Asistencia asistencia269_4A = new Asistencia(alumno9_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), false);
            repositorioAsistencia.guardar(asistencia269_4A);
            Asistencia asistencia270_4A = new Asistencia(alumno10_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), false);
            repositorioAsistencia.guardar(asistencia270_4A);
            Asistencia asistencia271_4A = new Asistencia(alumno11_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia271_4A);
            Asistencia asistencia272_4A = new Asistencia(alumno12_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia272_4A);
            Asistencia asistencia273_4A = new Asistencia(alumno13_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia273_4A);
            Asistencia asistencia274_4A = new Asistencia(alumno14_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), false);
            repositorioAsistencia.guardar(asistencia274_4A);
            Asistencia asistencia275_4A = new Asistencia(alumno15_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia275_4A);
            Asistencia asistencia276_4A = new Asistencia(alumno16_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia276_4A);
            Asistencia asistencia277_4A = new Asistencia(alumno17_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia277_4A);
            Asistencia asistencia278_4A = new Asistencia(alumno18_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), false);
            repositorioAsistencia.guardar(asistencia278_4A);
            Asistencia asistencia279_4A = new Asistencia(alumno19_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia279_4A);
            Asistencia asistencia280_4A = new Asistencia(alumno20_4A, ESO4A_prog, LocalDate.of(2023, 10, 11), true);
            repositorioAsistencia.guardar(asistencia280_4A);

            //PROG-DÍA 5
            Asistencia asistencia281_4A = new Asistencia(alumno1_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia281_4A);
            Asistencia asistencia282_4A = new Asistencia(alumno2_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia282_4A);
            Asistencia asistencia283_4A = new Asistencia(alumno3_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia283_4A);
            Asistencia asistencia284_4A = new Asistencia(alumno4_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia284_4A);
            Asistencia asistencia285_4A = new Asistencia(alumno5_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), false);
            repositorioAsistencia.guardar(asistencia285_4A);
            Asistencia asistencia286_4A = new Asistencia(alumno6_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia286_4A);
            Asistencia asistencia287_4A = new Asistencia(alumno7_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia287_4A);
            Asistencia asistencia288_4A = new Asistencia(alumno8_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia288_4A);
            Asistencia asistencia289_4A = new Asistencia(alumno9_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia289_4A);
            Asistencia asistencia290_4A = new Asistencia(alumno10_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia290_4A);
            Asistencia asistencia291_4A = new Asistencia(alumno11_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia291_4A);
            Asistencia asistencia292_4A = new Asistencia(alumno12_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), false);
            repositorioAsistencia.guardar(asistencia292_4A);
            Asistencia asistencia293_4A = new Asistencia(alumno13_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia293_4A);
            Asistencia asistencia294_4A = new Asistencia(alumno14_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia294_4A);
            Asistencia asistencia295_4A = new Asistencia(alumno15_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia295_4A);
            Asistencia asistencia296_4A = new Asistencia(alumno16_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), false);
            repositorioAsistencia.guardar(asistencia296_4A);
            Asistencia asistencia297_4A = new Asistencia(alumno17_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia297_4A);
            Asistencia asistencia298_4A = new Asistencia(alumno18_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia298_4A);
            Asistencia asistencia299_4A = new Asistencia(alumno19_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), false);
            repositorioAsistencia.guardar(asistencia299_4A);
            Asistencia asistencia300_4A = new Asistencia(alumno20_4A, ESO4A_prog, LocalDate.of(2023, 10, 13), true);
            repositorioAsistencia.guardar(asistencia300_4A);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sesionDemo.close();
        }
    }

    private static Session sesionDemo;
}
