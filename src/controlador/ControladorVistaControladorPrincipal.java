package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaControladorPrincipal;
import vista.VistaControladorVuelos;
import vista.VistaControladorDisponibilidad;

public class ControladorVistaControladorPrincipal {

    private VistaControladorPrincipal vista;

    public ControladorVistaControladorPrincipal(VistaControladorPrincipal vista) {
        this.vista = vista;

        // Eventos
        this.vista.btnVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaControladorVuelos vistaVuelos = new VistaControladorVuelos();
                ControladorVistaControladorVuelos controladorVuelos = new ControladorVistaControladorVuelos(
                        vistaVuelos);
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
                System.out.println("Clic en Graficos");
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