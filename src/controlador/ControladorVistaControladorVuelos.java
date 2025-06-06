package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import vista.VistaControladorBuscarVuelo;
import vista.VistaControladorModificarVuelo;
import vista.VistaControladorVuelos;

public class ControladorVistaControladorVuelos {

    private VistaControladorVuelos vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private JFrame vistaAnterior;

    public ControladorVistaControladorVuelos(VistaControladorVuelos vista, Aeropuerto aeropuerto, Aerolinea aerolinea,
            JFrame vistaPrincipal, JFrame vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;

        this.vista.btnBuscarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorBuscarVuelo vistaBuscarVuelo = new VistaControladorBuscarVuelo();
                ControladorVistaControladorBuscarVuelo controladorBuscarVuelo = new ControladorVistaControladorBuscarVuelo(
                        vistaBuscarVuelo, vistaAnterior);
                controladorBuscarVuelo.iniciar();
                vista.setVisible(false);

            }
        });

        this.vista.btnModificarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorModificarVuelo vistaModificarVuelo = new VistaControladorModificarVuelo();
                ControladorVistaControladorModificarVuelo controladorModificarVuelo = new ControladorVistaControladorModificarVuelo(
                        vistaModificarVuelo, vistaAnterior);
                controladorModificarVuelo.iniciar();
                vista.setVisible(false);

            }
        });

        this.vista.btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                vistaPrincipal.setVisible(true);
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}