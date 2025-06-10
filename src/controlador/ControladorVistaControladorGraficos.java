package controlador;

import vista.VistaControladorGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ControladorVistaControladorGraficos {

    private VistaControladorGraficos vista;
    private JFrame vistaAnterior;


    public ControladorVistaControladorGraficos(VistaControladorGraficos vista, JFrame vistaAnterior) {
        this.vista = vista;
        this.vistaAnterior = vistaAnterior;

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