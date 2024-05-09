package com.ferreiro.proyecto.application;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ferreiro.proyecto.controller.controllerDB.HibernateUtil;
import com.ferreiro.proyecto.ui.PantallaLogin;

public class Main {

    public static void main(String[] args) {
    	
    	try {
			Session sesion = HibernateUtil.getSessionFactory().openSession();
			PantallaLogin principal = new PantallaLogin();
			principal.setVisible(true);
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
      
    }
}
