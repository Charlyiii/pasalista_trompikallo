package com.ferreiro.proyecto.ui;


import com.ferreiro.proyecto.controller.controllerDB.Demo;
import com.ferreiro.proyecto.controller.controllerDB.EmpleadoNoexisteException;
import com.ferreiro.proyecto.controller.controllerDB.RepositorioProfesor;
import com.ferreiro.proyecto.controller.controllerUI.CambiarPanel;
import com.ferreiro.proyecto.controller.controllerUI.Login;
import com.ferreiro.proyecto.model.entities.Profesor;
import org.hibernate.Session;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PantallaLogin extends JFrame {

    private static final long serialVersionUID = 1L;

    public PantallaLogin() {
        ImageIcon icono = new ImageIcon(getClass().getResource("/icons/Logo120.png"));
        setIconImage(icono.getImage());
        initComponents();

        //---- Centramos el frame en el centro de la pantalla

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Obtén el tamaño del JFrame
        int frameWidth = getWidth();
        int frameHeight = getHeight();

        // Calcula las coordenadas para centrar el JFrame
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        // Establece las coordenadas del JFrame
        setLocation(x, y);

        //--- Eventos de los botones

        // --Login
        botonLogin.addActionListener(new Login(textUser, textPass, panelPrincipal, this));

        //Mapeamos el botón de login al enter en el textfield de la contraseña
        Login.enterLogin(textPass, botonLogin);

        //Botón demo
        botonDemo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //Arrancamos la base de datos de la demo
                Session sesion = Demo.arrancarDemo();

                //Recuperamos el profesor de la demo
                RepositorioProfesor repositorioProfesor = new RepositorioProfesor(sesion);
                Profesor profesor;
                try {
                    profesor = repositorioProfesor.recuperarPorId(1);
                } catch (EmpleadoNoexisteException ex) {
                    throw new RuntimeException(ex);
                }
                //Cambiamos panel con los datos del profesor
                CambiarPanel cambiarPanel = new CambiarPanel(panelPrincipal, new ProfesorUI(marco, profesor , sesion));
                cambiarPanel.cambioPanel();
            }
        });
    }

    private void initComponents() {

        panelPrincipal = new JPanel();
        panelIzqda = new JPanel();
        panelBienvenida = new JPanel();
        panelTitulo = new JPanel();
        labelTitulo = new JLabel();
        panelDibujo = new JPanel();
        labelDibujo = new JLabel();
        panelDcha = new JPanel();
        panelLogo = new JPanel();
        labelLogo = new JLabel();
        panelUser = new JPanel();
        panelLabelUser = new JPanel();
        labelUser = new JLabel();
        panelTextUser = new JPanel();
        textUser = new JTextField();
        iconUser = new JLabel();
        panelPass = new JPanel();
        panelLabelPass = new JPanel();
        labelPass = new JLabel();
        panelTextPass = new JPanel();
        textPass = new JPasswordField();
        iconPass = new JLabel();
        panelBotonLogin = new JPanel();
        botonLogin = new JButton();
        panelSaludo = new JPanel();
        labelSaludo = new JLabel();
        panelDemo = new JPanel();
        botonDemo = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PasaLista - Colegio Trompikallo");

        panelPrincipal.setBackground(new Color(204, 255, 255));
        panelPrincipal.setLayout(new GridLayout(0, 2));

        panelIzqda.setBackground(new Color(127, 204, 228));
        panelIzqda.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 65));
        panelIzqda.setLayout(new BorderLayout());

        panelBienvenida.setBackground(new Color(204, 204, 204));

        panelTitulo.setBackground(new Color(3, 119, 200));

        labelTitulo.setFont(new Font("Yu Gothic UI", 1, 30)); // NOI18N
        labelTitulo.setForeground(new Color(255, 255, 255));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setText("PASALISTA TROMPIKALLO");
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(5, 1, 1, 1));
        panelTitulo.add(labelTitulo);

        panelDibujo.setBackground(new Color(204, 204, 204));

        GroupLayout panelDibujoLayout = new GroupLayout(panelDibujo);
        panelDibujo.setLayout(panelDibujoLayout);
        panelDibujoLayout.setHorizontalGroup(panelDibujoLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 65, Short.MAX_VALUE));
        panelDibujoLayout.setVerticalGroup(panelDibujoLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 707, Short.MAX_VALUE));

        labelDibujo.setHorizontalAlignment(SwingConstants.CENTER);
        labelDibujo.setIcon(new ImageIcon(getClass().getResource("/icons/imagenBienvenida.jpg"))); // NOI18N

        GroupLayout panelBienvenidaLayout = new GroupLayout(panelBienvenida);
        panelBienvenida.setLayout(panelBienvenidaLayout);
        panelBienvenidaLayout
                .setHorizontalGroup(panelBienvenidaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelTitulo, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBienvenidaLayout.createSequentialGroup().addComponent(labelDibujo)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelDibujo, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        panelBienvenidaLayout.setVerticalGroup(panelBienvenidaLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelBienvenidaLayout.createSequentialGroup()
                        .addComponent(panelTitulo, GroupLayout.PREFERRED_SIZE, 68,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBienvenidaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(panelDibujo, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelDibujo))));

        panelIzqda.add(panelBienvenida, BorderLayout.CENTER);

        panelPrincipal.add(panelIzqda);

        panelDcha.setBackground(new Color(127, 204, 228));
        panelDcha.setLayout(new GridLayout(5, 0));

        panelLogo.setBackground(new Color(127, 204, 228));
        panelLogo.setLayout(new FlowLayout(java.awt.FlowLayout.RIGHT));

        labelLogo.setIcon(new ImageIcon(getClass().getResource("/icons/Logo120.png"))); // NOI18N
        labelLogo.setToolTipText("");
        panelLogo.add(labelLogo);

        panelDcha.add(panelLogo);

        panelUser.setBackground(new Color(127, 204, 228));
        panelUser.setLayout(new GridLayout(2, 0));

        panelLabelUser.setBackground(new Color(127, 204, 228));

        labelUser.setFont(new Font("Yu Gothic UI", 1, 22)); // NOI18N
        labelUser.setForeground(new Color(255, 255, 255));
        labelUser.setText("Usuario");

        GroupLayout panelLabelUserLayout = new GroupLayout(panelLabelUser);
        panelLabelUser.setLayout(panelLabelUserLayout);
        panelLabelUserLayout
                .setHorizontalGroup(panelLabelUserLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLabelUserLayout.createSequentialGroup().addGap(103, 103, 103)
                                .addComponent(labelUser).addContainerGap(580, Short.MAX_VALUE)));
        panelLabelUserLayout
                .setVerticalGroup(panelLabelUserLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, panelLabelUserLayout
                                .createSequentialGroup().addGap(0, 52, Short.MAX_VALUE).addComponent(labelUser)));

        panelUser.add(panelLabelUser);

        panelTextUser.setBackground(new java.awt.Color(127, 204, 228));

        textUser.setFont(new Font("Segoe UI", 0, 24));
        textUser.setHorizontalAlignment(JTextField.CENTER);
        textUser.setBorder(null);

        iconUser.setIcon(new ImageIcon(getClass().getResource("/icons/user.png"))); // NOI18N

        GroupLayout panelTextUserLayout = new GroupLayout(panelTextUser);
        panelTextUser.setLayout(panelTextUserLayout);
        panelTextUserLayout.setHorizontalGroup(panelTextUserLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelTextUserLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(iconUser)
                        .addGap(18, 18, 18)
                        .addComponent(textUser, GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(55, Short.MAX_VALUE)));
        panelTextUserLayout.setVerticalGroup(panelTextUserLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelTextUserLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelTextUserLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(iconUser)
                                .addComponent(textUser, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(12, Short.MAX_VALUE)));


        panelUser.add(panelTextUser);

        panelDcha.add(panelUser);

        panelPass.setBackground(new Color(127, 204, 228));
        panelPass.setLayout(new GridLayout(2, 0));

        panelLabelPass.setBackground(new Color(127, 204, 228));

        labelPass.setFont(new Font("Yu Gothic UI", 1, 22)); // NOI18N
        labelPass.setForeground(new Color(255, 255, 255));
        labelPass.setText("Contraseña");

        GroupLayout panelLabelPassLayout = new GroupLayout(panelLabelPass);
        panelLabelPass.setLayout(panelLabelPassLayout);
        panelLabelPassLayout
                .setHorizontalGroup(panelLabelPassLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLabelPassLayout.createSequentialGroup().addGap(102, 102, 102)
                                .addComponent(labelPass).addContainerGap(544, Short.MAX_VALUE)));
        panelLabelPassLayout.setVerticalGroup(
                panelLabelPassLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                        GroupLayout.Alignment.TRAILING, panelLabelPassLayout.createSequentialGroup()
                                .addContainerGap(46, Short.MAX_VALUE).addComponent(labelPass).addContainerGap()));

        panelPass.add(panelLabelPass);

        panelTextPass.setBackground(new Color(127, 204, 228));
        panelTextPass.setBorder(new EmptyBorder(0, 9, 0, 0));


        textPass.setFont(new Font("Segoe UI", 0, 24));
        textPass.setHorizontalAlignment(JTextField.CENTER);
        textPass.setBorder(null);


        iconPass.setIcon(new ImageIcon(getClass().getResource("/icons/password-50.png"))); // NOI18N
        iconPass.setBorder(new EmptyBorder(0, 0, 0, 5));
        GroupLayout panelTextPassLayout = new GroupLayout(panelTextPass);
        panelTextPass.setLayout(panelTextPassLayout);
        panelTextPassLayout.setHorizontalGroup(panelTextPassLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelTextPassLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(iconPass)
                        .addGap(18, 18, 18)
                        .addComponent(textPass, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(55, Short.MAX_VALUE)));
        panelTextPassLayout.setVerticalGroup(panelTextPassLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelTextPassLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelTextPassLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(iconPass)
                                .addComponent(textPass, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(12, Short.MAX_VALUE)));

        panelPass.add(panelTextPass);

        panelDcha.add(panelPass);


        panelBotonLogin.setBackground(new Color(127, 204, 228));
        panelBotonLogin.setBorder(BorderFactory.createEmptyBorder(50, 175, 50, 100));
        panelBotonLogin.setLayout(null);

        botonLogin.setBackground(new Color(3, 119, 200));
        botonLogin.setFont(new Font("Yu Gothic UI", 1, 24)); // NOI18N
        botonLogin.setForeground(new Color(255, 255, 255));
        botonLogin.setText("Login");
        botonLogin.setToolTipText("Inicio de sesión para profesores y gestores");
        panelBotonLogin.add(botonLogin);
        botonLogin.setBounds(190, 50, 390, 40);

        panelDcha.add(panelBotonLogin);

        panelSaludo.setBackground(new Color(127, 204, 228));
        panelSaludo.setLayout(new GridLayout(2, 0));

        labelSaludo.setFont(new Font("Yu Gothic UI", 1, 60)); // NOI18N
        labelSaludo.setForeground(new Color(255, 255, 255));
        labelSaludo.setHorizontalAlignment(SwingConstants.CENTER);
        labelSaludo.setText("Bienvenid@!!!");
        panelSaludo.add(labelSaludo);

        panelDemo.setBackground(new Color(127, 204, 228));
        panelDemo.setBorder(BorderFactory.createEmptyBorder(30, 1, 1, 10));
        panelDemo.setLayout(new FlowLayout(java.awt.FlowLayout.RIGHT));

        botonDemo.setBackground(new Color(3, 119, 200));
        botonDemo.setForeground(new Color(255, 255, 255));
        botonDemo.setText("Demo");
        botonDemo.setToolTipText("Prueba PasaLista con nuestra base de datos ");
        panelDemo.add(botonDemo);

        panelSaludo.add(panelDemo);

        panelDcha.add(panelSaludo);

        panelPrincipal.add(panelDcha);


        add(panelPrincipal, BorderLayout.CENTER);

        setSize(Toolkit.getDefaultToolkit().getScreenSize());

        //pack(); //Redimensiona el Frame para que se vean todos los componentes
    }

    private JButton botonLogin;
    private JLabel iconPass;
    private JLabel iconUser;
    private JButton botonDemo;
    private JPanel panelDemo;
    private JLabel labelDibujo;
    private JLabel labelLogo;
    private JLabel labelPass;
    private JLabel labelSaludo;
    private JLabel labelTitulo;
    private JLabel labelUser;
    private JPanel panelBienvenida;
    private JPanel panelBotonLogin;
    private JPanel panelDcha;
    private JPanel panelDibujo;
    private JPanel panelIzqda;
    private JPanel panelLabelPass;
    private JPanel panelLabelUser;
    private JPanel panelPrincipal;
    private JPanel panelLogo;
    private JPanel panelPass;
    private JPanel panelSaludo;
    private JPanel panelTextPass;
    private JPanel panelTextUser;
    private JPanel panelTitulo;
    private JPanel panelUser;
    private JPasswordField textPass;
    private JTextField textUser;
    private JFrame marco = this;
   

}
