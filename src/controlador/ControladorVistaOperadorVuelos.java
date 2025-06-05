package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import vista.VistaOperadorCrearVuelo;
import vista.VistaOperadorEliminarVuelo;
import vista.VistaOperadorModificarEstadoVuelo;
import vista.VistaOperadorMostrarVuelos;
import vista.VistaOperadorVuelos;

import controlador.ControladorVistaOperadorCrearVuelo;

public class ControladorVistaOperadorVuelos {

    private VistaOperadorVuelos vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private JFrame vistaPrincipal;

    public ControladorVistaOperadorVuelos(VistaOperadorVuelos vista, Aeropuerto aeropuerto, Aerolinea aerolinea, JFrame vistaPrincipal) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaPrincipal = vistaPrincipal;
        this.aerolinea = aerolinea;
        
        this.vista.btnCrearVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaOperadorCrearVuelo vistaCrear = new VistaOperadorCrearVuelo();
                ControladorVistaOperadorCrearVuelo controladorCrear = new ControladorVistaOperadorCrearVuelo(vistaCrear, aeropuerto, aerolinea);
                controladorCrear.iniciar();
            }
        });

        this.vista.btnModificarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaOperadorModificarEstadoVuelo vistaModificar = new VistaOperadorModificarEstadoVuelo();
                ControladorVistaOperadorModificarEstadoVuelo controladorModificar = new ControladorVistaOperadorModificarEstadoVuelo(vistaModificar, aeropuerto, aerolinea, vista);
                controladorModificar.iniciar();
                vista.setVisible(false);
            }
        });

        vista.btnEliminarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaOperadorEliminarVuelo vistaEliminar = new VistaOperadorEliminarVuelo();
                ControladorVistaOperadorEliminarVuelo controladorEliminar =
                    new ControladorVistaOperadorEliminarVuelo(aeropuerto, vistaEliminar, aerolinea, vista);
                controladorEliminar.iniciar();
                vista.setVisible(false);
            }
        });

        this.vista.btnMostrarVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaOperadorMostrarVuelos vistaMostrar = new VistaOperadorMostrarVuelos();
                ControladorVistaOperadorMostrarVuelos controladorMostrar = new ControladorVistaOperadorMostrarVuelos(vistaMostrar, aeropuerto, aerolinea, vista);
                controladorMostrar.iniciar();
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