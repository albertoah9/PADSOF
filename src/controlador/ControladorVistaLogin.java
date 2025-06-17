package controlador;

import modelo.*;
import vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ControladorVistaLogin {

    private VistaLogin vista;
    private Aeropuerto aeropuerto;

    public ControladorVistaLogin(VistaLogin vista, Aeropuerto aeropuerto) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;

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

                if (u instanceof OperadorAereo operador) {
                    if (operador.isBloqueado()) {
                        vista.mostrarMensaje("Cuenta bloqueada.");
                        return;
                    }

                    if (operador.necesitaResetear()) {
                        vista.mostrarMensaje("Debe cambiar su contraseña. Introduzca nueva:");
                        String nueva = JOptionPane.showInputDialog(vista, "Nueva contraseña:");
                        if (nueva != null && !nueva.isBlank()) {
                            operador.setContraseña(nueva);
                            operador.setNecesitaResetear(false);
                            operador.setIntentosFallidos(0);
                            vista.mostrarMensaje("Contraseña actualizada. Inicie sesión nuevamente.");
                            vista.limpiarCampos();
                        }
                        return;
                    }
                }

                if (u.getContraseña().equals(contrasenaIngresada)) {
                    if (u instanceof OperadorAereo operador) {
                        operador.setIntentosFallidos(0);
                    }

                    aeropuerto.setUsuarioActivo(u);
                    vista.dispose();

                    if (u instanceof GestorAeropuerto gestor) {
                        VistaGestorPrincipal vistaGestor = new VistaGestorPrincipal(gestor);
                        ControladorVistaGestorPrincipal controladorGestor = new ControladorVistaGestorPrincipal(vistaGestor, aeropuerto, gestor);
                        controladorGestor.iniciar();

                    } else if (u instanceof OperadorAereo operador) {
                        VistaOperadorPrincipal vistaOperador = new VistaOperadorPrincipal();
                        ControladorVistaOperadorPrincipal controladorOperador = new ControladorVistaOperadorPrincipal(vistaOperador, aeropuerto, operador.getAerolineaAsignada());
                        controladorOperador.iniciar();

                    } else if (u instanceof ControladorAereo controlador) {
                        VistaControladorPrincipal vistaControlador = new VistaControladorPrincipal();
                        ControladorVistaControladorPrincipal controladorVistaControlador = new ControladorVistaControladorPrincipal(vistaControlador, aeropuerto, controlador, vista);
                        controladorVistaControlador.iniciar();
                    }

                    return;
                } else {
                    if (u instanceof OperadorAereo operador) {
                        operador.incrementarIntentosFallidos();
                        vista.mostrarMensaje("Contraseña incorrecta. Intento " + operador.getIntentosFallidos() + "/3");

                        if (operador.getIntentosFallidos() >= 3) {
                            operador.setBloqueado(true);
                            vista.mostrarMensaje("Cuenta bloqueada.");
                        }
                    } else {
                        vista.mostrarMensaje("Contraseña incorrecta.");
                    }
                    return;
                }
            }
        }

        vista.mostrarMensaje("Usuario no encontrado.");
    }
}