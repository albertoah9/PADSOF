package controlador;

import vista.VistaControladorDisponibilidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaControladorDisponibilidad {

    private VistaControladorDisponibilidad vista;

    public ControladorVistaControladorDisponibilidad(VistaControladorDisponibilidad vista) {
        this.vista = vista;

        // unimos la aprte visual con la logica
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}