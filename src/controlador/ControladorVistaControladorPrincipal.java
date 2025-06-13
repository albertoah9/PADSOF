package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import modelo.*;
import vista.VistaControladorPrincipal;
import vista.VistaControladorVuelos;
import vista.VistaControladorDisponibilidad;
import vista.VistaControladorGraficos;
import vista.VistaControladorNotificaciones;

public class ControladorVistaControladorPrincipal {

    private VistaControladorPrincipal vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private JFrame vistaAnterior;
    private List<ElementoAeropuerto> elementos;
    private ArrayList<UsoElementoAeropuerto> usos;
    private List<String> listaNotificaciones;

    public ControladorVistaControladorPrincipal(VistaControladorPrincipal vista, Aeropuerto aeropuerto,
            Aerolinea aerolinea, JFrame vistaAnterior) {

        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;
        this.vistaAnterior = vistaAnterior;

        this.listaNotificaciones = new ArrayList<>();

        this.vista.btnVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaControladorVuelos vistaVuelos = new VistaControladorVuelos();
                ControladorVistaControladorVuelos controladorVuelos = new ControladorVistaControladorVuelos(
                        vistaVuelos, aeropuerto, aerolinea, vista, vista);
                controladorVuelos.iniciar();
            }
        });

        this.vista.btnDisponibilidad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorDisponibilidad vistaDisponibilidades = new VistaControladorDisponibilidad();

                ControladorVistaControladorDisponibilidad controladorDisponibilidad = new ControladorVistaControladorDisponibilidad(
                        vistaDisponibilidades, elementos, usos);
                controladorDisponibilidad.iniciar();
            }
        });

        this.vista.btnGraficos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorGraficos VistaGraficos = new VistaControladorGraficos();
                ControladorVistaControladorGraficos controladorGraficos = new ControladorVistaControladorGraficos(
                        VistaGraficos, vista);
                controladorGraficos.iniciar();
                vista.setVisible(false);
            }
        });

        this.vista.btnNotificaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorNotificaciones vistaNotificaciones = new VistaControladorNotificaciones();
                ControladorVistaControladorNotificaciones controladorNotificaciones = new ControladorVistaControladorNotificaciones(
                        vistaNotificaciones, vista, listaNotificaciones);
                controladorNotificaciones.iniciar();
                vista.setVisible(false);
            }
        });

    }

    public void iniciar() {
        vista.setVisible(true);
    }
}