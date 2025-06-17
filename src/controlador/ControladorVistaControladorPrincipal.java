package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import modelo.*;
import vista.VistaControladorDisponibilidad;
import vista.VistaControladorGraficos;
import vista.VistaControladorNotificaciones;
import vista.VistaControladorPrincipal;
import vista.VistaControladorVueloSeguro;
import vista.VistaControladorVuelos;

public class ControladorVistaControladorPrincipal {

    private VistaControladorPrincipal vista;
    private Aeropuerto aeropuerto;
    private Vuelo vuelo;
    private JFrame vistaAnterior;
    private List<ElementoAeropuerto> elementos;
    private ArrayList<UsoElementoAeropuerto> usos;
    private List<String> listaNotificaciones;
    private List<Pista> pista;

    public ControladorVistaControladorPrincipal(VistaControladorPrincipal vista, Aeropuerto aeropuerto, JFrame vistaAnterior) {

        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;

        this.listaNotificaciones = new ArrayList<>();
        this.elementos = aeropuerto.getElementosAeropuerto();
        this.pista = aeropuerto.getPistas();
        this.usos = new ArrayList<>();

        this.vista.btnVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaControladorVuelos vistaVuelos = new VistaControladorVuelos();
                ControladorVistaControladorVuelos controladorVuelos = new ControladorVistaControladorVuelos(
                        vistaVuelos, aeropuerto, vista, vista);
                controladorVuelos.iniciar();
            }
        });

        this.vista.btnDisponibilidad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorDisponibilidad vistaDisponibilidades = new VistaControladorDisponibilidad();

                ControladorVistaControladorDisponibilidad controladorDisponibilidad = new ControladorVistaControladorDisponibilidad(
                        vistaDisponibilidades,
                        elementos,
                        pista,
                        usos,
                        vista);

                controladorDisponibilidad.iniciar();
                vista.setVisible(false);
            }
        });

        this.vista.btnGraficos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorGraficos vistaGraficos = new VistaControladorGraficos();

                Estadisticas estadisticas = new Estadisticas(
                        aeropuerto.getHangares(),
                        aeropuerto.getAparcamientos(),
                        aeropuerto.getPuertasEmbarque(),
                        List.of(vuelo.getFinger()),
                        aeropuerto.getVuelos());

                ControladorVistaControladorGraficos controladorGraficos = new ControladorVistaControladorGraficos(
                        vistaGraficos, vista, estadisticas);
                controladorGraficos.iniciar();
                vista.setVisible(false);
            }
        });

        this.vista.btnNotificaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorNotificaciones vistaNotificaciones = new VistaControladorNotificaciones();
                ControladorVistaControladorNotificaciones controladorNotificaciones = new ControladorVistaControladorNotificaciones(
                        vistaNotificaciones, aeropuerto, vista);
                controladorNotificaciones.iniciar();
                vista.setVisible(false);
            }
        });

        this.vista.btnVueloSeguro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorVueloSeguro vistaVueloSeguro = new VistaControladorVueloSeguro();
                ControladorVistaControladorVueloSeguro controladorVueloSeguro = new ControladorVistaControladorVueloSeguro(
                        vistaVueloSeguro, vista);
                controladorVueloSeguro.iniciar();
                vista.setVisible(false);
            }
        });

    }

    public void iniciar() {
        vista.setVisible(true);
    }
}