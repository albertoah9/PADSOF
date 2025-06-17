package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import modelo.*;
import vista.VistaControladorDisponibilidad;
import vista.VistaControladorNotificaciones;
import vista.VistaControladorPrincipal;
import vista.VistaControladorVueloSeguro;
import vista.VistaControladorVuelos;

/**
 * Controlador principal que gestiona la ventana principal de la aplicación.
 * 
 * Se encarga de inicializar y manejar las acciones de los botones para navegar
 * entre las diferentes vistas del sistema, tales como vuelos, disponibilidad,
 * notificaciones y control de vuelo seguro.
 * 
 * Además, mantiene referencias a los elementos del aeropuerto, las pistas y
 * los usos de los elementos, así como una lista de notificaciones.
 */
public class ControladorVistaControladorPrincipal {

    private VistaControladorPrincipal vista;
    private Aeropuerto aeropuerto;
    private Vuelo vuelo;
    private JFrame vistaAnterior;
    private List<ElementoAeropuerto> elementos;
    private ArrayList<UsoElementoAeropuerto> usos;
    private List<String> listaNotificaciones;
    private List<Pista> pista;
    private ControladorAereo controladorAereo;

<<<<<<< HEAD
    public ControladorVistaControladorPrincipal(VistaControladorPrincipal vista, Aeropuerto aeropuerto, ControladorAereo controlador, JFrame vistaAnterior) {
=======
    /**
     * Constructor que inicializa el controlador con la vista principal, el
     * aeropuerto
     * y la vista anterior para facilitar la navegación.
     * 
     * @param vista         instancia de la vista principal
     * @param aeropuerto    aeropuerto que contiene los datos y elementos a manejar
     * @param vistaAnterior ventana anterior para regresar al cerrar esta vista
     */
    public ControladorVistaControladorPrincipal(VistaControladorPrincipal vista, Aeropuerto aeropuerto,
            JFrame vistaAnterior) {
>>>>>>> 3671a262ba9ca5bb0c043d52f65f3a834ed3e952

        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;
        this.controladorAereo = controlador;

        this.listaNotificaciones = new ArrayList<>();
        this.elementos = aeropuerto.getElementosAeropuerto();
        this.pista = aeropuerto.getPistas();
        this.usos = new ArrayList<>();

        this.vista.btnVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaControladorVuelos vistaVuelos = new VistaControladorVuelos();
                ControladorVistaControladorVuelos controladorVuelos = new ControladorVistaControladorVuelos(
                        vistaVuelos, aeropuerto, vista, controladorAereo, vista);
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

    /**
     * Muestra la vista principal.
     */
    public void iniciar() {
        vista.setVisible(true);
    }
}