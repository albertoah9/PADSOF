package controlador;

import vista.VistaControladorNotificaciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaControladorNotificaciones {

    private VistaControladorNotificaciones vista;

    public ControladorVistaControladorNotificaciones(VistaControladorNotificaciones vista) {
        this.vista = vista;

        // unimos la aprte visual con la logica
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}