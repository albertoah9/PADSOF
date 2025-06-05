package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import vista.VistaOperadorAviones;
import vista.VistaOperadorPrincipal;
import vista.VistaOperadorVuelos;

public class ControladorVistaOperadorPrincipal {

    private VistaOperadorPrincipal vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;

    public ControladorVistaOperadorPrincipal(VistaOperadorPrincipal vista, Aeropuerto aeropuerto, Aerolinea aerolinea) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;

        // Eventos
        this.vista.btnVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaOperadorVuelos vistaVuelos = new VistaOperadorVuelos();
                ControladorVistaOperadorVuelos controladorVuelos =
                    new ControladorVistaOperadorVuelos(vistaVuelos, aeropuerto, aerolinea, vista);
                controladorVuelos.iniciar();
            }
        });

        this.vista.btnAviones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaOperadorAviones vistaAviones = new VistaOperadorAviones();
                ControladorVistaOperadorAviones controladorAviones = 
                    new ControladorVistaOperadorAviones(vistaAviones, aeropuerto, aerolinea, vista);
                controladorAviones.iniciar();
            }
        });

        this.vista.btnFacturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clic en Gestionar Facturas");
            }
        });

        this.vista.btnNotificaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clic en Ver Notificaciones");
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}