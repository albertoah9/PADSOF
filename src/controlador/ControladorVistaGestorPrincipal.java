package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Aeropuerto;
import modelo.GestorAeropuerto;
import vista.VistaGestorNotificaciones;
import vista.VistaGestorPrincipal;
import vista.VistaGestorUsuarios;

public class ControladorVistaGestorPrincipal {

    private VistaGestorPrincipal vista;
    private Aeropuerto aeropuerto;
    private GestorAeropuerto gestor;

    public ControladorVistaGestorPrincipal(VistaGestorPrincipal vista, Aeropuerto aeropuerto, GestorAeropuerto gestor) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.gestor = gestor;

        this.vista.btnUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaGestorUsuarios vistaUsuarios = new VistaGestorUsuarios();
                ControladorVistaGestorUsuarios controladorUsuarios = new ControladorVistaGestorUsuarios(
                    vistaUsuarios, gestor, vista
                );
                controladorUsuarios.iniciar();
            }
        });

        this.vista.btnElementos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir gestión de elementos aeropuerto");
            }
        });

        this.vista.btnNotificaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaGestorNotificaciones vistaNotis = new VistaGestorNotificaciones();
                ControladorVistaGestorNotificaciones controladorNotis =
                    new ControladorVistaGestorNotificaciones(vistaNotis, gestor, vista);
                vista.setVisible(false);
                controladorNotis.iniciar();
            }
        });

        this.vista.btnFacturacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir facturación");
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}