package controlador;

import vista.VistaControladorModificarVuelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaControladorModificarVuelo {

    private VistaControladorModificarVuelo vista;

    public ControladorVistaControladorModificarVuelo(VistaControladorModificarVuelo vista) {
        this.vista = vista;

        // unimos la aprte visual con la logica
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}
