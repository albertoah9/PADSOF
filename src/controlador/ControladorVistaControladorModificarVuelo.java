package controlador;

import vista.VistaControladorModificarVuelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ControladorVistaControladorModificarVuelo {

    private VistaControladorModificarVuelo vista;
    private JFrame vistaAnterior;

    public ControladorVistaControladorModificarVuelo(VistaControladorModificarVuelo vista, JFrame vistaAnterior) {
        this.vista = vista;

    this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                vistaAnterior.setVisible(true);
            }
        });    }

    public void iniciar() {
        vista.setVisible(true);
    }
}
