package controlador;

import vista.VistaControladorGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaControladorGraficos {

    private VistaControladorGraficos vista;

    public ControladorVistaControladorGraficos(VistaControladorGraficos vista) {
        this.vista = vista;

        // unimos la aprte visual con la logica
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}