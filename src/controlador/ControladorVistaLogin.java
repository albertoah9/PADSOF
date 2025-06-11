package controlador;

import modelo.*;
import vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaLogin {
    private VistaLogin vista;
    private Aeropuerto aeropuerto;

    public ControladorVistaLogin(VistaLogin vista, Aeropuerto aeropuerto) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;

        // Agrega el listener al botón
        this.vista.btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarLogin();
            }
        });
    }

    private void procesarLogin() {
        String usuarioIngresado = vista.txtUsuario.getText().trim();
        String contrasenaIngresada = new String(vista.txtContrasena.getPassword());

        for (Usuario u : aeropuerto.getUsuarios()) {
            if (u.getNombre().equals(usuarioIngresado)) {
                if (u.isBloqueado()) {
                    vista.mostrarMensaje("Cuenta bloqueada. Contacte al gestor.");
                    return;
                }

                if (u.necesitaResetear()) {
                    vista.mostrarMensaje("Debe cambiar su contraseña. Introduzca nueva:");
                    String nueva = javax.swing.JOptionPane.showInputDialog(vista, "Nueva contraseña:");
                    if (nueva != null && !nueva.isBlank()) {
                        u.setContraseña(nueva);
                        u.setNecesitaResetear(false);
                        u.setIntentosFallidos(0);
                        vista.mostrarMensaje("Contraseña actualizada. Inicie sesión nuevamente.");
                        vista.limpiarCampos();
                    }
                    return;
                }

                if (u.getContraseña().equals(contrasenaIngresada)) {
                    u.setIntentosFallidos(0);
                    aeropuerto.setUsuarioActivo(u);
                    vista.dispose();

                    if (u instanceof GestorAeropuerto) {
                        VistaGestorPrincipal vistaGestor = new VistaGestorPrincipal((GestorAeropuerto)aeropuerto.getUsuarioActivo());
                        vistaGestor.setVisible(true);
                    } else if (u instanceof OperadorAereo) {
                        VistaOperadorPrincipal vistaOperador = new VistaOperadorPrincipal();
                        vistaOperador.setVisible(true);
                    } else if (u instanceof ControladorAereo) {
                        VistaControladorPrincipal vistaControlador = new VistaControladorPrincipal();
                        vistaControlador.setVisible(true);
                    } else {
                        vista.mostrarMensaje("Tipo de usuario desconocido.");
                    }
                    return;
                } else {
                    u.incrementarIntentosFallidos();
                    vista.mostrarMensaje("Contraseña incorrecta. Intento " + u.getIntentosFallidos() + "/3");

                    if (u.getIntentosFallidos() >= 3) {
                        u.setBloqueado(true);
                        vista.mostrarMensaje("Cuenta bloqueada. Contacte al gestor.");
                    }
                    return;
                }
            }
        }

        vista.mostrarMensaje("Usuario no encontrado.");
    }
}