package controlador;

import vista.VistaControladorBuscarVuelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaControladorBuscarVuelo {

    private VistaControladorBuscarVuelo vista;

    public ControladorVistaControladorBuscarVuelo(VistaControladorBuscarVuelo vista) {
        this.vista = vista;

        // unimos la aprte visual con la logica
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}