package controlador;

import vista.VistaControladorBuscarVuelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ControladorVistaControladorBuscarVuelo {

    private VistaControladorBuscarVuelo vista;
    private JFrame vistaAnterior;

    public ControladorVistaControladorBuscarVuelo(VistaControladorBuscarVuelo vista, JFrame vistaAnterior) {
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