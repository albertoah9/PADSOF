package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import vista.VistaOperadorAviones;
import vista.VistaOperadorEliminarAvion;
import vista.VistaOperadorRegistrarAvion;
import vista.VistaOperadorVerFlota;

public class ControladorVistaOperadorAviones {

    private VistaOperadorAviones vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private JFrame vistaAnterior;

    public ControladorVistaOperadorAviones(VistaOperadorAviones vista, Aeropuerto aeropuerto, Aerolinea aerolinea, JFrame vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;
        this.vistaAnterior = vistaAnterior;

        this.vista.btnVerFlota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaOperadorVerFlota vistaFlota = new VistaOperadorVerFlota();
                ControladorVistaOperadorVerFlota controladorFlota =
                    new ControladorVistaOperadorVerFlota(vistaFlota, aerolinea, vista);
                controladorFlota.iniciar();
                vista.setVisible(false);
            }
        });

        this.vista.btnRegistrarAvion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaOperadorRegistrarAvion vistaRegistrar = new VistaOperadorRegistrarAvion();
                ControladorVistaOperadorRegistrarAvion controladorRegistrar =
                    new ControladorVistaOperadorRegistrarAvion(vistaRegistrar, aerolinea, vista);
                controladorRegistrar.iniciar();
                vista.setVisible(false);
            }
        });

        vista.btnEliminarAvion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaOperadorEliminarAvion vistaEliminar = new VistaOperadorEliminarAvion();
                ControladorVistaOperadorEliminarAvion controladorEliminar =
                    new ControladorVistaOperadorEliminarAvion(vistaEliminar, aerolinea, vista);
                controladorEliminar.iniciar();
                vista.setVisible(false);
            }
        });

        this.vista.btnVolver.addActionListener(new ActionListener() {
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
