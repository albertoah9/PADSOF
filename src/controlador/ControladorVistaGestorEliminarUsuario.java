package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.*;
import vista.*;

public class ControladorVistaGestorEliminarUsuario {

    private VistaGestorEliminarUsuario vista;
    private GestorAeropuerto gestor;
    private VistaGestorUsuarios vistaAnterior;

    public ControladorVistaGestorEliminarUsuario(GestorAeropuerto gestor, VistaGestorUsuarios vistaAnterior) {
        this.gestor = gestor;
        this.vistaAnterior = vistaAnterior;

        List<Usuario> eliminables = obtenerUsuariosEliminables(gestor);
        this.vista = new VistaGestorEliminarUsuario(eliminables);

        this.vista.btnEliminar.addActionListener(e -> {
            Usuario seleccionado = vista.getUsuarioSeleccionado();
            if (seleccionado == null) {
                vista.mostrarMensaje("Debes seleccionar un usuario.");
                return;
            }

            if (seleccionado instanceof OperadorAereo) {
                gestor.eliminarOperadorAereo((OperadorAereo) seleccionado);
            } else if (seleccionado instanceof ControladorAereo) {
                gestor.eliminarControladorAereo((ControladorAereo) seleccionado);
            }

            vista.mostrarMensaje("Usuario eliminado correctamente.");
            vista.setVisible(false);
            vistaAnterior.setVisible(true);
        });

        this.vista.btnCancelar.addActionListener(e -> {
            vista.setVisible(false);
            vistaAnterior.setVisible(true);
        });
    }

    private List<Usuario> obtenerUsuariosEliminables(GestorAeropuerto gestor) {
        List<Usuario> eliminables = new ArrayList<>();
        for (Usuario u : gestor.getUsuarios()) {
            if (!u.equals(gestor) && (u instanceof OperadorAereo || u instanceof ControladorAereo)) {
                eliminables.add(u);
            }
        }
        return eliminables;
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}