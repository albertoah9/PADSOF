package vista;

import java.awt.*;
import javax.swing.*;
import modelo.GestorAeropuerto;


public class VistaGestorPrincipal extends JFrame {

    private JTabbedPane tabbedPane;
    
    private VistaGestionesAsignaciones vistaGestionesAsignaciones; 
    private VistaHistorial vistaHistorial;
    // Nuevas referencias a las vistas
    private VistaGestorPagosFacturas vistaGestorPagosFacturas;
    private VistaGestorVuelosActivos vistaGestorVuelosActivos;
    private VistaGestorSeguridadVuelo vistaGestorSeguridadVuelo;


    public VistaGestorPrincipal(GestorAeropuerto gestor) {
        setTitle("Sistema de Gestión de Aeropuerto");
        setSize(1200, 800); // Aumentar tamaño para más pestañas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        // 1. Pestaña: Gestiones y Asignaciones (ya existente)
        vistaGestionesAsignaciones = new VistaGestionesAsignaciones(gestor); // Mantengo el GestorAeropuerto para que los sub-controladores lo reciban
        tabbedPane.addTab("Gestiones y Asignaciones", vistaGestionesAsignaciones);

        // 2. Pestaña: Historial (ya existente)
        vistaHistorial = new VistaHistorial();
        tabbedPane.addTab("Historial", vistaHistorial);
        
        // 3. Pestaña: Pagos y Facturas (¡NUEVA!)
        vistaGestorPagosFacturas = new VistaGestorPagosFacturas(gestor);
        tabbedPane.addTab("Pagos y Facturas", vistaGestorPagosFacturas);

        // 4. Pestaña: Vuelos Activos (¡NUEVA!)
        vistaGestorVuelosActivos = new VistaGestorVuelosActivos(gestor);
        tabbedPane.addTab("Vuelos Activos", vistaGestorVuelosActivos);

        // 5. Pestaña: Seguridad de Vuelo (¡NUEVA!)
        vistaGestorSeguridadVuelo = new VistaGestorSeguridadVuelo(gestor);
        tabbedPane.addTab("Seguridad de Vuelo", vistaGestorSeguridadVuelo);
    }


    public VistaGestionesAsignaciones getVistaGestionesAsignaciones() {
        return vistaGestionesAsignaciones;
    }

    public VistaHistorial getVistaHistorial() {
        return vistaHistorial;
    }

    public VistaGestorPagosFacturas getVistaGestorPagosFacturas() {
        return vistaGestorPagosFacturas;
    }

    public VistaGestorVuelosActivos getVistaGestorVuelosActivos() {
        return vistaGestorVuelosActivos;
    }

    public VistaGestorSeguridadVuelo getVistaGestorSeguridadVuelo() {
        return vistaGestorSeguridadVuelo;
    }

    public void mostrarVista(JPanel panel) {
        int index = tabbedPane.indexOfComponent(panel);
        if (index != -1) {
            tabbedPane.setSelectedIndex(index);
        } else {
            System.err.println("Advertencia: Intentando mostrar un panel que no está en el JTabbedPane principal.");
        }
    }
}