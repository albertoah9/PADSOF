package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Aerolinea;
import modelo.Aeropuerto;
import vista.VistaControladorPrincipal;
import vista.VistaControladorVuelos;
import vista.VistaControladorDisponibilidad;
import vista.VistaControladorGraficos;
import vista.VistaControladorNotificaciones;

public class ControladorVistaControladorPrincipal {

    private VistaControladorPrincipal vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;

    public ControladorVistaControladorPrincipal(VistaControladorPrincipal vista, Aeropuerto aeropuerto,
            Aerolinea aerolinea) {

        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;

        // Eventos
        this.vista.btnVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaControladorVuelos vistaVuelos = new VistaControladorVuelos();
                ControladorVistaControladorVuelos controladorVuelos = new ControladorVistaControladorVuelos(
                        vistaVuelos, aeropuerto, aerolinea, vista);
                controladorVuelos.iniciar();
            }
        });

        this.vista.btnDisponibilidad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorDisponibilidad vistaDisponibilidades = new VistaControladorDisponibilidad();
                ControladorVistaControladorDisponibilidad controladorDisponibilidad = new ControladorVistaControladorDisponibilidad(
                        vistaDisponibilidades);
                controladorDisponibilidad.iniciar();
            }
        });

        this.vista.btnGraficos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorGraficos VistaGraficos = new VistaControladorGraficos();
                ControladorVistaControladorGraficos controladorGraficos = new ControladorVistaControladorGraficos(
                        VistaGraficos);
                controladorGraficos.iniciar();
            }
        });

        this.vista.btnNotificaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorNotificaciones VistaNotificaciones = new VistaControladorNotificaciones();
                ControladorVistaControladorNotificaciones controladorNotificaciones = new ControladorVistaControladorNotificaciones(
                        VistaNotificaciones);
                controladorNotificaciones.iniciar();
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}