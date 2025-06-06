package controlador;

import vista.VistaControladorNotificaciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ControladorVistaControladorNotificaciones {

    private VistaControladorNotificaciones vista;
    private JFrame vistaAnterior;


    public ControladorVistaControladorNotificaciones(VistaControladorNotificaciones vista, JFrame vistaAnterior) {
        this.vista = vista;
        this.vistaAnterior = vistaAnterior;


        // unimos la aprte visual con la logica
        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                vistaAnterior.setVisible(true);
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}