package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import modelo.*;
import vista.VistaGestorNotificaciones;
import vista.VistaGestorPrincipal;

public class ControladorVistaGestorNotificaciones {

    private VistaGestorNotificaciones vista;
    private GestorAeropuerto gestor;
    private VistaGestorPrincipal vistaAnterior;
    private List<Notificacion> notificaciones;

    public ControladorVistaGestorNotificaciones(VistaGestorNotificaciones vista, GestorAeropuerto gestor, VistaGestorPrincipal vistaAnterior) {
        this.vista = vista;
        this.gestor = gestor;
        this.vistaAnterior = vistaAnterior;

        this.notificaciones = gestor.getNotificaciones();

        cargarNotificaciones();

        this.vista.listaNotificaciones.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = vista.listaNotificaciones.getSelectedIndex();
                    if (index >= 0 && index < notificaciones.size()) {
                        Notificacion noti = notificaciones.get(index);
                        noti.marcarComoLeida();
                        JOptionPane.showMessageDialog(vista, noti.getMensaje(), "Contenido de la Notificación", JOptionPane.INFORMATION_MESSAGE);
                        cargarNotificaciones();
                    }
                }
            }
        });

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });
    }

    private void cargarNotificaciones() {
        vista.limpiarLista();
        for (Notificacion n : notificaciones) {
            String estado = n.isLeida() ? "Leída" : "No leída";
            String texto = String.format("(%s) [%s] %s", n.getFecha(), estado, n.getMensaje());
            vista.agregarNotificacion(texto);
        }
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}
