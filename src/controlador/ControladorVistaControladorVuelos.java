package controlador;

import vista.VistaControladorVuelos;
import vista.VistaControladorBuscarVuelo;
import vista.VistaControladorModificarVuelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaControladorVuelos {

    private VistaControladorVuelos vista;

    public ControladorVistaControladorVuelos(VistaControladorVuelos vista) {
        this.vista = vista;

        this.vista.btnModificarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorBuscarVuelo vistaBuscarVuelo = new VistaControladorBuscarVuelo();
                ControladorVistaControladorBuscarVuelo controladorBuscarVuelo = new ControladorVistaControladorBuscarVuelo(
                        vistaBuscarVuelo);
                controladorBuscarVuelo.iniciar();
            }
        });

        this.vista.btnBuscarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorModificarVuelo vistaModificarVuelo = new VistaControladorModificarVuelo();
                ControladorVistaControladorModificarVuelo controladorModificarVuelo = new ControladorVistaControladorModificarVuelo(
                        vistaModificarVuelo);
                controladorModificarVuelo.iniciar();
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}