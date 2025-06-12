package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import modelo.Aeropuerto;
import modelo.Notificacion;
import modelo.Usuario;
import vista.VistaOperadorNotificaciones;
import vista.VistaOperadorPrincipal;

public class ControladorVistaOperadorNotificaciones {

    private VistaOperadorNotificaciones vista;
    private Aeropuerto aeropuerto;
    private VistaOperadorPrincipal vistaAnterior;
    private Usuario usuario;

    private List<Notificacion> notificaciones;

    public ControladorVistaOperadorNotificaciones(VistaOperadorNotificaciones vista, Aeropuerto aeropuerto, VistaOperadorPrincipal vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;
        this.usuario = aeropuerto.getUsuarioActivo();

        this.notificaciones = usuario.getNotificaciones();

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
        int i = 1;
        for (Notificacion n : notificaciones) {
            String estado = n.isLeida() ? "Leída" : "No leída";
            String texto = String.format("(%s) [%s] %s", n.getFecha(), estado, n.getMensaje());
            vista.agregarNotificacion(texto);
            i++;
        }
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}