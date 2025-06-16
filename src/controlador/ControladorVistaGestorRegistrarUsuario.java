package controlador;

import modelo.*;
import vista.VistaGestorRegistrarUsuario;
import vista.VistaGestorUsuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaGestorRegistrarUsuario {

    private VistaGestorRegistrarUsuario vista;
    private GestorAeropuerto gestor;
    private VistaGestorUsuarios vistaGestorUsuarios;

    public ControladorVistaGestorRegistrarUsuario(VistaGestorRegistrarUsuario vista, GestorAeropuerto gestor, VistaGestorUsuarios vistaGestorUsuarios) {
        this.vista = vista;
        this.gestor = gestor;
        this.vistaGestorUsuarios = vistaGestorUsuarios;

        this.vista.btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        this.vista.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarYVolver();
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    private void cerrarYVolver() {
        vista.dispose();
        vistaGestorUsuarios.setVisible(true);
    }

    private void registrarUsuario() {
        String tipoSeleccionado = (String) vista.cmbTipoUsuario.getSelectedItem();
        String nombre = vista.txtNombre.getText().trim();
        String contrasena = new String(vista.txtContrasena.getPassword());

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            vista.mostrarMensaje("Nombre y contraseña son obligatorios.");
            return;
        }

        try {
            if ("Operador Aéreo".equals(tipoSeleccionado)) {
                Aerolinea aerolinea = (Aerolinea) vista.cmbAerolinea.getSelectedItem();
                if (aerolinea == null) {
                    vista.mostrarMensaje("Debe seleccionar una aerolínea.");
                    return;
                }

                gestor.darDeAltaOperadorAereo(nombre, contrasena, aerolinea);
                vista.mostrarMensaje("Operador registrado con éxito.");

            } else if ("Controlador Aéreo".equals(tipoSeleccionado)) {
                Terminal terminal = (Terminal) vista.cmbTerminal.getSelectedItem();
                gestor.darDeAltaControladorAereo(nombre, contrasena, terminal);
                vista.mostrarMensaje("Controlador registrado con éxito.");
            }

            vista.dispose();

        } catch (Exception ex) {
            vista.mostrarMensaje("Error: " + ex.getMessage());
        }
    }
}