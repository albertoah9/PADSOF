package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Aeropuerto;
import modelo.GestorAeropuerto;
import vista.VistaGestorElementosAeropuerto;
import vista.VistaGestorFacturacion;
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
                VistaGestorElementosAeropuerto vistaElementos = new VistaGestorElementosAeropuerto();
                ControladorVistaGestorElementosAeropuerto controladorElementos =
                    new ControladorVistaGestorElementosAeropuerto(vistaElementos, aeropuerto, vista);
                vista.setVisible(false);
                controladorElementos.iniciar();
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
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaGestorFacturacion vistaFacturacion = new VistaGestorFacturacion();
                ControladorVistaGestorFacturacion controladorFacturacion =
                        new ControladorVistaGestorFacturacion(vistaFacturacion, gestor, vista);
                vista.setVisible(false);
                controladorFacturacion.iniciar();
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}