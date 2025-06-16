package controlador;

import modelo.*;
import vista.VistaGestorEliminarUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaGestorEliminarUsuario {

    private VistaGestorEliminarUsuario vista;
    private GestorAeropuerto gestor;

    public ControladorVistaGestorEliminarUsuario(VistaGestorEliminarUsuario vista, GestorAeropuerto gestor) {
        this.vista = vista;
        this.gestor = gestor;

        for (Usuario u : gestor.getUsuarios()) {
            if (u instanceof OperadorAereo || u instanceof ControladorAereo) {
                if (!u.getNombre().equals(gestor.getUsuarioActivo().getNombre())) {
                    vista.cmbUsuariosAEliminar.addItem(u.getNombre());
                }
            }
        }

        this.vista.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = (String) vista.cmbUsuariosAEliminar.getSelectedItem();
                if (nombre == null) return;

                Usuario usuarioAEliminar = gestor.getUsuarios().stream()
                        .filter(u -> u.getNombre().equals(nombre))
                        .findFirst()
                        .orElse(null);

                boolean eliminado = false;
                if (usuarioAEliminar instanceof OperadorAereo) {
                    eliminado = gestor.eliminarOperador(nombre);
                } else if (usuarioAEliminar instanceof ControladorAereo) {
                    eliminado = gestor.eliminarControladorAereo((ControladorAereo) usuarioAEliminar);
                }

                vista.mostrarMensaje(eliminado
                        ? "Usuario eliminado correctamente."
                        : "No se pudo eliminar el usuario.");

                vista.dispose();
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}