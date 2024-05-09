package com.ferreiro.proyecto.controller.controllerUI;

import com.ferreiro.proyecto.controller.controllerDB.EmpleadoNoexisteException;
import com.ferreiro.proyecto.controller.controllerDB.HibernateUtil;
import com.ferreiro.proyecto.controller.controllerDB.PassworIncorrectaException;
import com.ferreiro.proyecto.controller.controllerDB.RepositorioEmpleado;
import com.ferreiro.proyecto.model.entities.Empleado;
import com.ferreiro.proyecto.model.entities.Gestor;
import com.ferreiro.proyecto.model.entities.Profesor;
import com.ferreiro.proyecto.ui.GestorUI;
import com.ferreiro.proyecto.ui.ProfesorUI;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login implements ActionListener {

    public Login(JTextField userText, JPasswordField passwordText, JPanel panelPrincpal, JFrame marco) {
        this.userText = userText;
        this.passwordText = passwordText;
        this.panelPrincpal = panelPrincpal;
        this.marco = marco;

    }

    public void actionPerformed(ActionEvent e) {
        user = userText.getText();
        password = passwordText.getText();

        try {
            sesion = HibernateUtil.getSessionFactory().openSession();

            RepositorioEmpleado repositorioEmpleado = new RepositorioEmpleado(sesion);

            Empleado empleado = repositorioEmpleado.recuperarPorUsuario(userText.getText());

            Empleado empleadoLogueado = repositorioEmpleado.recuperarPorLogin(passwordText.getText());

            //Si la contraseña está vacía, obligamos a que cree una nueva
            if (empleadoLogueado.getPassword().equals("")) {

                do {
                    passwordNuevo = JOptionPane.showInputDialog(null, "Introduzca una contraseña nueva");
                    confirmaciónPasswordNuevo = JOptionPane.showInputDialog(null, "Confirme la contraseña nueva");
                    if (!passwordNuevo.equals(confirmaciónPasswordNuevo)) {
                        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
                    }
                } while (!passwordNuevo.equals(confirmaciónPasswordNuevo));
                repositorioEmpleado.actualizarPassword(empleadoLogueado, passwordNuevo);
                JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente");

            } else {

                if (empleadoLogueado instanceof Gestor) {
                    Gestor gestor = (Gestor) empleadoLogueado;

                    CambiarPanel cambiarPanel = new CambiarPanel(panelPrincpal, new GestorUI(marco, gestor, sesion));
                    cambiarPanel.cambioPanel();


                } else {
                    Profesor profesor = (Profesor) empleadoLogueado;

                    CambiarPanel cambiarPanel = new CambiarPanel(panelPrincpal, new ProfesorUI(marco, profesor, sesion));
                    cambiarPanel.cambioPanel();
                }
            }
        } catch (EmpleadoNoexisteException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (PassworIncorrectaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }

    }

    //Método para hacer que al darle Enter en el campo de contraseña, se ejecute el botón de login
    public static void enterLogin(JPasswordField passwordText, final JButton botonLogin) {
        passwordText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                botonLogin.doClick();
            }
        });
    }

    JTextField userText;
    JTextField passwordText;
    String user;
    String password;
    JPanel panelPrincpal;
    JFrame marco;
    String passwordNuevo;
    String confirmaciónPasswordNuevo;
    private Session sesion;


}
