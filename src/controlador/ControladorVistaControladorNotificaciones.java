package controlador;

import vista.VistaControladorNotificaciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorVistaControladorNotificaciones {

    private VistaControladorNotificaciones vista;
    private JFrame vistaAnterior;
    private List<String> listaNotificaciones;

    public ControladorVistaControladorNotificaciones(VistaControladorNotificaciones vista, JFrame vistaAnterior,
            List<String> listaNotificaciones) {
        this.vista = vista;
        this.vistaAnterior = vistaAnterior;
        this.listaNotificaciones = listaNotificaciones;

        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                vistaAnterior.setVisible(true);
            }
        });
    }

    public void iniciar() {
        actualizarVista();
        vista.setVisible(true);
    }

    public void actualizarVista() {
        String[] arrayNotificaciones = listaNotificaciones.toArray(new String[0]);
        vista.actualizarNotificaciones(arrayNotificaciones);
    }
}
