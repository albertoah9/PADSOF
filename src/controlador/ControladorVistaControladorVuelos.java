package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.Aeropuerto;
import modelo.ControladorAereo;
import vista.VistaControladorAsignarAlVuelo;
import vista.VistaControladorBuscarVuelo;
import vista.VistaControladorModificarVuelo;
import vista.VistaControladorVerificaciones;
import vista.VistaControladorVuelos;

/**
 * Controlador que gestiona la lógica y eventos para la vista de gestión de
 * vuelos.
 * 
 * Se encarga de enlazar las acciones de los botones en la interfaz gráfica con
 * la apertura de las vistas correspondientes y su respectivo controlador,
 * además de manejar la navegación entre ventanas.
 */
public class ControladorVistaControladorVuelos {

    private VistaControladorVuelos vista;
    private Aeropuerto aeropuerto;
    private JFrame vistaAnterior;
    private ControladorAereo controladorAereo;

    public ControladorVistaControladorVuelos(VistaControladorVuelos vista, Aeropuerto aeropuerto, JFrame vistaPrincipal, ControladorAereo controlador, JFrame vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.controladorAereo = controlador;

        this.vista.btnBuscarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorBuscarVuelo vistaBuscarVuelo = new VistaControladorBuscarVuelo();
                ControladorVistaControladorBuscarVuelos controladorBuscarVuelo = new ControladorVistaControladorBuscarVuelos(
                        vistaBuscarVuelo, aeropuerto, vista);
                controladorBuscarVuelo.iniciar();
                vista.setVisible(false);
            }
        });

        this.vista.btnModificarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorModificarVuelo vistaModificarVuelo = new VistaControladorModificarVuelo();
                ControladorVistaControladorModificarVuelo controladorModificarVuelo = new ControladorVistaControladorModificarVuelo(
                        vistaModificarVuelo, aeropuerto, vista);
                controladorModificarVuelo.iniciar();
                vista.setVisible(false);

            }
        });

        this.vista.btnAsignacionesVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorAsignarAlVuelo vistaAsignarAlVuelo = new VistaControladorAsignarAlVuelo();
                ControladorVistaControladorAsignarAlVuelo controladorAsignarAlVuelo = new ControladorVistaControladorAsignarAlVuelo(
                        vistaAsignarAlVuelo, controladorAereo, aeropuerto, vista);
                controladorAsignarAlVuelo.iniciar();
                vista.setVisible(false);

            }
        });

        this.vista.btnVerificaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorVerificaciones vistaVerificaciones = new VistaControladorVerificaciones();
                ControladorVistaControladorVerificaciones controladorVerificaciones = new ControladorVistaControladorVerificaciones(
                        vistaVerificaciones, aeropuerto, vista);
                controladorVerificaciones.iniciar();
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

    /**
     * Muestra la vista de gestión de vuelos.
     */
    public void iniciar() {
        vista.setVisible(true);
    }
}