package controlador;

import vista.VistaGestorPrincipal;
import modelo.GestorAeropuerto;

import controlador.*;

import javax.swing.SwingUtilities;

public class ControladorVistaGestorPrincipal {
    private VistaGestorPrincipal vistaPrincipal;
    private GestorAeropuerto gestor;

    // Controladores de las pestañas principales
    private ControladorVistaGestionesAsignaciones controladorGestionesAsignaciones;
    private ControladorVistaHistorial controladorHistorial;
    private ControladorVistaGestorPagosFacturas controladorPagosFacturas;
    private ControladorVistaGestorVuelosActivos controladorVuelosActivos;
    private ControladorVistaGestorSeguridadVuelo controladorSeguridadVuelo;


    public ControladorVistaGestorPrincipal(VistaGestorPrincipal vistaPrincipal, GestorAeropuerto gestor) {
        this.vistaPrincipal = vistaPrincipal;
        this.gestor = gestor;

        // Inicializar controladores de las vistas secundarias
        this.controladorGestionesAsignaciones = new ControladorVistaGestionesAsignaciones(vistaPrincipal.getVistaGestionesAsignaciones(), gestor);
        this.controladorHistorial = new ControladorVistaHistorial(vistaPrincipal.getVistaHistorial(), gestor);
        
        // ¡NUEVO! Inicializar los controladores para las nuevas pestañas
        this.controladorPagosFacturas = new ControladorVistaGestorPagosFacturas(vistaPrincipal.getVistaGestorPagosFacturas(), gestor);
        this.controladorVuelosActivos = new ControladorVistaGestorVuelosActivos(vistaPrincipal.getVistaGestorVuelosActivos(), gestor);
        this.controladorSeguridadVuelo = new ControladorVistaGestorSeguridadVuelo(vistaPrincipal.getVistaGestorSeguridadVuelo(), gestor);
    }

    public void iniciar() {
        vistaPrincipal.setVisible(true);
    }

}