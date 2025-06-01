package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaOperadorPrincipal;
import vista.VistaOperadorVuelos;

public class ControladorVistaOperadorPrincipal {

    private VistaOperadorPrincipal vista;

    public ControladorVistaOperadorPrincipal(VistaOperadorPrincipal vista) {
        this.vista = vista;

        // Eventos
        this.vista.btnVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaOperadorVuelos vistaVuelos = new VistaOperadorVuelos();
                ControladorVistaOperadorVuelos controladorVuelos =
                    new ControladorVistaOperadorVuelos(vistaVuelos, vista);
                controladorVuelos.iniciar();
            }
        });

        this.vista.btnAviones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clic en Gestionar Aviones");
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