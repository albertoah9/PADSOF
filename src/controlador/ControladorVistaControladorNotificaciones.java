package controlador;

import modelo.Aeropuerto;
import modelo.Notificacion;
import modelo.Usuario;
import vista.VistaControladorNotificaciones;
import vista.VistaControladorPrincipal;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Controlador para la gestión de notificaciones del usuario activo en el
 * aeropuerto.
 * 
 * Permite mostrar las notificaciones, marcar como leídas al hacer doble clic y
 * regresar a la vista principal.
 */
public class ControladorVistaControladorNotificaciones {

    private VistaControladorNotificaciones vista;
    private VistaControladorPrincipal vistaAnterior;
    private Aeropuerto aeropuerto;
    private Usuario usuario;
    private List<Notificacion> notificaciones;

    /**
     * Constructor que inicializa el controlador con la vista de notificaciones,
     * el aeropuerto y la vista anterior para regresar.
     * 
     * @param vista         vista de notificaciones a controlar
     * @param aeropuerto    aeropuerto que contiene los datos y usuario activo
     * @param vistaAnterior vista principal para regresar al cerrar esta vista
     */
    public ControladorVistaControladorNotificaciones(
            VistaControladorNotificaciones vista,
            Aeropuerto aeropuerto,
            VistaControladorPrincipal vistaAnterior) {

        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;
        this.usuario = aeropuerto.getUsuarioActivo();

        this.notificaciones = usuario.getNotificaciones();

        cargarNotificaciones();

        this.vista.getListaNotificaciones().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = vista.getListaNotificaciones().getSelectedIndex();
                    if (index >= 0 && index < notificaciones.size()) {
                        Notificacion noti = notificaciones.get(index);
                        noti.marcarComoLeida();
                        JOptionPane.showMessageDialog(vista, noti.getMensaje(), "Contenido de la Notificación",
                                JOptionPane.INFORMATION_MESSAGE);
                        cargarNotificaciones();
                    }
                }
            }
        });

        this.vista.btnVolver.addActionListener(_ -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });
    }

    /**
     * Carga las notificaciones del usuario activo en la lista de la vista,
     * mostrando el estado de cada una.
     */
    private void cargarNotificaciones() {
        vista.limpiarLista();
        for (Notificacion n : notificaciones) {
            String estado = n.isLeida() ? "Leída" : "No leída";
            String texto = String.format("(%s) [%s] %s", n.getFecha(), estado, n.getMensaje());
            vista.agregarNotificacion(texto);
        }
    }

    /**
     * Muestra la vista de notificaciones.
     */
    public void iniciar() {
        vista.setVisible(true);
    }
}
